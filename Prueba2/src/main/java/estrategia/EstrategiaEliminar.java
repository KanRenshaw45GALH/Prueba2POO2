package estrategia;
import modeloElemento.Elemento;

import java.util.List;

public class EstrategiaEliminar implements EstrategiaElemento{

    @Override
    public void ejecutar(List<Elemento> elementos, Elemento elemento){
        System.out.println("ESTRATEGIA ELIMINAR ELEMENTO");
        System.out.println("Elemento a eliminar: " + elemento.getTitulo());
        elementos.remove(elemento);
    }

    @Override
    public String getNombreEstrategia(){
        return "Eliminar";
    }
}
