package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modeloUsuario.ListadoUsuarios;
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

    ListadoUsuarios listadoUsuarios;
    public void setListadoUsuarios(ListadoUsuarios listadoUsuarios){
        this.listadoUsuarios = listadoUsuarios;
    }



    @FXML
    private void agregarTarea(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AgregarTarea.fxml"));
        Parent root = loader.load();

        AgregarTareaControlador controlador = loader.getController();
        controlador.setUsuarioActivo(usuarioActivo);

        Stage stage = (Stage) BtnTarea.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    private void agregarRecordatorio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AgregarRecordatorio.fxml"));
        Parent root = loader.load();

        AgregarRecordatorioControlador controlador = loader.getController();
        controlador.setUsuarioActivo(usuarioActivo);

        Stage stage = (Stage) BtnRecordatorio.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

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
    private void editarElemento(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditarElemento.fxml"));
        Parent root = loader.load();

        EditarElementoControlador controlador = loader.getController();
        controlador.setUsuarioActivo(usuarioActivo);

        Stage stage = (Stage) BtnEditar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

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
    private void imprimirUsuario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InformacionUsuario.fxml"));
        Parent root = loader.load();

        InformacionUsuarioControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) BtnInformacionUsuario.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }
    @FXML
    private void cambiarSuscripcion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CambiarSuscripcion.fxml"));
        Parent root = loader.load();

        CambiarSuscripcionControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) BtnSuscripcion.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    private void salir(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CerrarSesion.fxml"));
        Parent root = loader.load();

        CerrarSesionControlador controlador = loader.getController();

        Stage stage = (Stage) BtnSalir.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }


}
