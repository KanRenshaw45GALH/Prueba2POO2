package modeloUsuario;
import modeloElemento.Elemento;
import java.time.LocalDate;
import java.util.List;


public abstract class Usuario implements AccionesUsuario {
    //Atributos:
    private String nombreCompleto;
    private int edad;
    private String email;
    private String password;
    private boolean accesoCompleto;
    private LocalDate fechaActual;
    List <Elemento> elemento;


    //Constructor Parametrizado:
    public Usuario(String nombreCompleto, int edad, String email, String password, boolean accesoCompleto, LocalDate fechaActual, Elemento elemento) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.email = email;
        this.password = password;
        this.accesoCompleto = false;
        this.fechaActual = LocalDate.now();
        this.elemento = null;
    }

    //Getter y Setter:
    public String getNombreCompleto() {return nombreCompleto;}
    public void setNombreCompleto(String nombreCompleto) {this.nombreCompleto = nombreCompleto;}

    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public boolean getAccesoCompleto() {return accesoCompleto;}
    public void setAccesoCompleto() {this.accesoCompleto = accesoCompleto;}

    public LocalDate getFechaActual() {return fechaActual;}
    public void setFechaActual(LocalDate fechaActual) {this.fechaActual = fechaActual;}

    public List<Elemento> getElemento() {return elemento;}
    public void setElemento(List<Elemento> elemento) {this.elemento = elemento;}


    //Metodos Implementados:
    @Override
    public void verificarUsuario() {
        System.out.println("\n Por favor verifique su usuario.  ");
        if (password.equals(this.password)) {
            System.out.println("El Usuario se ha verificado correctamente. ");
        } else {
            System.out.println("El Usuario no esta verificado. ");
        }
    }
    @Override
    public void imprimirUsuario() {
        System.out.println(" ");
        System.out.println("El nombre del Usuario es:  " + nombreCompleto + " quien posee el corre electrnico: " + email);
        System.out.println("La catidad de proyectos que posee el Usuario es " + "c");
    }


}