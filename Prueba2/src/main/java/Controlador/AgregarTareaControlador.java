package Controlador;

import DAOs.ElementoTareaDAO;
import catalogo.Estado;
import catalogo.Prioridad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modeloElemento.ElementoTarea;
import modeloUsuario.Usuario;
import java.io.IOException;
import java.time.LocalDate;


public class AgregarTareaControlador {

    @FXML private TextField TextTitulo;
    @FXML private TextArea TextDescripcion;
    @FXML private ComboBox<Prioridad> ComboBoxPrioridad;
    @FXML private ComboBox<Estado> ComboBoxEstado;
    @FXML private DatePicker DatePickerFechaLimite;
    @FXML private Button BtnGuardar;
    @FXML private Button BtnVolver;
    ElementoTareaDAO dao;

    private Usuario usuarioActivo;

    //Metodos:
    public void setUsuarioActivo(Usuario usuario) {
        this.usuarioActivo = usuario;
    }
    @FXML
    public void initialize(){
        ComboBoxPrioridad.getItems().setAll(Prioridad.values());
        ComboBoxEstado.getItems().setAll(Estado.values());

    }
    @FXML
    public void guardar(ActionEvent event) {
        String titulo = TextTitulo.getText();
        String descripcion = TextDescripcion.getText();
        Prioridad prioridad = ComboBoxPrioridad.getValue();
        LocalDate fecha = DatePickerFechaLimite.getValue();
        Estado estado = ComboBoxEstado.getValue();

            if(titulo.isBlank()){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Debe agregar un Titulo.");
                alerta.showAndWait();
                return;
            }
            if(descripcion.isBlank()){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Debe agregar una Descripcion.");
                alerta.showAndWait();
                return;
            }
            if(prioridad == null){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Debe agregar una Prioridad.");
                alerta.showAndWait();
                return;
            }
            if(estado == null){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Debe agregar un EStado.");
                alerta.showAndWait();
                return;
            }
            if(fecha == null){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Debe agregar una Fecha.");
                alerta.showAndWait();
                return;
            }
                    if (fecha.isBefore(LocalDate.now())) {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setHeaderText(null);
                        alerta.setTitle("Error");
                        alerta.setContentText("La fecha límite no puede ser anterior a hoy.");
                        alerta.showAndWait();
                        return;
                    }
            if (usuarioActivo == null) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("No hay un usuario activo.");
                alerta.showAndWait();
                return;
            }

        ElementoTarea tarea = new ElementoTarea();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setPrioridad(prioridad);
        tarea.setEstado(estado);
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setFechaLimite(fecha);
        tarea.setUsuario(usuarioActivo);

        System.out.println("ID Usuario = " + usuarioActivo.getIdUsuario());

        ElementoTareaDAO dao = new ElementoTareaDAO();
        boolean guardado = dao.insertar(usuarioActivo.getIdUsuario(), tarea);
        if (guardado) {
            usuarioActivo.getElemento().add(tarea);
            TextTitulo.clear();
            TextDescripcion.clear();
            ComboBoxPrioridad.getSelectionModel().clearSelection();
            ComboBoxEstado.getSelectionModel().clearSelection();
            DatePickerFechaLimite.setValue(null);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("Éxito");
            alerta.setContentText("La tarea se creó correctamente.");
            alerta.showAndWait();

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setContentText("No fue posible guardar la tarea.");
            alerta.showAndWait();
        }


    }
    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) BtnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }




}
