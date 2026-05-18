package modeloUsuario;
import modeloElemento.Elemento;
import java.time.LocalDate;
import java.util.Scanner;


public class UsuarioGeneral extends Usuario{
    //Atributos:
    private boolean activarSuscripcion = false;
    final private int limiteElementosTareas = 8;
    final private int limiteElementosRecordatorios = 4;
    private int contadorElementoTareas = 0;
    private int contadorElementoRecordatorios = 0;


    //Constructores Parametrizados:
    public UsuarioGeneral(String nombreCompleto, int edad, String email, String password, int cantidadTareas, int cantidadRecordatorios, boolean activarSuscripcion, int contadorElementoTareas, int contadorElementoRecordatorios) {
        super(nombreCompleto, edad, email, password, cantidadTareas, cantidadRecordatorios);
        this.activarSuscripcion = activarSuscripcion;
        this.contadorElementoTareas = contadorElementoTareas;
        this.contadorElementoRecordatorios = contadorElementoRecordatorios;
    }
    public UsuarioGeneral(boolean activarSuscripcion, int contadorElementoTareas, int contadorElementoRecordatorios) {
        this.activarSuscripcion = activarSuscripcion;
        this.contadorElementoTareas = contadorElementoTareas;
        this.contadorElementoRecordatorios = contadorElementoRecordatorios;
    }
    //Construtor Vacio:
    public UsuarioGeneral(){    super();}


    //Getter y Setter:
    public boolean isActivarSuscripcion() {return activarSuscripcion;}
    public void setActivarSuscripcion(boolean activarSuscripcion) {this.activarSuscripcion = activarSuscripcion;}

    public int getContadorElementoTareas() {
        return contadorElementoTareas;
    }
    public void setContadorElementoTareas(int contadorElementoTareas) {this.contadorElementoTareas = contadorElementoTareas;}

    public int getContadorElementoRecordatorios() {
        return contadorElementoRecordatorios;
    }
    public void setContadorElementoRecordatorios(int contadorElementoRecordatorios) {this.contadorElementoRecordatorios = contadorElementoRecordatorios;}


    //Metodos Propios:
    public void activarSuscripcion() {
        System.out.println(" ");

        if(activarSuscripcion == false){
            activarSuscripcion = true;
            System.out.println(" ");
            System.out.println("La Suscripcion ha sido activada");
        }
    }
    public boolean conteoTarea() {
        if (getContadorElementoTareas() < limiteElementosTareas) {
            contadorElementoTareas++;
            System.out.println("Tarea agregada. (" + getContadorElementoTareas() + "/" + limiteElementosTareas + ")");
            return true;
        } else {
            System.out.println("Límite de tareas alcanzado: " + limiteElementosTareas);
            return false;
        }
    }
    public boolean conteoRecordatorio() {
        if (getContadorElementoRecordatorios() < limiteElementosRecordatorios) {
            contadorElementoRecordatorios++;
            System.out.println("Recordatorio agregado. (" + getContadorElementoRecordatorios() + "/" + limiteElementosRecordatorios + ")");
            return true;
        } else {
            System.out.println("Límite de recordatorios alcanzado: " + limiteElementosRecordatorios);
            return false;
        }
    }


    //Metodos Heredados:
    @Override
    public void verificarUsuario() {}
    @Override
    public void crearElemento(Elemento elemento) {}
    @Override
    public void imprimirUsuario() {
        super.imprimirUsuario();
        System.out.println("\n En tu version actual no gozas de los beneficios completos. \n Para ello debes comprar el servicio Premium. ");
    }
    @Override
    public void modoSuscripcion() {
        System.out.println(" Desea activar la suscripcion?. Ingrese Si o No: ");
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();
        if(respuesta.equalsIgnoreCase("Si")){
            UsuarioPremium premium = new UsuarioPremium();
            premium.setAccesoCompleto(true);
            System.out.println("\n La suscripcion esta activada");
            System.out.println("No posees limites para la creacion de Tareas, Recordatorios y Compartidos. \n");

        }else {
            setAccesoCompleto(false);
            System.out.println(" La suscripcion se mantiene sin una suscripcion. \n");
        }
    }


}
