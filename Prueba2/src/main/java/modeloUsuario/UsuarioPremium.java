package modeloUsuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class UsuarioPremium extends Usuario {
    //Atributos:
    private float pagarSuscripcion;
    private boolean activarSuscripcion = true;
    private boolean accesoCompleto = true;
    private boolean compartirElemento = true;
    private LocalDate fechaActual = LocalDate.now();
    private LocalDate fechaSuscripcion;
    private LocalDate fechaLimiteSuscripcion;


    //Construtor Parametrizado:
    public UsuarioPremium(String nombreCompleto, int edad, String email, String password, float pagarSuscripcion, boolean activarSuscripcion, boolean accesoCompleto, boolean compartirElemento, LocalDate fechaActual, LocalDate fechaSuscripcion, LocalDate fechaLimiteSuscripcion) {
        super(nombreCompleto, edad, email, password);
        this.pagarSuscripcion = pagarSuscripcion;
        this.activarSuscripcion = activarSuscripcion;
        this.accesoCompleto = accesoCompleto;
        this.fechaActual = fechaActual;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;
    }


    //Getter y Setter
    public float getPagarSuscripcion() {return pagarSuscripcion;}
    public void setPagarSuscripcion(float pagarSuscripcion) {this.pagarSuscripcion = pagarSuscripcion;}

    public boolean isActivarSuscripcion() {return activarSuscripcion;}
    public void setActivarSuscripcion(boolean activarSuscripcion) {}

    public boolean isAccesoCompleto() {return accesoCompleto;}
    public void setAccesoCompleto(boolean accesoCompleto) {}

    public boolean isCompartirElemento() {return compartirElemento;}
    public void setCompartirElemento(boolean compartirElemento) {}

    public LocalDate getFechaActual() {return fechaActual;}
    public void setFechaActual(LocalDate fechaActual) {this.fechaActual = fechaActual;}

    public LocalDate getFechaSuscripcion() {return fechaSuscripcion;}
    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {this.fechaSuscripcion = fechaSuscripcion;}

    public LocalDate getFechaLimiteSuscripcion() {return fechaLimiteSuscripcion;}
    public void setFechaLimiteSuscripcion(LocalDate fechaLimiteSuscripcion) {this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;}


    //Metodos Propios:
    public boolean cancelarSuscripcion(){
        if(activarSuscripcion == true) {
            activarSuscripcion = false;
            accesoCompleto = false;
        }
        System.out.println(" ");
        System.out.println("Suscripcion cancelada correctamente");
        System.out.println(" ");
        return true;
    }

    public float PagarSuscripcion() {
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
        if(activarSuscripcion == true){
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
