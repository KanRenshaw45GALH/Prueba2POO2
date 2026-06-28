package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modeloUsuario.Usuario;
import modeloUsuario.UsuarioPremium;

public class InformacionUsuarioControlador {

    @FXML private Label lblNombre;
    @FXML private Label lblEdad;
    @FXML private Label lblCorreo;
    @FXML private Label lblTipoCuenta;

    public void setUsuario(Usuario usuario) {
        lblNombre.setText(usuario.getNombreCompleto());
        lblEdad.setText(String.valueOf(usuario.getEdad()));
        lblCorreo.setText(usuario.getEmail());
        lblTipoCuenta.setText(
                usuario instanceof UsuarioPremium ? "Premium ★" : "General"
        );
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}