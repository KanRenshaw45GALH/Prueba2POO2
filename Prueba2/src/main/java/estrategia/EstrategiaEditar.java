package estrategia;
import modeloElemento.Elemento;
import catalogo.Prioridad;
import java.util.Scanner;

import java.util.List;

public class EstrategiaEditar implements EstrategiaElemento, Runnable{

    private final Elemento elemento;
    private static final Scanner sc = new Scanner(System.in);

    //Constructor parametrizado
    public EstrategiaEditar(Elemento elemento) {
        this.elemento = elemento;
    }

    // Metodo run, ejecuta el hilo Editar
    @Override
    public void run(){
        try {
            System.out.println("ESTRATEGIA EDITAR ELEMENTO");
            System.out.println("Elemento a editar: " + elemento.getTitulo());
            System.out.println("Ingrese qué opción desea editar: ");
            System.out.println("1. Editar título del elemento");
            System.out.println("2. Editar descripción del elemento");
            System.out.println("3. Editar prioridad del elemento");
            System.out.println("Opción seleccionada: ");

            // Lee la opcion seleccionada
            int opcion = Integer.parseInt(sc.nextLine().trim());

            // Opcion 1: Editar titulo del elemento
            if(opcion == 1){
                System.out.println("Nuevo titulo: ");
                String nuevoTitulo = sc.nextLine().trim();
                if(nuevoTitulo.isEmpty()){
                    throw new IllegalArgumentException("El titulo no puede estar vacío");
                }
                elemento.setTitulo(nuevoTitulo);
                System.out.println("El título fue modificado correctamente");
                // Opcion 2: Editar descripcion del elemento
            } else if(opcion == 2) {
                System.out.println("Nueva descripcion: ");
                String nuevaDescripcion = sc.nextLine().trim();
                elemento.setDescripcion(nuevaDescripcion);
                System.out.println("La descripcion fue modificada correctamente");
                // Opcion 3: Editar prioridad del elemento
            } else if(opcion == 3){
                System.out.println("Nueva prioridad: 1=ALTA, 2=MEDIA, 3=BAJA");
                System.out.println("Ingrese la opcion: ");
                int prioridad = Integer.parseInt(sc.nextLine().trim());

                // Se asigna y modifica la prioridad segun la opcion seleccionada
                if(prioridad == 1){
                    elemento.setPrioridad(Prioridad.ALTA);
                } else if(prioridad == 2) {
                    elemento.setPrioridad(Prioridad.MEDIA);
                } else{
                    elemento.setPrioridad(Prioridad.BAJA);
                }
                System.out.println("La prioridad fue modificada correctamente");
                // seleccion invalida
            } else{
                throw new IllegalArgumentException("La opcion ingresada no es valida");
            }
            System.out.println("La edicion del elemento fue finalizada con exito");

            // Atrapa error de numero invalido
        } catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println("El numero ingresado no es valida");
            //Atrapa cualquier otro error
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error inesperado" + e.getMessage());
        }
    }

    //Recibe la lista y el elemento, lanza el hilo Editar
    @Override
    public void ejecutar(List<Elemento> elementos, Elemento elemento){

        Thread hiloEditar = new Thread(this);
        // Se inicia el hilo Editar
        hiloEditar.start();

        try{
            hiloEditar.join();
        } catch(InterruptedException e){
            e.printStackTrace();
            System.out.println("El hilo Editar fue interrumpido");
        }
    }

    // Retorna el nombre de la estrategia usada
    @Override
    public String getNombreEstrategia(){
        return "EDITAR";
    }
}
