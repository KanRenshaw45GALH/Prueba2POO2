package Controlador;

import catalogo.Estado;
import catalogo.Prioridad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modeloElemento.Elemento;
import modeloElemento.ElementoTarea;
import modeloUsuario.Usuario;
import java.io.IOException;


public class EditarElementoControlador {

    @FXML private ComboBox<Elemento> ComboBoxElementosAlmacenados;
    @FXML private TextField TextTitulo;
    @FXML private TextArea TextDescripcion;
    @FXML private ComboBox<Prioridad> ComboBoxPrioridad;
    @FXML private ComboBox<Estado> ComboBoxEstado;
    @FXML private DatePicker DatePickerFechaLimite;
    @FXML private Button BtnGuardar;
    @FXML private Button BtnVolver;

    private Usuario usuarioActivo;

    //Metodos:
    public void setUsuarioActivo(Usuario usuario) {
        this.usuarioActivo = usuario;
        ComboBoxElementosAlmacenados.getItems().setAll(usuario.getElemento());
    }
    @FXML
    public void initialize(){
        ComboBoxPrioridad.getItems().setAll(Prioridad.values());
        ComboBoxEstado.getItems().setAll(Estado.values());

    }
    @FXML
    private void cargarElementosAlmacenados(ActionEvent event) {
        Elemento elemento = ComboBoxElementosAlmacenados.getValue();
        if (elemento == null) {
            return;
        }

        TextTitulo.setText(elemento.getTitulo());
        TextDescripcion.setText(elemento.getDescripcion());
        ComboBoxPrioridad.setValue(elemento.getPrioridad());
        DatePickerFechaLimite.setValue(elemento.getFechaLimite());
        //En caso sea Tarea o recordatorio:
            if (elemento instanceof ElementoTarea tarea) {
                ComboBoxEstado.setValue(tarea.getEstado());
            }
            else {
                ComboBoxEstado.getSelectionModel().clearSelection();
                ComboBoxEstado.setDisable(true);
            }
        ComboBoxEstado.setDisable(false);

    }

    @FXML
    public void guardar(ActionEvent event) {
        Elemento elemento = ComboBoxElementosAlmacenados.getValue();
        //Verifica la existencia del elemento.
        if (elemento == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Seleccione un elemento.");
            alerta.showAndWait();
            return;
        }

        //Actualiza los Datos:
        elemento.setTitulo(TextTitulo.getText());
        elemento.setDescripcion(TextDescripcion.getText());
        elemento.setPrioridad(ComboBoxPrioridad.getValue());
        elemento.setFechaLimite(DatePickerFechaLimite.getValue());
        //Verifica si es Tarea o Recordatorio:
        if (elemento instanceof ElementoTarea tarea) {
            tarea.setEstado(ComboBoxEstado.getValue());
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("Éxito");
        alerta.setContentText("El elemento se edito correctamente.");
        alerta.showAndWait();

        TextTitulo.clear();
        TextDescripcion.clear();
        ComboBoxPrioridad.getSelectionModel().clearSelection();
        ComboBoxEstado.getSelectionModel().clearSelection();
        DatePickerFechaLimite.setValue(null);

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
