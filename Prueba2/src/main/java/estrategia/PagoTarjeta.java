package estrategia;
import java.util.Scanner;

public class PagoTarjeta implements EstrategiaPago {

    @Override
    public void pagar(float monto){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el umero de la Tarjeta(**** **** **** ****): ");
        String numeroTarjeta = sc.nextLine();

        System.out.println("Ingrese el CVV de la Tarjeta: ");
        double cvvTarjeta = sc.nextDouble();

        System.out.println("Ingrese el Monto del Tarjeta: ");
        double montoTarjeta = sc.nextDouble();

        System.out.println("Procesando el pago en Tarjeta. ");
        System.out.println("Pago realizado correctamente. (:>) _111");


    }
}
