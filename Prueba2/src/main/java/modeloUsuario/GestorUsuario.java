package modeloUsuario;
import modeloElemento.Elemento;
import modeloElemento.ElementoRecordatorio;
import modeloElemento.ElementoTarea;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * GestorUsuario: maneja la conversión entre UsuarioGeneral y UsuarioPremium.
 * Resuelve el problema original donde modoSuscripcion() creaba objetos descartados.
 * Ahora el cambio de tipo se maneja aquí, copiando todos los datos del usuario actual.
 */
public class GestorUsuario {

    /**
     * Convierte un UsuarioGeneral a UsuarioPremium copiando todos sus datos.
     */
    public static UsuarioPremium convertirAPremium(UsuarioGeneral general) {
        UsuarioPremium premium = new UsuarioPremium(
                general.getNombreCompleto(),
                general.getEdad(),
                general.getEmail(),
                general.getPassword(),
                0, 0,
                4.99f,
                true,
                LocalDate.now(),
                LocalDate.now().plusMonths(1)
        );
        // Copiar elementos existentes
        premium.setElemento(new ArrayList<> (general.getElemento()));
        premium.setAccesoCompleto(true);
        return premium;
    }

    /**
     * Convierte un UsuarioPremium a UsuarioGeneral copiando todos sus datos.
     */
    public static UsuarioGeneral convertirAGeneral(UsuarioPremium premium) {
        Map<String, Integer> contador = new HashMap<>();
        contador.put("TAREA", 0);
        contador.put("RECORDATORIO", 0);
        Map<Integer,Integer> compartidas = new HashMap<>();

        UsuarioGeneral general = new UsuarioGeneral(
                premium.getNombreCompleto(),
                premium.getEdad(),
                premium.getEmail(),
                premium.getPassword(),
                0,
                0,
                false,
                contador,
                compartidas
        );

        // Copiar elementos existentes
        general.setElemento(new ArrayList<>(premium.getElemento()));
        int tareas = 0;
        int recordatorios = 0;
        for (Elemento e : premium.getElemento()) {
            if (e instanceof ElementoTarea) {
                tareas++;
            }
            else if (e instanceof ElementoRecordatorio) {
                recordatorios++;
            }
        }
        general.getContadorElementos().put("TAREA", tareas);
        general.getContadorElementos().put("RECORDATORIO", recordatorios);
        general.setAccesoCompleto(false);
        return general;
    }
}
