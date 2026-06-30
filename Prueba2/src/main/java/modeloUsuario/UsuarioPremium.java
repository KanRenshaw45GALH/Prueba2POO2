package modeloUsuario;
import estrategia.EstrategiaPago;
import estrategia.PagoTarjeta;
import estrategia.PagoBitcoin;
import modeloElemento.Elemento;
import java.time.LocalDate;
import java.util.Scanner;

public class UsuarioPremium extends Usuario {

    // Atributos
    private float pagarSuscripcion = 4.99f;
    private boolean compartirElemento = true;
    private LocalDate fechaSuscripcion;
    private LocalDate fechaLimiteSuscripcion;
    private EstrategiaPago estrategiaPago;

    // Constructores
    public UsuarioPremium(String nombreCompleto, int edad, String email, String password,
                          int cantidadTareas, int cantidadRecordatorios, float pagarSuscripcion,
                          boolean compartirElemento, LocalDate fechaSuscripcion,
                          LocalDate fechaLimiteSuscripcion) {
        super(nombreCompleto, edad, email, password, cantidadTareas, cantidadRecordatorios);
        this.pagarSuscripcion = pagarSuscripcion;
        this.compartirElemento = compartirElemento;
        this.fechaSuscripcion = fechaSuscripcion;
        this.fechaLimiteSuscripcion = fechaLimiteSuscripcion;
    }


    // Getters y Setters
    public float getPagarSuscripcion() { return pagarSuscripcion; }
    public void setPagarSuscripcion(float pagarSuscripcion) { this.pagarSuscripcion = pagarSuscripcion; }

    public boolean isCompartirElemento() { return compartirElemento; }
    public void setCompartirElemento(boolean compartirElemento) { this.compartirElemento = compartirElemento; }

    public LocalDate getFechaSuscripcion() { return fechaSuscripcion; }
    public void setFechaSuscripcion(LocalDate fechaSuscripcion) { this.fechaSuscripcion = fechaSuscripcion; }

    public LocalDate getFechaLimiteSuscripcion() { return fechaLimiteSuscripcion; }
    public void setFechaLimiteSuscripcion(LocalDate fechaLimiteSuscripcion) { this.fechaLimiteSuscripcion = fechaLimiteSuscripcion; }

    public EstrategiaPago getEstrategiaPago() { return estrategiaPago; }
    public void setEstrategiaPago(EstrategiaPago estrategiaPago) { this.estrategiaPago = estrategiaPago; }


    // Metodos propios
    public float pagarSuscripcion() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nPara mantener los beneficios debe pagar la suscripcion.");
        System.out.println("Monto: $" + pagarSuscripcion + " Antees de la fecha limite: " + getFechaLimiteSuscripcion());
        System.out.println(" Escoga su metodo de Pago: [1] Tarjeta     [2] Efectivo");

        int opcion = Integer.parseInt(sc.nextLine());
        switch (opcion) {
            case 1:
                setEstrategiaPago(new PagoBitcoin());
                break;
            case 2:
                setEstrategiaPago(new PagoTarjeta());
                break;
            default:
                System.out.println("Metodo de pago invalido.");
                return 0;
        }

        estrategiaPago.pagar(pagarSuscripcion);
        System.out.println("Suscripcion pagada correctamente.");
        return pagarSuscripcion;
    }


    // Metodos heredados.
    @Override
    public void crearElemento(Elemento elemento) {
        super.crearElemento(elemento);
    }

    @Override
    public void imprimirUsuario() {
        super.imprimirUsuario();
        System.out.println("Usted posee un servicio Premium.");
        System.out.println("Suscripcion actual: $" + getPagarSuscripcion());
        System.out.println("Fecha de Suscripcion: " + getFechaSuscripcion());
        System.out.println("Fecha Limite: " + getFechaLimiteSuscripcion());
    }
}
