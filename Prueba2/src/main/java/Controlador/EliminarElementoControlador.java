package Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import modeloElemento.Elemento;
import modeloElemento.ElementoTarea;
import modeloUsuario.Usuario;

import java.io.IOException;
import java.util.List;

public class EliminarElementoControlador {

    @FXML private ListView<String> listaElementos;
    @FXML private Label lblMensaje;
    @FXML private Button BtnEliminar;
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
            lblMensaje.setText("No tienes elementos registrados.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            Elemento e = lista.get(i);
            String tipo = (e instanceof ElementoTarea) ? "TAREA" : "RECORDATORIO";
            items.add((i + 1) + ". [" + tipo + "] " + e.getTitulo());
        }
        listaElementos.setItems(items);
    }

    @FXML
    private void eliminarSeleccionado() {
        int idx = listaElementos.getSelectionModel().getSelectedIndex();

        if (idx < 0) {
            lblMensaje.setText("Selecciona un elemento primero.");
            lblMensaje.setStyle("-fx-text-fill: #e53935;");
            return;
        }

        Elemento eliminado = lista.get(idx);
        lista.remove(idx);
        lblMensaje.setText("Eliminado: " + eliminado.getTitulo());
        lblMensaje.setStyle("-fx-text-fill: #2e7d32;");
        cargarLista();
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) BtnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}