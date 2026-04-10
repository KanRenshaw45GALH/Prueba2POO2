package modeloElemento;
import catalogo.Prioridad;
import modeloUsuario.Usuario;
import java.time.LocalDate;


public abstract class  Elemento implements AccionesElemento {
    //Atributos:
    private int id;
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private int cantidadColaboradores;
    private Usuario usuario;


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
    public void setTitulo() {this.titulo = titulo;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Prioridad getPrioridad() {return prioridad;}
    public void setPrioridad() {this.prioridad = prioridad;}

    public LocalDate getFechaCreacion() {return fechaCreacion;}
    public void setFechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;}

    public LocalDate getFechaLimite() {return fechaLimite;}
    public void setFechaLimite(LocalDate fechaLimite) {this.fechaLimite = fechaLimite;}

    public int getCantidadColaboradores() {return cantidadColaboradores;}
    public void setCantidadColaboradores(int cantidadColaboradores) {this.cantidadColaboradores = cantidadColaboradores;}

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

    }

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
        System.out.println("usuario creador: " + usuario.getNombreCompleto());;
    }










}