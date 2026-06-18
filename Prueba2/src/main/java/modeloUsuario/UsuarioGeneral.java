package modeloUsuario;
import modeloElemento.Elemento;
import modeloElemento.ElementoRecordatorio;
import modeloElemento.ElementoTarea;
import java.util.HashMap;
import java.util.Map;

public class UsuarioGeneral extends Usuario {

    // Atributos
    private boolean activarSuscripcion = false;
    private Map<String, Integer> contadorElementos;
    private Map<Integer,Integer> tareasCompartidas;


    // Constructores
    public UsuarioGeneral(String nombreCompleto, int edad, String email, String password, int cantidadTareas, int cantidadRecordatorios, boolean activarSuscripcion, Map<String, Integer> contadorElementos, Map<Integer,Integer> tareasCompartidas) {
        super(nombreCompleto, edad, email, password, cantidadTareas, cantidadRecordatorios);
        this.activarSuscripcion = activarSuscripcion;
        this.tareasCompartidas = tareasCompartidas;
        this.contadorElementos = contadorElementos;
        if (!this.contadorElementos.containsKey("TAREA")) {
            this.contadorElementos.put("TAREA", 0);
        }
        if (!this.contadorElementos.containsKey("RECORDATORIO")) {
            this.contadorElementos.put("RECORDATORIO", 0);
        }
    }

    public UsuarioGeneral(){
        super();
        contadorElementos = new HashMap<>();
        contadorElementos.put("TAREA", 0);
        contadorElementos.put("RECORDATORIO", 0);
        tareasCompartidas = new HashMap<>();
    }

    // Getters y Setters
    public boolean isActivarSuscripcion() { return activarSuscripcion; }
    public void setActivarSuscripcion(boolean activarSuscripcion) { this.activarSuscripcion = activarSuscripcion; }
    public Map<String, Integer> getContadorElementos() { return contadorElementos; }
    public void setContadorElementos(Map<String, Integer> contadorElementos) {this.contadorElementos = contadorElementos;}

    // Metodos propios
    public boolean limiteTarea() {
        if(contadorElementos.get("TAREA") >= 8){
            System.out.println("Limite de TAREAS alcanzado. ");
            return false;
        }
        return true;
    }
    public boolean limiteRecordatorio() {
        if(contadorElementos.get("RECORDATORIO") >= 4){
            System.out.println("Limite de RECORDATORIOS alcanzado. ");
            return false;
        }
        return true;
    }
    public boolean compartirTarea(Elemento elemento) {
        if (!(elemento instanceof ElementoTarea)) {
            return true;
        }

        int vecesCompartida = tareasCompartidas.getOrDefault(elemento.getId(), 0);

        if (vecesCompartida >= 1) {
            System.out.println("Esta tarea ya fue compartida anteriormente.");
            return false;
        }

        tareasCompartidas.put(
                elemento.getId(),
                vecesCompartida + 1
        );
        return true;
    }


    // Metodos heredados
    @Override
    public void verificarUsuario() {
        super.verificarUsuario();
    }

    @Override
    public void crearElemento(Elemento elemento) {
        super.crearElemento(elemento);

        if (elemento instanceof ElementoTarea) {
            contadorElementos.put("TAREA", contadorElementos.get("TAREA") + 1);
        }else if(elemento instanceof ElementoRecordatorio) {
            contadorElementos.put("RECORDATORIO", contadorElementos.get("RECORDATORIO") + 1);
        }
    }

    @Override
    public void imprimirUsuario() {
        super.imprimirUsuario();
        System.out.println("Cantidad de TAREAS: " + contadorElementos.get("TAREA"));
        System.out.println("Cantidad de RECORDATORIOS: " + contadorElementos.get("RECORDATORIO"));
        System.out.println("\nEn tu version actual no gozas de los beneficios completos.");
        System.out.println("Para ello debes comprar el servicio Premium.");
    }

}
