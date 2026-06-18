package modeloElemento;

import catalogo.Prioridad;
import modeloUsuario.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ElementoRecordatorio extends Elemento {

    // Atributos
    //  nombre en minúscula siguiendo convención Java
    private boolean alerta;
    private LocalDate fechaRecordatorio;
    private LocalDate fechaActual;

    // Constructor parametrizado
    public ElementoRecordatorio(int id, String titulo, String descripcion, int cantidadColaboradores,
                                Prioridad prioridad, LocalDate fechaCreacion, LocalDate fechaLimite,
                                LocalDate fechaRecordatorio, Usuario usuario) {
        super(id, titulo, descripcion, cantidadColaboradores, prioridad, fechaCreacion, fechaLimite, usuario);
        this.fechaRecordatorio = fechaRecordatorio;
        // fechaActual debe ser la fecha de hoy, no fechaCreacion
        this.fechaActual = LocalDate.now();
    }

    public ElementoRecordatorio() {
        super();
        this.fechaActual = LocalDate.now();
    }

    // Getters y Setters
    public boolean isAlerta() { return alerta; }
    public void setAlerta(boolean alerta) { this.alerta = alerta; }

    public LocalDate getFechaRecordatorio() { return fechaRecordatorio; }
    public void setFechaRecordatorio(LocalDate fechaRecordatorio) { this.fechaRecordatorio = fechaRecordatorio; }

    public LocalDate getFechaActual() { return fechaActual; }
    public void setFechaActual(LocalDate fechaActual) { this.fechaActual = fechaActual; }

    // Metodos propios
    public void activarAlerta() {
        // CORRECCIÓN: compara fechaActual con fechaRecordatorio para saber si ya llegó el momento
        if (fechaRecordatorio != null && !fechaActual.isBefore(fechaRecordatorio)) {
            alerta = true;
            System.out.println("\nAlerta activada! El recordatorio ha llegado a su fecha.");
        } else {
            alerta = false;
            System.out.println("\nAlerta desactivada. Aun no es la fecha del recordatorio.");
        }
    }

    // Metodos heredados
    @Override
    public void crearElemento() {
        super.crearElemento();
        // La fechaRecordatorio se puede establecer igual que la fechaLimite por defecto
        this.fechaRecordatorio = getFechaLimite();
    }

    @Override
    public boolean esPendiente() {
        return true;
    }
    @Override
    public void imprimirPendiente(int numero) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("  [%d] RECORDATORIO | %s | vence %s%n",
                numero, getTitulo(),
                getFechaLimite() != null ? getFechaLimite().format(fmt) : "sin fecha");
    }
    @Override
    public void editar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("  [1] Titulo  [2] Descripcion  [3] Prioridad  [4] Fecha recordatorio");
        System.out.print("  -> ");
        int op = Integer.parseInt(sc.nextLine().trim());
        if (op == 4) {
            // lógica de fecha recordatorio
            activarAlerta(); // re-evalúa la alerta con la nueva fecha
        } else {
            super.editar(); // delega los campos comunes al padre
        }
    }
    @Override
    public void imprimirElementos() {
        super.imprimirElementos();
        System.out.println("Fecha de activacion de Recordatorio: " + fechaRecordatorio);
        System.out.println("Fecha Actual: " + fechaActual);
        System.out.println("Alerta activa: " + alerta);
    }
}
