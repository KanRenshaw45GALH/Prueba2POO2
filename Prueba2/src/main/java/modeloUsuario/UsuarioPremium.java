package modeloUsuario;
import modeloElemento.Elemento;
import java.time.LocalDate;


public class UsuarioPremium extends Usuario {
    //Atributos:
    private float pagarSuscripcion;
    private boolean compartirElemento = true;
    private LocalDate fechaSuscripcion;
    private LocalDate fechaLimiteSuscripcion;


    //Construtor Parametrizado:
    public UsuarioPremium(String nombreCompleto, int edad, String email, String password, boolean accesoCompleto, LocalDate fechaActual, Elemento elemento, float pagarSuscripcion, boolean compartirElemento, LocalDate fechaSuscripcion, LocalDate fechaLimiteSuscripcion) {
        super(nombreCompleto, edad, email, password, accesoCompleto, fechaActual, elemento);
        this.pagarSuscripcion = pagarSuscripcion;
        this.compartirElemento = compartirElemento;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;
    }

    //Getter y Setter
    public float getPagarSuscripcion() {return pagarSuscripcion;}
    public void setPagarSuscripcion(float pagarSuscripcion) {this.pagarSuscripcion = pagarSuscripcion;}

    public boolean isCompartirElemento() {return compartirElemento;}
    public void setCompartirElemento(boolean compartirElemento) {}

    public LocalDate getFechaSuscripcion() {return fechaSuscripcion;}
    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {this.fechaSuscripcion = fechaSuscripcion;}

    public LocalDate getFechaLimiteSuscripcion() {return fechaLimiteSuscripcion;}
    public void setFechaLimiteSuscripcion(LocalDate fechaLimiteSuscripcion) {this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;}


    //Metodos Propios:
    public boolean cancelarSuscripcion(){
        Usuario usuario = null;
        if(getAccesoCompleto() == true) {
            pagarSuscripcion = 0;
            usuario.setAccesoCompleto();
        }
        System.out.println(" ");
        System.out.println("Suscripcion cancelada correctamente");
        System.out.println(" ");
        return true;
    }

    public float pagarSuscripcion() {
        System.out.println(" ");
        System.out.println("Para mantener los beneficios debe pagar suscripcion. ");
        System.out.println("El precio a pagar es de: "+ getPagarSuscripcion() + " antes de la fecha Limite: " + getFechaLimiteSuscripcion());
        System.out.println("Suscripcion pagada correctamente. ");
        System.out.println(" ");
        return pagarSuscripcion;

    }


    //Metodos Heredados:
    @Override
    public void verificarUsuario() {}
    @Override
    public void imprimirUsuario() {
        super.imprimirUsuario();
        System.out.println("Suscripcion: " + getPagarSuscripcion());
        System.out.println("Fecha de Suscripcion" + getFechaSuscripcion());
        System.out.println("Fecha Limite: " + getFechaLimiteSuscripcion());
        System.out.println(" ");
    }
    @Override
    public void modoSuscripcion() {
        if(getAccesoCompleto() == true){
            System.out.println(" ");
            System.out.println("Suscripcion esta activada");
            System.out.println("No posees limites para la creacion de Tareas, Recordatorion o Compartidos. ");
            System.out.println(" ");
        }else{
            System.out.println(" ");
            System.out.println("Suscripcion esta desactivada");
            System.out.println(" ");
        }
    }


}
