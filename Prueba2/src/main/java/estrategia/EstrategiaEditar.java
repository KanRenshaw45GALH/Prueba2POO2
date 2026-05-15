package estrategia;
import modeloElemento.Elemento;

import java.util.List;

public class EstrategiaEditar implements EstrategiaElemento{

    @Override
    public void ejecutar(List<Elemento> elementos, Elemento elemento){

    }

    @Override
    public String getNombreEstrategia(){
        return "EDITAR";
    }
}
