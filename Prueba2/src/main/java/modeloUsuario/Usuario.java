package modeloUsuario;
import catalogo.Prioridad;
import modeloElemento.Elemento;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Usuario implements AccionesUsuario {

    // Atributos
    private String nombreCompleto;
    private int edad;
    private String email;
    private String password;
    private boolean accesoCompleto;
    private LocalDate fechaActual;
    private List<Elemento> elemento;

    // Constructor parametrizado
    public Usuario(String nombreCompleto, int edad, String email, String password,
                   int cantidadTareas, int cantidadRecordatorios) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.email = email;
        this.password = password;
        this.accesoCompleto = false;
        this.fechaActual = LocalDate.now();
        this.elemento = new ArrayList<>();
    }

    public Usuario() {
        this.elemento = new ArrayList<>();
        this.fechaActual = LocalDate.now();
    }

    // Getters y Setters
    public String getNombreCompleto() { return nombreCompleto; }
    // CORRECCIÓN: el setter ahora asigna el parámetro s correctamente
    public void setNombreCompleto(String s) { this.nombreCompleto = s; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean getAccesoCompleto() { return accesoCompleto; }
    public void setAccesoCompleto(boolean accesoCompleto) { this.accesoCompleto = accesoCompleto; }

    public LocalDate getFechaActual() { return fechaActual; }
    public void setFechaActual(LocalDate fechaActual) { this.fechaActual = fechaActual; }

    public List<Elemento> getElemento() { return elemento; }
    public void setElemento(List<Elemento> elemento) { this.elemento = elemento; }



    //Metodos propios:
    public void guardarElemento(Elemento elemento) {
        boolean yaExiste = this.elemento.stream().anyMatch(e -> e.getId() == elemento.getId());
        if (yaExiste) {
            System.out.println("El elemento ya existe en la lista.");
            return;
        } else {
            this.elemento.add(elemento);
            System.out.println("Elemento guardado correctamente.");
            System.out.println("Elementos guardados: " + this.elemento.size());
        }
    }

    public void eliminarElemento() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID del elemento a Eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        boolean eliminado = this.elemento.removeIf(e -> e.getId() == id);
        if (eliminado) {
            System.out.println("Elemento eliminado correctamente.");
        } else {
            System.out.println("El elemento no fue encontrado en la lista.");
        }
    }

    public void editarElemento() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del elemento a editar: ");
        int id = Integer.parseInt(sc.nextLine());

        Elemento elementoEncontrado = null;
        for (Elemento e : this.elemento) {
            if (e.getId() == id) {
                elementoEncontrado = e;
                break;
            }
        }
        if (elementoEncontrado == null) {
            System.out.println("No existe un elemento con ese ID.");
            return;
        }
                try {
                    System.out.println("Elemento a encontrado: " + elementoEncontrado.getTitulo());
                    System.out.println("Ingrese que opcion desea editar: ");
                    System.out.println("1. [TITULO], 2.[DESCRIPCION], 3.[PRIORIDAD], 4.[FECHA ELEMENTO]");
                    System.out.print("Opcion seleccionada: ");

                    int opcion = Integer.parseInt(sc.nextLine().trim());

                    if (opcion == 1) {
                        System.out.print("Nuevo titulo: ");
                        elementoEncontrado.setTitulo(sc.nextLine());
                        System.out.println("El titulo fue modificado correctamente.");

                    } else if (opcion == 2) {
                        System.out.print("Nueva descripcion: ");
                        elementoEncontrado.setDescripcion(sc.nextLine());
                        System.out.println("La descripcion fue modificada correctamente.");

                    } else if (opcion == 3) {
                        System.out.println("Nueva prioridad: 1=ALTA, 2=MEDIA, 3=BAJA");
                        System.out.print("Ingrese la opcion: ");
                        int prioridad = Integer.parseInt(sc.nextLine());
                        switch (prioridad) {
                            case 1:
                                elementoEncontrado.setPrioridad(Prioridad.ALTA);
                                break;
                            case 2:
                                elementoEncontrado.setPrioridad(Prioridad.MEDIA);
                                break;
                            case 3:
                                elementoEncontrado.setPrioridad(Prioridad.BAJA);
                                break;
                            default:
                                System.out.println("Opcion no valida.");
                        }
                        System.out.println("Opcion no valida.");
                    } else if (opcion == 4) {
                        System.out.print("Ingrese la fecha: ");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        elementoEncontrado.setFechaLimite(LocalDate.parse(sc.nextLine().trim(), formatter));
                    }
                    System.out.println("La prioridad fue modificada correctamente.");
                    System.out.println("La edicion del elemento fue finalizada con exito.");
                } catch (NumberFormatException e) {
                    System.out.println("El valor ingresado no es un numero valido.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
    }

    public synchronized void compartirElemento(List<Usuario> usuarioList) {
        Scanner sc = new Scanner(System.in);
        Elemento elementoEncontrado = null;
        Usuario usuarioEncontrado = null;

        if (this.elemento.isEmpty()) {
            System.out.println("No tienes elementos para compartir.");
            return;
        }

        System.out.println("\n  TUS ELEMENTOS DISPONIBLES");
        for (int i = 0; i < this.elemento.size(); i++) {
            Elemento e = this.elemento.get(i);
            System.out.println("  [ID " + e.getId() + "] " + e.getTitulo());
        }

        System.out.print("Introduzca el ID del Elemento: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Elemento e : this.elemento) {
            if (e.getId() == id) {
                elementoEncontrado = e;
                break;
            }
        }
        if (elementoEncontrado == null) {
            System.out.println("El Elemento no existe.");
            return;
        }

        System.out.print("Introduzca el Email del Usuario a enviar el elemento: ");
        String emailUsuario = sc.nextLine();

        for (Usuario u : usuarioList) {
            if (u.getEmail().equalsIgnoreCase(emailUsuario)) {
                usuarioEncontrado = u;
                break;
            }
        }
        if (usuarioEncontrado == null) {
            System.out.println("El Usuario no existe.");
            return;
        }

        // Verificar que el destino no sea el mismo dueño del elemento
        if (elementoEncontrado.getUsuario() != null &&
                elementoEncontrado.getUsuario().getEmail().equalsIgnoreCase(usuarioEncontrado.getEmail())) {
            System.out.println("No puedes compartir un elemento contigo mismo.");
            return;
        }

        // Verificar que el elemento no ha sido compartido previamente con este usuario
        final Usuario destinoFinal = usuarioEncontrado;
        boolean yaCompartido = elementoEncontrado.getColaboradores().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(destinoFinal.getEmail()));

        if (yaCompartido) {
            System.out.println("El elemento ya fue compartido con " + usuarioEncontrado.getNombreCompleto() + ".");
            return;
        }

        usuarioEncontrado.getElemento().add(elementoEncontrado);
        elementoEncontrado.getColaboradores().add(usuarioEncontrado);
        elementoEncontrado.setCantidadColaboradores(elementoEncontrado.getCantidadColaboradores() + 1);
        System.out.println("El Elemento fue compartido exitosamente con " + usuarioEncontrado.getNombreCompleto() + ".");
    }


    // Metodos heredados
    @Override
    public void verificarUsuario() {
        System.out.println("\nPor favor verifique su usuario.");
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Ingrese su Nombre completo: ");
            setNombreCompleto(sc.nextLine());
            System.out.print("Ingrese su Email: ");
            setEmail(sc.nextLine());

            // CORRECCIÓN: se guarda la password ingresada en variable separada antes de comparar
            System.out.print("Ingrese su Password: ");
            String passwordIngresada = sc.nextLine();
            if (passwordIngresada.equals(getPassword())) {
                System.out.println("El usuario se encuentra verificado.");
            } else {
                System.out.println("El usuario NO se encuentra verificado. Password incorrecto.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error durante la verificacion.");
        }
    }

    @Override
    public void crearElemento(Elemento elemento) {
        this.elemento.add(elemento);
    }

    @Override
    public void imprimirUsuario() {
        System.out.println(" ");
        // CORRECCIÓN: typos corregidos y se usa elemento.size() en lugar de "c"
        System.out.println("El nombre del Usuario es: " + nombreCompleto + " quien posee el correo electrónico: " + email);
        System.out.println("La cantidad de elementos que posee el Usuario es: " + elemento.size());
    }

}


