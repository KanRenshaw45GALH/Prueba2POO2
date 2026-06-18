package estrategia;
import java.util.Scanner;

public class PagoEfectivo implements EstrategiaPago {

    @Override
    public void pagar(float monto) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pago en efectivo registrado. ");
        System.out.println("Monto recibido: $" + sc.nextFloat());

    }

}

