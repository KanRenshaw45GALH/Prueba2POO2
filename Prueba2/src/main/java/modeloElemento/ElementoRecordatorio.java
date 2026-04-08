package modeloElemento;
import catalogo.Prioridad;
import modeloUsuario.Usuario;

import java.time.format.DateTimeFormatter;

public class ElementoRecordatorio extends Elemento {
    //Atributos:
    private boolean Alerta;
    private DateTimeFormatter fechaRecordatorio;
    private DateTimeFormatter fechaActual;


    //Constructor Parametrizado:
    public ElementoRecordatorio(int id, String titulo, String descripcion, int cantidadColaboradores, Prioridad prioridad, DateTimeFormatter fechaCreacion, DateTimeFormatter fechaLimite, DateTimeFormatter fechaRecordatorio, Usuario usuario) {
        super(id, titulo, descripcion, cantidadColaboradores, prioridad, fechaCreacion, fechaLimite,usuario);
        this.fechaRecordatorio = fechaRecordatorio;
        this.fechaActual = fechaCreacion;
    }


    //Getter y Setter:
    public DateTimeFormatter getFechaRecordatorio() {return fechaRecordatorio; }
    public void setFechaRecordatorio(DateTimeFormatter fechaRecordatorio) { this.fechaRecordatorio = fechaRecordatorio;}

    public DateTimeFormatter getFechaActual() {return fechaActual; }
    public void setFechaActual(DateTimeFormatter fechaActual) { this.fechaActual = fechaActual;}


    //Metodos Propios:
    public void activarAlerta(){
        if(fechaRecordatorio.equals(fechaActual) ){
            Alerta = true;
            System.out.println(" ");
            System.out.println("Alerta activada!!!. Trabajo en fecha Limite!!!. ");
            System.out.println(" ");
        }else{
            Alerta = false;
            System.out.println(" ");
            System.out.println("Alerta desactivada. ");
            System.out.println(" ");
        }
    }


    //Metodos Heredados
    @Override
    public void crearElemento() {
        System.out.println(" ");
        System.out.println("Creando Elemento Recordatorio. ");
        System.out.println(" ");
    }
    @Override
    public void imprimirElementos() {
        super.imprimirElementos();
        System.out.println("Fecha de activacion de Recordatorio: " + getFechaRecordatorio());
        System.out.println("Fecha Actual: " + getFechaActual());
        System.out.println(" ");
    }
}
