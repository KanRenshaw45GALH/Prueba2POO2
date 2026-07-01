package estrategia;

import java.time.LocalDate;
import java.util.Date;

public class PagoTarjeta implements EstrategiaPago {

    private int idTarjeta;
    private String titular;
    private String numeroTarjeta;
    private String cvv;
    private LocalDate fechaVencimiento;


    public int getIdTarjeta() {return idTarjeta;}
    public void setIdTarjeta(int idTarjeta) {this.idTarjeta = idTarjeta;}

    public String getTitular() {return titular;}
    public void setTitular(String titular) {this.titular = titular;}

    public String getNumeroTarjeta() {return numeroTarjeta;}
    public void setNumeroTarjeta(String numeroTarjeta) {this.numeroTarjeta = numeroTarjeta;}

    public String getCVV() {return cvv;}
    public void setCVV(String cvv) {this.cvv = cvv;}

    public LocalDate getFechaVencimiento() {return fechaVencimiento;}
    public void setFechaVencimiento(LocalDate fechaVencimiento) {this.fechaVencimiento = fechaVencimiento;}

    public void setDatosTarjeta(String titular, String numeroTarjeta, String cvv, LocalDate fechaVencimiento) {
        this.titular = titular;
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
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