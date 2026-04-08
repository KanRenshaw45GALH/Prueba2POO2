package modeloUsuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class UsuarioGeneral extends Usuario{
    //Atributos:
    private boolean activarSuscripcion = false;
    private int limiteElementosTareas = 8;
    private int limiteElementosRecordatorios = 4;
    private int limiteElementosCompartidos = 1;
    private LocalDate fechaActual = LocalDate.now();
    private DateTimeFormatter fechaSuscripcion = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter fechaLimiteSuscripcion = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    //Constructor Parametrizado:
    public UsuarioGeneral(String nombreCompleto, int edad, String email, String password, boolean activarSuscripcion, int limiteElementosTareas, int limiteElementosRecordatorios, int limiteElementosCompartidos, LocalDate fechaActual, DateTimeFormatter fechaSuscripcion, DateTimeFormatter fechaLimiteSuscripcion) {
        super(nombreCompleto,edad,email,password);
        this.activarSuscripcion = activarSuscripcion;
        this.limiteElementosTareas = limiteElementosTareas;
        this.limiteElementosRecordatorios = limiteElementosRecordatorios;
        this.limiteElementosCompartidos = limiteElementosCompartidos;
        this.fechaActual = fechaActual;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;
    }


    //Getter y Setter:
    public boolean isActivarSuscripcion() {return activarSuscripcion;}
    public void setActivarSuscripcion(boolean activarSuscripcion) {this.activarSuscripcion = activarSuscripcion;}

    public int getLimiteElementosTareas() {return limiteElementosTareas;}
    public void setLimiteElementosTareas(int limiteElementosTareas) {this.limiteElementosTareas = limiteElementosTareas;}

    public int getLimiteElementosRecordatorios() {return limiteElementosRecordatorios;}
    public void setLimiteElementosRecordatorios(int limiteElementosRecordatorios) {this.limiteElementosRecordatorios = limiteElementosRecordatorios;}

    public int getLimiteElementosCompartidos() {return limiteElementosCompartidos;}
    public void setLimiteElementosCompartidos(int limiteElementosCompartidos) {this.limiteElementosCompartidos = limiteElementosCompartidos;}

    public LocalDate getFechaActual() {return fechaActual;}
    public void setFechaActual(LocalDate fechaActual) {this.fechaActual = fechaActual;}

    public DateTimeFormatter getFechaSuscripcion() {return fechaSuscripcion;}
    public void setFechaSuscripcion(DateTimeFormatter fechaSuscripcion) {this.fechaSuscripcion = fechaSuscripcion;}

    public DateTimeFormatter getFechaLimiteSuscripcion() {return fechaLimiteSuscripcion;}
    public void setFechaLimiteSuscripcion(DateTimeFormatter fechaLimiteSuscripcion) {this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;}


    //Metodos Propios:
    public void activarSuscripcion() {
        if(activarSuscripcion == false){
            activarSuscripcion = true;
            System.out.println(" ");
            System.out.println("La Suscripcion ha sido activada");
        }
    }


    //Metodos Heredados:
    @Override
    public void verificarUsuario() {}
    @Override
    public void imprimirUsuario() {
        super.imprimirUsuario();
        System.out.println("En tu version actual no gozas de los beneficios completos. ");
    }
    @Override
    public void modoSuscripcion() {
        if(activarSuscripcion == false){
            System.out.println(" ");
            System.out.println("Suscripcion esta desactivada");
            System.out.println("Posees un limite de Tareas de: " + limiteElementosTareas);
            System.out.println("Posees un limite de Recordatorios de: " + limiteElementosRecordatorios);
            System.out.println("Posees un limite de Elementos Compartidos: " + limiteElementosCompartidos);
            System.out.println(" ");
        }else{
            System.out.println(" ");
            System.out.println("Suscripcion activa");
            System.out.println(" ");
        }
    }


}
