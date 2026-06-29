package Controlador;

import catalogo.Estado;
import catalogo.Prioridad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modeloElemento.Elemento;
import modeloUsuario.Usuario;

import java.time.LocalDate;


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
    public void guardar(ActionEvent event) {
        String titulo = TextTitulo.getText();
        String descripcion = TextDescripcion.getText();
        Prioridad prioridad = ComboBoxPrioridad.getValue();
        LocalDate fecha = DatePickerFechaLimite.getValue();
        Estado estado = ComboBoxEstado.getValue();

        if(titulo.isBlank()){

        }if(titulo.isBlank()){

        }

    }
    @FXML
    private void volver(ActionEvent event){

    }





}
