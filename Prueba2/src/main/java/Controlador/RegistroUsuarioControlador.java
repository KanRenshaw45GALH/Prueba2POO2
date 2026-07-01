package Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modeloUsuario.ListadoUsuarios;
import modeloUsuario.UsuarioGeneral;
import DAOs.UsuarioDAO;
import DAOs.UsuarioGeneralDAO;

public class RegistroUsuarioControlador {

    @FXML private TextField tfUsuario;
    @FXML private TextField tfEmail;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfFechaNacimiento;
    private ListadoUsuarios listadoUsuarios;

    public void setListadoUsuarios(ListadoUsuarios listadoUsuarios){
        this.listadoUsuarios = listadoUsuarios;
    }

    @FXML
    private void guardarUsuario() {

        try {

            UsuarioGeneral usuario = new UsuarioGeneral();

            usuario.setNombreCompleto(tfUsuario.getText());
            usuario.setEmail(tfEmail.getText());
            usuario.setPassword(pfPassword.getText());
            usuario.setEdad(Integer.parseInt(tfFechaNacimiento.getText()));

            UsuarioGeneralDAO dao = new UsuarioGeneralDAO();

            if(dao.insertar(usuario)){
                listadoUsuarios.agregarUsuario(usuario);
                System.out.println("Guardado correctamente.");
            }else{
                System.out.println("Error al guardar.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirVerificar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormularioVerificar.fxml"));
            Parent root = loader.load();

            VerificarUsuarioControlador controlador = loader.getController();
            controlador.setListadoUsuarios(listadoUsuarios);

            Stage stage = (Stage) tfUsuario.getScene().getWindow();
            stage.setTitle("Verificar Usuario");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}