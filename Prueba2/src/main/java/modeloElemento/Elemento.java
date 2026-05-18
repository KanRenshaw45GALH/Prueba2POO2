package modeloElemento;
import catalogo.Prioridad;
import modeloUsuario.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class  Elemento implements AccionesElemento {
    //Atributos:
    private int id;
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private Usuario usuario;
    private List<Usuario> colaboradores;
    private int cantidadColaboradores;

    //Constructor Prametrizado:
    Elemento(int id, String titulo, String descripcion, int cantidadColaboradores, Prioridad prioridad, LocalDate fechaCreacion, LocalDate fechaLimite, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.usuario = usuario;
        this.colaboradores = new ArrayList<>();
        this.cantidadColaboradores = cantidadColaboradores;
    }
    public Elemento() {}


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

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public int getCantidadColaboradores() {return cantidadColaboradores;}
    public void setCantidadColaboradores(int cantidadColaboradores) {this.cantidadColaboradores = cantidadColaboradores;}

    public List<Usuario> getColaboradores() {return colaboradores;}
    public void setColaboradores(List<Usuario> colaboradores) {this.colaboradores = colaboradores;}


    //Metodos Heredados:
    @Override
    public void crearElemento(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Creando nuevo Elemento. ");
        System.out.println("Introduzca el Titulo del Elemento: " );
        setTitulo(sc.nextLine());
        System.out.println("Introduzca la Descripcion del Elemento: " );
        setDescripcion(sc.nextLine());
        System.out.println("Introduzca la Prioridad del Elemento ingresando el numeral: \n 1. ALTA. \n 2. MEDIA. \n 3. BAJA. " );
        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                setPrioridad(Prioridad.ALTA);
                break;
            case 2:
                setPrioridad(Prioridad.MEDIA);
                break;
            case 3:
                setPrioridad(Prioridad.BAJA);
                break;
            default:
                System.out.println("Opcion no valida. ");
                setPrioridad(Prioridad.BAJA);
        }
        System.out.println("Introduzca la fecha limite del Elemento como el siguiente ejemplo (DD/MM/YYYY): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setFechaLimite(LocalDate.parse(sc.nextLine(), formatter));
        System.out.println("Elemento creado correctamente.");

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