package Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroUsuarioControlador {

    @FXML private TextField tfUsuario;
    @FXML private TextField tfEmail;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfFechaNacimiento;

    @FXML
    private void guardarUsuario() {
        // aquí llamas a tu GestorUsuario para registrar
    }

    @FXML
    private void abrirVerificar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormularioVerificar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Verificar Usuario");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}