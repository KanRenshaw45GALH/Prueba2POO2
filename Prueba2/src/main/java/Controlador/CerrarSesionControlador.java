package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modeloUsuario.Usuario;

import java.io.IOException;

public class CerrarSesionControlador {

    @FXML private Button BtnVolver;
    private Usuario usuarioActivo;
    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;
    }


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
    private void cancelar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) BtnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}