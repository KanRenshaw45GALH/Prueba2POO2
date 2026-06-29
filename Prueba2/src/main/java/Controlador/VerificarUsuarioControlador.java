package Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modeloUsuario.GestorUsuario;
import modeloUsuario.ListadoUsuarios;
import modeloUsuario.Usuario;
import javafx.event.ActionEvent;
import java.io.IOException;

public class VerificarUsuarioControlador {

    @FXML private TextField tfUsuarioVerificar;
    @FXML private PasswordField pfPasswordVerificar;
    Usuario usuario;
    ListadoUsuarios listadoUsuarios = new ListadoUsuarios();

    @FXML
    private void verificarUsuario(ActionEvent event) throws IOException {
        String correo = tfUsuarioVerificar.getText();
        String password = pfPasswordVerificar.getText();

         usuario = listadoUsuarios.iniciarSesion(correo, password);

        if(usuario != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
            Parent root = loader.load();
            MenuGeneralControlador controlador = loader.getController();
            controlador.setUsuario(usuario);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }else{
            //Lanza error

        }

    }
}