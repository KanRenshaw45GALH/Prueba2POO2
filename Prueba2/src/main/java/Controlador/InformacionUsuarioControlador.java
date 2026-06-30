package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modeloUsuario.Usuario;
import modeloUsuario.UsuarioPremium;

import java.io.IOException;

public class InformacionUsuarioControlador {

    @FXML private Label lblNombre;
    @FXML private Label lblEdad;
    @FXML private Label lblCorreo;
    @FXML private Label lblTipoCuenta;
    Usuario usuarioActivo;

    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;

        lblNombre.setText(usuario.getNombreCompleto());
        lblEdad.setText(String.valueOf(usuario.getEdad()));
        lblCorreo.setText(usuario.getEmail());
        lblTipoCuenta.setText(
                usuario instanceof UsuarioPremium ? "Premium ★" : "General"
        );
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}