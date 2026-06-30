package estrategia;

public class PagoEfectivo implements EstrategiaPago {

    @Override
    public boolean pagar(float monto) {
        System.out.println("Pago en efectivo registrado");
        System.out.println(" Monto: $" + monto);
        return true;
    }
}