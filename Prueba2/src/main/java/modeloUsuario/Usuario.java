package modeloUsuario;


public abstract class Usuario implements AccionesUsuario {
    //Atributos:
    private String nombreCompleto;
    private int edad;
    private String email;
    private String password;


    //Constructor Parametrizado:
    public Usuario(String nombreCompleto, int edad, String email, String password) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.email = email;
        this.password = password;
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


    //Metodos Implementados:
    @Override
    public void verificarUsuario() {
        if (password.equals(this.password)) {
            System.out.println("El Usuario se ha verificado correctamente. ");
        } else {
            System.out.println("El Usuario no esta verificado. ");
        }
    }
    @Override
    public void imprimirUsuario() {
        System.out.println(" ");
        System.out.println("El Usuario es " + nombreCompleto);
        System.out.println("El Usuario es " + edad);
        System.out.println("El Usuario es " + email);
    }


}