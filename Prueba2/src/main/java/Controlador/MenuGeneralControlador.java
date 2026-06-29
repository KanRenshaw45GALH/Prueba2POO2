package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modeloUsuario.Usuario;

import java.io.IOException;

public class MenuGeneralControlador {

    @FXML private Button BtnTarea;
    @FXML private Button BtnRecordatorio;
    @FXML private Button BtnElementosCreados;
    @FXML private Button BtnPendientes;
    @FXML private Button BtnCompletarTarea;
    @FXML private Button BtnEditar;
    @FXML private Button BtnCompartir;
    @FXML private Button BtnEliminar;
    @FXML private Button BtnInformacionUsuario;
    @FXML private Button BtnSuscripcion;
    @FXML private Button BtnSalir;


    private Usuario usuarioActivo;
    public void setUsuario(Usuario usuario){
        this.usuarioActivo = usuario;
    }



    @FXML
    private void agregarTarea(ActionEvent event) {

    }
    @FXML
    private void agregarRecordatorio(ActionEvent event) {

    }
    @FXML
    private void verTodos(ActionEvent event) {

    }
    @FXML
    private void verPendientes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/VerPendientes.fxml"));
        Parent root = loader.load();

        VerPendientesControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) BtnPendientes.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    private void completarTareas(ActionEvent event) {

    }
    @FXML
    private void editarElemento(ActionEvent event) {

    }
    @FXML
    private void compartirElemento(ActionEvent event) {

    }
    @FXML
    private void eliminarElemento(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EliminarElemento.fxml"));
        Parent root = loader.load();

        EliminarElementoControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) BtnEliminar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    private void imprimirUsuario(ActionEvent event) {

    }
    @FXML
    private void cambiarSuscripcion(ActionEvent event) {

    }
    @FXML
    private void salir(ActionEvent event) {

    }


}
