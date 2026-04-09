package modeloUsuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class UsuarioGeneral extends Usuario{
    //Atributos:
    private boolean activarSuscripcion = false;
    final private int limiteElementosTareas = 8;
    final private int limiteElementosRecordatorios = 4;
    final private int limiteElementosCompartidos = 1;
    private int contadorElementoTareas = 0;
    private int contadorElementoRecordatorios = 0;
    private int contadorElementoCompartidos = 0;
    private LocalDate fechaActual = LocalDate.now();
    private LocalDate fechaSuscripcion;
    private LocalDate fechaLimiteSuscripcion;


    //Constructor Parametrizado:
    public UsuarioGeneral(String nombreCompleto, int edad, String email, String password, boolean activarSuscripcion, int contadorElementoTareas, int contadorElementoRecordatorios, int contadorElementoCompartidos, LocalDate fechaActual, LocalDate fechaSuscripcion, LocalDate fechaLimiteSuscripcion) {
        super(nombreCompleto,edad,email,password);
        this.activarSuscripcion = activarSuscripcion;
        this.contadorElementoTareas = contadorElementoTareas;
        this.contadorElementoRecordatorios = contadorElementoRecordatorios;
        this.contadorElementoCompartidos = contadorElementoCompartidos;
        this.fechaActual = fechaActual;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;
    }


    //Getter y Setter:
    public boolean isActivarSuscripcion() {return activarSuscripcion;}
    public void setActivarSuscripcion(boolean activarSuscripcion) {this.activarSuscripcion = activarSuscripcion;}

    public LocalDate getFechaActual() {return fechaActual;}
    public void setFechaActual(LocalDate fechaActual) {this.fechaActual = fechaActual;}

    public LocalDate getFechaSuscripcion() {return fechaSuscripcion;}
    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {this.fechaSuscripcion = fechaSuscripcion;}

    public LocalDate getFechaLimiteSuscripcion() {return fechaLimiteSuscripcion;}
    public void setFechaLimiteSuscripcion(LocalDate fechaLimiteSuscripcion) {this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;}

    public int getContadorElementoTareas() {
        return contadorElementoTareas;
    }

    public void setContadorElementoTareas(int contadorElementoTareas) {
        this.contadorElementoTareas = contadorElementoTareas;
    }

    public int getContadorElementoRecordatorios() {
        return contadorElementoRecordatorios;
    }

    public void setContadorElementoRecordatorios(int contadorElementoRecordatorios) {
        this.contadorElementoRecordatorios = contadorElementoRecordatorios;
    }

    public int getContadorElementoCompartidos() {
        return contadorElementoCompartidos;
    }

    public void setContadorElementoCompartidos(int contadorElementoCompartidos) {
        this.contadorElementoCompartidos = contadorElementoCompartidos;
    }

    //Metodos Propios:
    public void activarSuscripcion() {
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
    public boolean conteoCompartido() {
        if (getContadorElementoCompartidos() < limiteElementosCompartidos) {
            contadorElementoCompartidos++;
            System.out.println("Elemento compartido agregado. (" + getContadorElementoCompartidos() + "/" + limiteElementosCompartidos + ")");
            return true;
        } else {
            System.out.println("Límite de compartidos alcanzado: " + limiteElementosCompartidos);
            return false;
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
