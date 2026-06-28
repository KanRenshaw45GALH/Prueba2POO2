package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CerrarSesionControlador {

    @FXML
    private void confirmarCerrar(ActionEvent event) {
        try {
            // Cierra la ventana actual
            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();

            // Abre el MenuBienvenida
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuBienvenida.fxml"));
            Parent root = loader.load();
            Stage stageBienvenida = new Stage();
            stageBienvenida.setTitle("Gestor de Tareas y Recordatorios");
            stageBienvenida.setScene(new Scene(root, 640, 480));
            stageBienvenida.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}