package estrategia;

public class PagoBitcoin implements EstrategiaPago {

    private String numeroBitcoin;

    public void setDatosDui(String numeroDUI) {
        this.numeroBitcoin = numeroDUI;
    }

    @Override
    public boolean pagar(float monto) {
        System.out.println("Pago en BITCOIN registrado");
        System.out.println(" Monto: $" + monto);
        System.out.println(" Numero Tarjeta: " + numeroBitcoin);
        return true;
    }
}
