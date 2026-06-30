package Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Usuario usuario;
    private ListadoUsuarios listadoUsuarios;


    //Metodos:
    public void setListadoUsuarios(ListadoUsuarios listadoUsuarios){
        this.listadoUsuarios = listadoUsuarios;
    }

    @FXML
    private void verificarUsuario(ActionEvent event) throws IOException {
        String nombre = tfUsuarioVerificar.getText();
        String password = pfPasswordVerificar.getText();

        if(nombre.isBlank() || password.isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe completar todos los campos.");
            alert.showAndWait();
            return;
        }

         usuario = listadoUsuarios.iniciarSesion(nombre, password);

        if (usuario != null) {
            System.out.println("Usuario encontrado");
        } else {
            System.out.println("Usuario NO encontrado");
        }

        if(usuario != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
            Parent root = loader.load();
            MenuGeneralControlador controlador = loader.getController();
            controlador.setUsuario(usuario);
            controlador.setListadoUsuarios(listadoUsuarios);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }else{
            //Lanza error
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setContentText("Correo o contraseña incorrectos.");
            alerta.showAndWait();

        }

    }
}