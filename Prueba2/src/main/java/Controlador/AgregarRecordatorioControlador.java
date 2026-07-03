package Controlador;

import catalogo.Prioridad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modeloElemento.ElementoRecordatorio;
import modeloUsuario.Usuario;
import java.io.IOException;
import java.time.LocalDate;
import DAOs.ElementoRecordatorioDAO;
import DAOs.UsuarioDAO;


public class AgregarRecordatorioControlador {

    @FXML private TextField TextTitulo;
    @FXML private TextArea TextDescripcion;
    @FXML private ComboBox<Prioridad> ComboBoxPrioridad;
    @FXML private DatePicker DatePickerFechaLimite;
    @FXML private Button BtnGuardar;
    @FXML private Button BtnVolver;

    private Usuario usuarioActivo;
    public void setUsuarioActivo(Usuario usuario) {
        this.usuarioActivo = usuario;
    }

    @FXML
    public void initialize(){
        ComboBoxPrioridad.getItems().setAll(Prioridad.values());

    }
    @FXML
    public void guardar(ActionEvent event) {
        String titulo = TextTitulo.getText();
        String descripcion = TextDescripcion.getText();
        Prioridad prioridad = ComboBoxPrioridad.getValue();
        LocalDate fecha = DatePickerFechaLimite.getValue();

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

        ElementoRecordatorio recordatorio = new ElementoRecordatorio();
        recordatorio.setTitulo(titulo);
        recordatorio.setDescripcion(descripcion);
        recordatorio.setPrioridad(prioridad);
        recordatorio.setFechaCreacion(LocalDate.now());
        recordatorio.setFechaLimite(fecha);      // Importante
        recordatorio.setFechaRecordatorio(fecha);
        recordatorio.setUsuario(usuarioActivo);

        ElementoRecordatorioDAO dao = new ElementoRecordatorioDAO();

        boolean guardado = dao.insertar(usuarioActivo.getIdUsuario(), recordatorio);

        if (guardado) {

            // Solo si seguís usando la lista en memoria
            usuarioActivo.getElemento().add(recordatorio);

            TextTitulo.clear();
            TextDescripcion.clear();
            ComboBoxPrioridad.getSelectionModel().clearSelection();
            DatePickerFechaLimite.setValue(null);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("Éxito");
            alerta.setContentText("El recordatorio se creó correctamente.");
            alerta.showAndWait();

        } else {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setContentText("No se pudo guardar el recordatorio en la base de datos.");
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

