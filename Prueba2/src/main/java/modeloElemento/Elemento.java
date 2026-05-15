package modeloElemento;
import catalogo.Prioridad;
import modeloUsuario.Usuario;
import java.time.LocalDate;
import java.util.Scanner;


public abstract class  Elemento implements AccionesElemento {
    //Atributos:
    private int id;
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private int cantidadColaboradores;
    Usuario usuario;


    //Constructor Prametrizado:
    Elemento(int id, String titulo, String descripcion, int cantidadColaboradores, Prioridad prioridad, LocalDate fechaCreacion, LocalDate fechaLimite, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.cantidadColaboradores = cantidadColaboradores;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.usuario = usuario;
    }


    //Getter y Setter:
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Prioridad getPrioridad() {return prioridad;}
    public void setPrioridad(Prioridad prioridad) {this.prioridad = prioridad;}

    public LocalDate getFechaCreacion() {return fechaCreacion;}
    public void setFechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public LocalDate getFechaLimite() {return fechaLimite;}
    public void setFechaLimite(LocalDate fechaLimite) {this.fechaLimite = fechaLimite;}

    public int getCantidadColaboradores() {return cantidadColaboradores;}
    public void setCantidadColaboradores(int cantidadColaboradores) {this.cantidadColaboradores = cantidadColaboradores;}

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}


    //Metodos Heredaddos:

    public void crearElemento(){
        System.out.println("Creando nuevo Elemento. ");
        System.out.println("Introduzca el nombre del Elemento: " ); setTitulo(titulo);
        System.out.println("Introduzca el descripcion del Elemento: " ); setDescripcion(descripcion);
        System.out.println("Introduzca el prioridad del Elemento: " );  setPrioridad(prioridad);
        System.out.println("Introduzca la fecha limite del Elemento: "); setFechaLimite(LocalDate. parse(fechaLimite.toString()));
    };
    @Override
    public synchronized void compartirElemento(){
            System.out.println("Introduzca el nombre del Elemento: ");
        try{
            setTitulo(titulo);
            if (titulo.equals(getTitulo())) {
                System.out.println("Introduzca el Email del Usuario a quien compartir: ");
                setUsuario(usuario);
                System.out.println("Espere a la carga de Datos... ");
                System.out.println("El Elemento fue compartido con el Usuario: " + getUsuario() + " Correctamente!!! ");
            } else {
                System.out.println("El nombre del Elemento no existe. Pruebe otro Nombre: ");
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("El Elemento no existe. Pruebe otro Nombre: ");
        }
    };
    @Override
    public void imprimirElementos(){
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Cantidad Colaboradores: " + cantidadColaboradores);
        System.out.println("Nombre de Colaboradores: " + getUsuario().getNombreCompleto());
        System.out.println("Fecha Creacion: " + fechaCreacion);
        System.out.println("usuario creador: " + usuario.getNombreCompleto());;
    }


}