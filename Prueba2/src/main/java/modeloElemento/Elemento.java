package modeloElemento;
import catalogo.Prioridad;
import java.time.format.DateTimeFormatter;


public abstract class Elemento implements AccionesElemento {
    //Atributos:
    private int id;
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;
    private DateTimeFormatter fechaCreacion = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter fechaLimite = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int cantidadColaboradores;


    //Constructor Prametrizado:
    Elemento(int id, String titulo, String descripcion, int cantidadColaboradores, Prioridad prioridad, DateTimeFormatter fechaCreacion, DateTimeFormatter fechaLimite) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.cantidadColaboradores = cantidadColaboradores;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
    }


    //Getter y Setter:
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getTitulo() {return titulo;}
    public void setTitulo() {this.titulo = titulo;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Prioridad getPrioridad() {return prioridad;}
    public void setPrioridad() {this.prioridad = prioridad;}

    public DateTimeFormatter getFechaCreacion() {return fechaCreacion;}
    public void setFechaCreacion(DateTimeFormatter fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public DateTimeFormatter getFechaLimite() {return fechaLimite;}
    public void setFechaLimite(DateTimeFormatter fechaLimite) {this.fechaLimite = fechaLimite;}

    public int getCantidadColaboradores() {return cantidadColaboradores;}
    public void setCantidadColaboradores(int cantidadColaboradores) {this.cantidadColaboradores = cantidadColaboradores;}


    //Metodos Heredaddos:
    @Override
    public void crearElemento(){};
    @Override
    public void compartirElemento(){};
    @Override
    public void imprimirElementos(){
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Cantidad Colaboradores: " + cantidadColaboradores);
        System.out.println("Fecha Creacion: " + fechaCreacion);
    }










}