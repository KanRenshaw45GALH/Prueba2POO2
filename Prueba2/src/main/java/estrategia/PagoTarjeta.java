package estrategia;

public class PagoTarjeta implements EstrategiaPago {

    private String numeroTarjeta;
    private String cvv;

    public void setDatosTarjeta(String numeroTarjeta, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
    }

    @Override
    public boolean pagar(float monto) {
        if (numeroTarjeta == null || numeroTarjeta.trim().isEmpty()
                || cvv == null || cvv.trim().isEmpty()) {
            System.out.println("Datos de tarjeta invalidos.");
            return false;
        }

        System.out.println("Procesando el pago en Tarjeta por $" + monto + ".");
        System.out.println("Pago realizado correctamente. (:>) _111");
        return true;
    }
}