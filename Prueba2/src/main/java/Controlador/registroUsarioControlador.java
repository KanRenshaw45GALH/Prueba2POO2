package Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class registroUsarioControlador {

    @FXML private TextField tfUsuario;
    @FXML private TextField tfEmail;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfFechaNacimiento;
    @FXML private Label lblMensajeRegistro;

    @FXML private TextField tfUsuarioVerificar;
    @FXML private PasswordField pfPasswordVerificar;
    @FXML private Label lblMensajeVerificar;

    @FXML
    private void guardarUsuario() {
        // aquí llamas a tu GestorUsuario para registrar
    }

    @FXML
    private void verificarUsuario() {
        // aquí llamas a tu GestorUsuario para verificar
    }
}