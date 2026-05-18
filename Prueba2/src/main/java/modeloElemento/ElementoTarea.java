package modeloElemento;
import catalogo.Estado;
import catalogo.Prioridad;
import modeloUsuario.Usuario;
import java.time.LocalDate;


public class ElementoTarea extends Elemento {
    //Atributos:
    private Estado estado;


    //Constructor Parametrizado:
    public ElementoTarea(int id, String titulo, String descripcion, int cantidadColaboradores, Prioridad prioridad, Estado estado, LocalDate fechaCreacion, LocalDate fechaLimite, Usuario usuario) {
        super(id, titulo, descripcion, cantidadColaboradores, prioridad, fechaCreacion, fechaLimite, usuario);
        this.estado = estado;
    }
    public ElementoTarea() {super();}


    //Getter y Setter:
    public Estado getEstado() {return estado;}
    public void setEstado(Estado estado) {this.estado = estado;}


    //Metodos Propios:


    //Metodos Heredados:
    @Override
    public void crearElemento() {
        super.crearElemento();
    }
    @Override
    public void imprimirElementos() {
        super.imprimirElementos();
        System.out.println("Estado: " + estado);
    }


}





