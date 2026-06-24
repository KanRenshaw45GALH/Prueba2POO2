package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MenuBienvenidaControlador {

    @FXML
    private void irARegistro(ActionEvent event) {
        cambiarPantalla(event, "/FormularioRegistro.fxml");
    }

    @FXML
    private void irAVerificar(ActionEvent event) {
        cambiarPantalla(event, "/FormularioVerificar.fxml");
    }

    private void cambiarPantalla(ActionEvent event, String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se pudo cargar: " + ruta);
        }
    }
}