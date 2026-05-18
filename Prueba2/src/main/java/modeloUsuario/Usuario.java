package modeloUsuario;
import modeloElemento.Elemento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
    public Usuario(String nombreCompleto, int edad, String email, String password, int cantidadTareas, int cantidadRecordatorios) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.email = email;
        this.password = password;
        this.accesoCompleto = false;
        this.fechaActual = LocalDate.now();
        this.elemento= new ArrayList<Elemento>();
    }
    public Usuario() {}

    //Getter y Setter:
    public String getNombreCompleto() {return nombreCompleto;}
    public void setNombreCompleto(String s) {this.nombreCompleto = nombreCompleto;}

    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public boolean getAccesoCompleto() {return accesoCompleto;}
    public void setAccesoCompleto(boolean accesoCompleto) {this.accesoCompleto = accesoCompleto;}

    public LocalDate getFechaActual() {return fechaActual;}
    public void setFechaActual(LocalDate fechaActual) {this.fechaActual = fechaActual;}

    public List<Elemento> getElemento() {return elemento;}
    public void setElemento(List<Elemento> elemento) {this.elemento = elemento;}


    //Metodos Propios:
    public void ListarElementos() {
        for(Elemento elemento: this.elemento){
            elemento.imprimirElementos();
        }
    }

    public void EliminarElemento(Elemento elemento) {
        this.elemento.remove(elemento);
    }

    public void compartirElemento(List<Usuario> usuarioList){
        Scanner sc = new Scanner(System.in);
        Elemento elementoEncontrado = null;
        Usuario usuarioEncontrado = null;

        System.out.println("Introduzca el nombre del Elemento: ");
        String nombreElemento = sc.nextLine();

        for(Elemento elemento : this.elemento){
            if(elemento.getTitulo().equalsIgnoreCase(nombreElemento)){
                elementoEncontrado = elemento;
                break;
            }
        }
        if(elementoEncontrado == null){
            System.out.println("El Elemento no existe");
            return;
        }

        System.out.println("Introduzca el Email del Usuario a Enviar el elemento: ");
        String emailUsuario = sc.nextLine();

        for(Usuario usuario : usuarioList){
            if(usuario.getEmail().equalsIgnoreCase(emailUsuario)) {
                usuarioEncontrado = usuario;
                break;
            }
        }
        if(usuarioEncontrado == null){
            System.out.println("El Usuario no existe");
            return;
        }

        Elemento finalElemento = elementoEncontrado;
        Usuario finalUsuario = usuarioEncontrado;

        Thread hilo1 = new Thread(() -> accesoConcurrente(finalElemento, finalUsuario, "Hilo-1"));
        Thread hilo2 = new Thread(() -> accesoConcurrente(finalElemento, finalUsuario, "Hilo-2"));
        Thread hilo3 = new Thread(() -> accesoConcurrente(finalElemento, finalUsuario, "Hilo-3"));

        hilo1.start();
        hilo2.start();
        hilo3.start();
    };
    private synchronized void accesoConcurrente(Elemento elemento, Usuario usuario, String hilo){

        System.out.println(hilo + " intentando compartir elemento...");

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            System.out.println("Error en el hilo.");
        }

        usuario.getElemento().add(elemento);

        elemento.getColaboradores().add(usuario);

        elemento.setCantidadColaboradores(
                elemento.getCantidadColaboradores() + 1
        );

        System.out.println(hilo + " compartio el elemento exitosamente a "
                + usuario.getNombreCompleto());
    }


    //Metodos Heredados:
    @Override
    public void verificarUsuario() {
        System.out.println("\n Por favor verifique su usuario.  ");
        try {
            System.out.println("Ingrese su Nombre completo: ");
            Scanner usuarioIngreso = new Scanner(System.in);
            setNombreCompleto(usuarioIngreso.nextLine());
            try {
                System.out.println("Ingrese su Email: ");
                setEmail(usuarioIngreso.nextLine());
                try {
                    System.out.println("Ingrese su Password: ");
                    setPassword(usuarioIngreso.nextLine());
                    if(getPassword().equals(getPassword())) {
                        System.out.println("El usuario se encuentra verificado.");
                    }else{
                        System.out.println("El usuario no se encuentra verificado.");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("\n Password equivocado o no encontrado.\n Por favor reintroduzca el Password correcto. \n");
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("\n Email equivocado o no encontrado.\n Por favor reintroduzca el Email correcto. \n");
            }
        }catch (Exception e) {
                e.printStackTrace();
                System.out.println("\n Nombre equivocado o no encontrado.\n Por favor reintroduzca el Nombre correcto. \n");
        }

    }
    @Override
    public void crearElemento(Elemento elemento) {
        this.elemento.add(elemento);
    };
    @Override
    public void imprimirUsuario() {
        System.out.println(" ");
        System.out.println("El nombre del Usuario es:  " + nombreCompleto + " quien posee el corre electrnico: " + email);
        System.out.println("La catidad de proyectos que posee el Usuario es " + "c");
    }


}