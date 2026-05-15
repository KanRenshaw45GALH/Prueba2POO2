package estrategia;
import modeloElemento.Elemento;
import java.util.List;

public interface EstrategiaElemento {

    public void ejecutar(List<Elemento> elementos, Elemento elemento);
    public String getNombreEstrategia();
}
