package estrategia;

public class PagoBitcoin implements EstrategiaPago {

    private int idBitcoin;
    private String duiBitcoin;


    public int getIdBitcoin() {return idBitcoin;}
    public void setIdBitcoin(int idBitcoin) {this.idBitcoin = idBitcoin;}

    public String getDuiBitcoin() {return duiBitcoin;}
    public void setDuiBitcoin(String numeroBitcoin) {this.duiBitcoin = numeroBitcoin;}

    public void setDatosDui(String duiBitcoin) {
        this.duiBitcoin = duiBitcoin;
    }

    @Override
    public boolean pagar(float monto) {

        if (duiBitcoin == null || duiBitcoin.isBlank()) {
            System.out.println("Debe ingresar un DUI válido.");
            return false;
        }

        System.out.println("Pago en BITCOIN registrado");
        System.out.println(" Monto: $" + monto);
        System.out.println(" Numero DUI registrado: " + duiBitcoin);
        System.out.println(" Pago realizado correctamente. ");
        return true;
    }
}
