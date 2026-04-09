package Main;
import catalogo.Estado;
import catalogo.Prioridad;
import modeloElemento.Elemento;
import modeloElemento.ElementoRecordatorio;
import modeloElemento.ElementoTarea;
import modeloUsuario.Usuario;
import modeloUsuario.UsuarioGeneral;
import modeloUsuario.UsuarioPremium;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        //Atributo Propio de formatedo de fecha:
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Objetos: Usuario General1, Tarea1 y Recordatorio1.
        UsuarioGeneral usuariogen1 = new UsuarioGeneral("Rossaline Duran", 23, "Rosaduran@gmail.com", "Rossy23", false, 7, 3, 0, LocalDate.now(), LocalDate.parse("31/01/2026", fmt), LocalDate.parse("31/01/2026", fmt));
        System.out.println("====================================================");
        usuariogen1.imprimirUsuario();
        usuariogen1.verificarUsuario();
        usuariogen1.modoSuscripcion();
        usuariogen1.activarSuscripcion();
        usuariogen1.modoSuscripcion();

        ElementoTarea tarea1 = new ElementoTarea(1, "Proyecto de Arquitectura", "Entrega de modelo a escala de torre Cuscatlan de fiedos y pegamento", 0, Prioridad.ALTA, Estado.EN_PROGRESO, LocalDate.parse("03/01/2026", fmt), LocalDate.parse("16/03/2026", fmt), usuariogen1);
        tarea1.crearElemento();
        tarea1.imprimirElementos();

        ElementoRecordatorio recordatorio1 = new ElementoRecordatorio(1, "Alarma de Proyecto", "Recordatorio de proyecto sobre modelo a escala", 0, Prioridad.ALTA, LocalDate.parse("03/01/2026", fmt), LocalDate.parse("16/03/2026", fmt), LocalDate.parse("27/02/2026", fmt), usuariogen1);
        recordatorio1.crearElemento();
        recordatorio1.imprimirElementos();
        recordatorio1.activarAlerta();
        System.out.println(" ");

        usuariogen1.conteoTarea();
        usuariogen1.conteoRecordatorio();
        usuariogen1.conteoCompartido();
        System.out.println("====================================================");





        //Objetos: Usuario Premium1, Tarea2 y Recordatorio2
        UsuarioPremium usuarioprem1 = new UsuarioPremium("Diego Perez", 32, "Dogoperron@amail.com", "DOGO001", 20.00f, true, true, true, LocalDate.now(), LocalDate.parse("31/01/2026", fmt), LocalDate.parse("31/01/2026", fmt));
        System.out.println("====================================================");
        usuarioprem1.imprimirUsuario();
        usuarioprem1.verificarUsuario();
        usuarioprem1.modoSuscripcion();
        usuarioprem1.PagarSuscripcion();
        usuarioprem1.cancelarSuscripcion();
        usuarioprem1.modoSuscripcion();

        ElementoTarea tarea2 = new ElementoTarea(1, "Proyecto de Arquitectura", "Entrega de proyecto de moviles sobre App de delivery", 2, Prioridad.MEDIA, Estado.EN_PROGRESO, LocalDate.parse("03/01/2026", fmt), LocalDate.parse("16/03/2026", fmt),usuarioprem1);
        tarea2.crearElemento();
        tarea2.imprimirElementos();

        ElementoRecordatorio recordatorio2 = new ElementoRecordatorio(1, "Alarma de Proyecto", "Recordatorio de proyecto sobre App", 2, Prioridad.ALTA, LocalDate.parse("03/01/2026", fmt), LocalDate.parse("27/02/2026", fmt),LocalDate.parse("27/02/2026", fmt),usuarioprem1);
        recordatorio2.crearElemento();
        recordatorio2.imprimirElementos();
        recordatorio2.activarAlerta();
        System.out.println("====================================================");


    }
}
