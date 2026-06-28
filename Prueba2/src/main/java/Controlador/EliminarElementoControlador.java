package Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import modeloElemento.Elemento;
import modeloElemento.ElementoTarea;
import modeloUsuario.Usuario;
import java.util.List;

public class EliminarElementoControlador {

    @FXML private ListView<String> listaElementos;
    @FXML private Label lblMensaje;

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
    private void volver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}