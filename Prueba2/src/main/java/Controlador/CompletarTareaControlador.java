package Controlador;

import catalogo.Estado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modeloElemento.Elemento;
import modeloElemento.ElementoTarea;
import modeloUsuario.Usuario;

import java.io.IOException;
import java.util.List;

public class CompletarTareaControlador {

    @FXML private ListView<String> listaTareas;
    @FXML private Label lblMensaje;
    @FXML private Button BtnCompletar;
    @FXML private Button BtnVolver;

    private Usuario usuarioActivo;
    private List<Elemento> lista;

    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;
        cargarLista();
    }

    private void cargarLista() {
        lista = usuarioActivo.getElemento();
        ObservableList<String> items = FXCollections.observableArrayList();

        if (lista == null || lista.isEmpty()) {
            lblMensaje.setText("No tienes tareas registradas.");
            lblMensaje.setStyle("-fx-text-fill: #e53935;");
            return;
        }

        boolean hayTareas = false;
        for (int i = 0; i < lista.size(); i++) {
            Elemento e = lista.get(i);
            if (e instanceof ElementoTarea tarea) {
                // Solo mostrar tareas que NO estén completadas ni canceladas
                if (tarea.getEstado() != Estado.COMPLETADO && tarea.getEstado() != Estado.CANCELADA) {
                    items.add((i + 1) + ". [" + tarea.getEstado() + "] " + tarea.getTitulo());
                    hayTareas = true;
                }
            }
        }

        if (!hayTareas) {
            lblMensaje.setText("No tienes tareas pendientes.");
            lblMensaje.setStyle("-fx-text-fill: #e53935;");
            return;
        }

        listaTareas.setItems(items);
        lblMensaje.setText("");
    }

    @FXML
    private void completarSeleccionada(ActionEvent event) {
        String seleccionada = listaTareas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            lblMensaje.setText("Selecciona una tarea primero.");
            lblMensaje.setStyle("-fx-text-fill: #e53935;");
            return;
        }

        // Extraer el índice real de la lista original (número al inicio - 1)
        int indiceReal = Integer.parseInt(seleccionada.split("\\.")[0]) - 1;
        Elemento elemento = lista.get(indiceReal);

        if (elemento instanceof ElementoTarea tarea) {
            tarea.setEstado(Estado.COMPLETADO);
            lblMensaje.setText("✔ Tarea completada: " + tarea.getTitulo());
            lblMensaje.setStyle("-fx-text-fill: #2e7d32;");
            cargarLista(); // Refrescar la lista
        }
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);
        controlador.setListadoUsuarios(null); // Si no tenés listado aquí, se pasa null

        Stage stage = (Stage) BtnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
