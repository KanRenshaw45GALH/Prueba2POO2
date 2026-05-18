package ui;
import catalogo.Estado;
import catalogo.Prioridad;
import modeloElemento.ElementoTarea;
import modeloElemento.ElementoRecordatorio;
import modeloElemento.Elemento;
import modeloUsuario.UsuarioGeneral;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntradaDatos {

    private final Scanner sc = new Scanner(System.in);
    private UsuarioGeneral usuario = null;
    private int nextId = 1;
    private static final String LIN = "─────────────────────────────────────";

    // ── ARRANQUE ──────────────────────────────────────────────

    public void iniciar() {
        System.out.println("  RECORDATORIO DE TAREAS");
        System.out.println(LIN);

        boolean salir = false;
        while (!salir) {
            System.out.println("\n  [ 1 ] Acceder   [ 2 ] Salir");
            switch (leerInt()) {
                case 1 -> acceder();
                case 2 -> { System.out.println("  Hasta luego!"); salir = true; }
                default -> System.out.println("  Opcion no valida.");
            }
        }
        sc.close();
    }

    // ── ACCESO ────────────────────────────────────────────────

    private void acceder() {
        if (usuario == null) {
            System.out.println("\n  No hay usuario registrado.");
            System.out.println("  [ 1 ] Registrarse   [ 2 ] Volver");
            if (leerInt() == 1) registrar();
        } else {
            login();
        }
    }

    private void registrar() {
        System.out.println("\n  NUEVO USUARIO\n" + LIN);
        System.out.print("  Nombre    : "); String nombre = sc.nextLine().trim();

        // try-catch con nextInt() para edad
        int edad = 0;
        while (true) {
            System.out.print("  Edad      : ");
            try {
                edad = sc.nextInt();
                sc.nextLine();
                if (edad <= 0) {
                    System.out.println("  La edad debe ser un numero positivo. Intentalo de nuevo.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("  Ingresa solo numeros. Intentalo de nuevo.");
                sc.nextLine();
            }
        }

        System.out.print("  Correo    : ");
        String email = sc.nextLine().trim();
        while (email.isEmpty()) {
            System.out.println("  El correo no puede estar vacio.");
            System.out.print("  Correo    : ");
            email = sc.nextLine().trim();
        }

        System.out.print("  Contrasena: ");
        String password = sc.nextLine().trim();
        while (password.isEmpty()) {
            System.out.println("  La contrasena no puede estar vacia.");
            System.out.print("  Contrasena: ");
            password = sc.nextLine().trim();
        }

        usuario = new UsuarioGeneral();

        System.out.println("  Bienvenido/a, " + nombre + "!");
        menuPrincipal();
    }

    private void login() {
        System.out.println("\n  INICIAR SESION\n" + LIN);
        System.out.print("  Correo    : "); String email    = sc.nextLine().trim();
        System.out.print("  Contrasena: "); String password = sc.nextLine().trim();

        if (email.equals(usuario.getEmail()) && password.equals(usuario.getPassword())) {
            System.out.println("  Hola de nuevo, " + usuario.getNombreCompleto() + "!");
            menuPrincipal();
        } else {
            System.out.println("  Credenciales incorrectas.");
        }
    }

    // ── MENU PRINCIPAL ────────────────────────────────────────

    private void menuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n" + LIN);
            System.out.println("  MENU — " + usuario.getNombreCompleto());
            System.out.println(LIN);
            System.out.println("  [ 1 ] Agregar tarea");
            System.out.println("  [ 2 ] Agregar recordatorio");
            System.out.println("  [ 3 ] Ver pendientes");
            System.out.println("  [ 4 ] Completar tarea");
            System.out.println("  [ 5 ] Eliminar elemento");
            System.out.println("  [ 6 ] Ver todos");
            System.out.println("  [ 7 ] Verificar usuario");
            System.out.println("  [ 8 ] Cerrar sesion");
            System.out.println(LIN);
            System.out.print("  -> ");

            switch (leerInt()) {
                case 1 -> agregarTarea();
                case 2 -> agregarRecordatorio();
                case 3 -> verPendientes();
                case 4 -> completar();
                case 5 -> eliminar();
                case 6 -> verTodos();
                case 7 -> verificar();
                case 8 -> { System.out.println("  Sesion cerrada."); salir = true; }
                default -> System.out.println("  Opcion no valida.");
            }
        }
    }

    // ── AGREGAR ───────────────────────────────────────────────

    private void agregarTarea() {
        if (!usuario.conteoTarea()) return;

        System.out.println("\n  AGREGAR TAREA");

        // Validar titulo no vacio
        String titulo = "";
        while (titulo.isEmpty()) {
            System.out.print("  Titulo      : ");
            titulo = sc.nextLine().trim();
            if (titulo.isEmpty()) System.out.println("  El titulo no puede estar vacio.");
        }

        // Validar descripcion no vacia
        String descripcion = "";
        while (descripcion.isEmpty()) {
            System.out.print("  Descripcion : ");
            descripcion = sc.nextLine().trim();
            if (descripcion.isEmpty()) System.out.println("  La descripcion no puede estar vacia.");
        }

        // leerFecha() ya tiene su propio try-catch interno
        LocalDate fechaLimite = leerFecha();
        Prioridad prioridad   = leerPrioridad();
        Estado estado         = leerEstado();

        ElementoTarea tarea = new ElementoTarea(
                nextId++, titulo, descripcion,
                0, prioridad, estado,
                LocalDate.now(), fechaLimite, usuario
        );

        agregarALista(tarea);
        tarea.crearElemento();
    }

    private void agregarRecordatorio() {
        if (!usuario.conteoRecordatorio()) return;

        System.out.println("\n  AGREGAR RECORDATORIO");

        // Validar titulo no vacio
        String titulo = "";
        while (titulo.isEmpty()) {
            System.out.print("  Titulo      : ");
            titulo = sc.nextLine().trim();
            if (titulo.isEmpty()) System.out.println("  El titulo no puede estar vacio.");
        }

        // Validar descripcion no vacia
        String descripcion = "";
        while (descripcion.isEmpty()) {
            System.out.print("  Descripcion : ");
            descripcion = sc.nextLine().trim();
            if (descripcion.isEmpty()) System.out.println("  La descripcion no puede estar vacia.");
        }

        // leerFecha() ya tiene su propio try-catch interno
        System.out.print("  Fecha limite       : "); LocalDate fechaLimite      = leerFecha();
        System.out.print("  Fecha recordatorio : "); LocalDate fechaRecordatorio = leerFecha();
        Prioridad prioridad = leerPrioridad();

        ElementoRecordatorio rec = new ElementoRecordatorio(
                nextId++, titulo, descripcion,
                0, prioridad,
                LocalDate.now(), fechaLimite, fechaRecordatorio, usuario
        );

        agregarALista(rec);
        rec.crearElemento();
        rec.activarAlerta();
    }

    // ── VER / COMPLETAR / ELIMINAR ────────────────────────────

    private void verPendientes() {
        System.out.println("\n  PENDIENTES\n" + LIN);
        List<Elemento> lista = usuario.getElemento();
        if (lista == null || lista.isEmpty()) { System.out.println("  Sin elementos."); return; }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean hay = false;
        for (int i = 0; i < lista.size(); i++) {
            Elemento e = lista.get(i);
            if (e instanceof ElementoTarea t && t.getEstado() != Estado.COMPLETADO && t.getEstado() != Estado.CANCELADA) {
                System.out.printf("  [%d] TAREA        | %s | %s | %s%n",
                        i + 1, t.getTitulo(), t.getEstado(), t.getPrioridad());
                hay = true;
            } else if (e instanceof ElementoRecordatorio r) {
                System.out.printf("  [%d] RECORDATORIO | %s | vence %s%n",
                        i + 1, r.getTitulo(), r.getFechaLimite().format(fmt));
                hay = true;
            }
        }
        if (!hay) System.out.println("  Sin pendientes.");
    }

    private void completar() {
        listar();
        System.out.print("  Numero a completar (0 cancela): ");
        int idx = leerInt() - 1;
        List<Elemento> lista = usuario.getElemento();
        if (lista == null || idx < 0 || idx >= lista.size()) { System.out.println("  Cancelado."); return; }

        Elemento e = lista.get(idx);
        if (e instanceof ElementoTarea t) {
            if (t.getEstado() == Estado.COMPLETADO) { System.out.println("  Ya estaba completada."); return; }
            t.setEstado(Estado.COMPLETADO);
            System.out.println("  Completada: " + t.getTitulo());
        } else {
            System.out.println("  Solo se pueden completar tareas.");
        }
    }

    private void eliminar() {
        listar();
        System.out.print("  Numero a eliminar (0 cancela): ");
        int idx = leerInt() - 1;
        List<Elemento> lista = usuario.getElemento();
        if (lista == null || idx < 0 || idx >= lista.size()) { System.out.println("  Cancelado."); return; }

        // Loop de confirmacion: solo acepta exactamente "s" o "n"
        while (true) {
            System.out.print("  Confirmar? (s/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            if (resp.equals("s")) {
                System.out.println("  Eliminado: " + lista.remove(idx).getTitulo());
                break;
            } else if (resp.equals("n")) {
                System.out.println("  Cancelado.");
                break;
            } else {
                System.out.println("  Respuesta invalida. Escribe s o n.");
            }
        }
    }

    private void verTodos() {
        System.out.println("\n  TODOS LOS ELEMENTOS\n" + LIN);
        List<Elemento> lista = usuario.getElemento();
        if (lista == null || lista.isEmpty()) { System.out.println("  Sin elementos."); return; }
        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%n  [%d] ", i + 1);
            lista.get(i).imprimirElementos();
        }
    }

    private void verificar() {
        System.out.print("\n  Contrasena para verificar: ");
        if (sc.nextLine().trim().equals(usuario.getPassword())) {
            usuario.verificarUsuario();
        } else {
            System.out.println("  Contrasena incorrecta.");
        }
    }

    // ── HELPERS ───────────────────────────────────────────────

    private void agregarALista(Elemento e) {
        List<Elemento> lista = usuario.getElemento();
        if (lista == null) lista = new ArrayList<>();
        lista.add(e);
        usuario.setElemento(lista);
    }

    private void listar() {
        List<Elemento> lista = usuario.getElemento();
        if (lista == null || lista.isEmpty()) { System.out.println("  Sin elementos."); return; }
        for (int i = 0; i < lista.size(); i++) {
            Elemento e = lista.get(i);
            String tipo  = (e instanceof ElementoTarea) ? "T" : "R";
            String estado = (e instanceof ElementoTarea t) ? t.getEstado().toString() : "---";
            System.out.printf("  [%d] [%s] %s — %s%n", i + 1, tipo, e.getTitulo(), estado);
        }
    }

    private Prioridad leerPrioridad() {
        System.out.println("  Prioridad [ 1 ] Alta  [ 2 ] Media  [ 3 ] Baja");
        System.out.print("  -> ");
        return switch (leerInt()) { case 1 -> Prioridad.ALTA; case 2 -> Prioridad.MEDIA; default -> Prioridad.BAJA; };
    }

    private Estado leerEstado() {
        System.out.println("  Estado [ 1 ] Pendiente  [ 2 ] En progreso  [ 3 ] Cancelada");
        System.out.print("  -> ");
        return switch (leerInt()) { case 2 -> Estado.EN_PROGRESO; case 3 -> Estado.CANCELADA; default -> Estado.PENDIENTE; };
    }

    private int leerInt() {
        int numero = -1;
        try {
            numero = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
        }
        return numero;
    }

    // try-catch con nextInt() para dia, mes y anio por separado
    private LocalDate leerFecha() {
        while (true) {
            try {
                System.out.print("  Fecha (dd/MM/yyyy): ");
                String entrada = sc.nextLine().trim();
                String[] partes = entrada.split("/");

                int dia  = Integer.parseInt(partes[0]);
                int mes  = Integer.parseInt(partes[1]);
                int anio = Integer.parseInt(partes[2]);

                LocalDate fecha = LocalDate.of(anio, mes, dia);

                if (fecha.isBefore(LocalDate.now())) {
                    System.out.println("  La fecha debe ser hoy o futura. Intentalo de nuevo.");
                } else {
                    return fecha;
                }
            } catch (NumberFormatException e) {
                System.out.println("  Ingresa solo numeros. Intentalo de nuevo.");
            } catch (Exception e) {
                System.out.println("  Formato invalido. Usa dd/MM/yyyy. Intentalo de nuevo.");
            }
        }
    }
}