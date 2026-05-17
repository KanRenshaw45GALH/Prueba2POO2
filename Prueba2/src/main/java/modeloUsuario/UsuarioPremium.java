package modeloUsuario;
import java.time.LocalDate;
import java.util.Scanner;


public class UsuarioPremium extends Usuario {
    //Atributos:
    private float pagarSuscripcion = 4.99f;
    private boolean compartirElemento = true;
    private LocalDate fechaSuscripcion;
    private LocalDate fechaLimiteSuscripcion;


    //Construtores Parametrizados:
    public UsuarioPremium(String nombreCompleto, int edad, String email, String password, int cantidadTareas, int cantidadRecordatorios, float pagarSuscripcion, boolean compartirElemento, LocalDate fechaSuscripcion, LocalDate fechaLimiteSuscripcion) {
        super(nombreCompleto, edad, email, password, cantidadTareas, cantidadRecordatorios);
        this.pagarSuscripcion = pagarSuscripcion;
        this.compartirElemento = compartirElemento;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;
    }
    public UsuarioPremium(float pagarSuscripcion, boolean compartirElemento, LocalDate fechaSuscripcion, LocalDate fechaLimiteSuscripcion) {
        this.pagarSuscripcion = pagarSuscripcion;
        this.compartirElemento = compartirElemento;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;
    }
    //Construtor Vacio:
    public UsuarioPremium() {super();}


    //Getter y Setter
    public float getPagarSuscripcion() {return pagarSuscripcion;}
    public void setPagarSuscripcion(float pagarSuscripcion) {this.pagarSuscripcion = pagarSuscripcion;}

    public boolean isCompartirElemento() {return compartirElemento;}
    public void setCompartirElemento(boolean compartirElemento) {}

    public LocalDate getFechaSuscripcion() {return fechaSuscripcion;}
    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {this.fechaSuscripcion = fechaSuscripcion;}

    public LocalDate getFechaLimiteSuscripcion() {return fechaLimiteSuscripcion;}
    public void setFechaLimiteSuscripcion(LocalDate fechaLimiteSuscripcion) {this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;}


//    //Metodos Propios:
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
    public void crearElemento() {}
    @Override
    public void imprimirUsuario() {
        super.imprimirUsuario();
        System.out.println(" Usted posee un servicio Premium. ");
        System.out.println("Suscripcion actual posee un precio de: " + getPagarSuscripcion());
        System.out.println("Fecha de Suscripcion" + getFechaSuscripcion());
        System.out.println("Fecha Limite: " + getFechaLimiteSuscripcion());
    }
    @Override
    public void modoSuscripcion() {
        System.out.println(" Desea desactivar la suscripcion?. Ingrese Si o No: ");
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();
        if(respuesta.equalsIgnoreCase("Si")){
            UsuarioGeneral general = new UsuarioGeneral();
            general.setAccesoCompleto(false);
            System.out.println("\n La suscripcion esta desactivada. ");
            System.out.println("Ahorra posees un limite en la creacion de Elementos. \n");

        }else{
            setAccesoCompleto(false);
            System.out.println("\n La suscripcion sigue activada");
            System.out.println("No posees limites para la creacion de Tareas, Recordatorios y Compartidos. \n");
        }
    }


}