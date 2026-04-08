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
    //Objeto Uusuario General1 y Tarea1
        UsuarioGeneral usuariogen1 = new UsuarioGeneral("Rossaline Duran", 23, "Rosaduran@gmail.com", "Rossy23", false, 8, 6, 0, LocalDate.now(), DateTimeFormatter.ofPattern("31/01/2026"), DateTimeFormatter.ofPattern("31/01/2026"));
        System.out.println("====================================================");
        usuariogen1.imprimirUsuario();
        usuariogen1.verificarUsuario();
        usuariogen1.modoSuscripcion();
        usuariogen1.activarSuscripcion();
        usuariogen1.modoSuscripcion();

        ElementoTarea tarea1 = new ElementoTarea(1, "Proyecto de Arquitectura", "Entrega de modelo a escala de torre Cuscatlan de fiedos y pegamento", 0, Prioridad.ALTA, Estado.EN_PROGRESO, DateTimeFormatter.ofPattern("03/01/2026"), DateTimeFormatter.ofPattern("16/03/2026"), usuariogen1);
        tarea1.crearElemento();
        tarea1.imprimirElementos();

        ElementoRecordatorio recordatorio1 = new ElementoRecordatorio(1, "Alarma de Proyecto", "Recordatorio de proyecto sobre modelo a escala", 0, Prioridad.ALTA, DateTimeFormatter.ofPattern("03/01/2026"), DateTimeFormatter.ofPattern("16/03/2026"), DateTimeFormatter.ofPattern("27/02/2026"), usuariogen1);
        recordatorio1.crearElemento();
        recordatorio1.imprimirElementos();
        recordatorio1.activarAlerta();
        System.out.println("====================================================");




    //Objeto Uusuario General1 y Tarea1
        UsuarioPremium usuariogen2 = new UsuarioPremium("Diego Perez", 32, "Dogoperron@amail.com", "DOGO001", 20.00f, true, true, true, LocalDate.now(), DateTimeFormatter.ofPattern("31/01/2026"), DateTimeFormatter.ofPattern("31/01/2026"));
        System.out.println("====================================================");
        usuariogen1.imprimirUsuario();
        usuariogen1.verificarUsuario();
        usuariogen1.modoSuscripcion();
        usuariogen1.activarSuscripcion();
        usuariogen1.modoSuscripcion();

        ElementoTarea tarea2 = new ElementoTarea(1, "Proyecto de Arquitectura", "Entrega de proyecto de moviles sobre App de delivery", 2, Prioridad.MEDIA, Estado.EN_PROGRESO, DateTimeFormatter.ofPattern("03/01/2026"), DateTimeFormatter.ofPattern("16/03/2026"),usuariogen2);
        tarea1.crearElemento();
        tarea1.imprimirElementos();

        ElementoRecordatorio recordatorio2 = new ElementoRecordatorio(1, "Alarma de Proyecto", "Recordatorio de proyecto sobre App", 2, Prioridad.ALTA, DateTimeFormatter.ofPattern("03/01/2026"), DateTimeFormatter.ofPattern("27/03/2026"), DateTimeFormatter.ofPattern("27/02/2026"),usuariogen1);
        recordatorio2.crearElemento();
        recordatorio2.imprimirElementos();
        recordatorio2.activarAlerta();
        System.out.println("====================================================");


    }
}
