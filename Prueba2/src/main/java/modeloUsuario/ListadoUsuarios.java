package modeloUsuario;

import java.util.ArrayList;
import java.util.List;

public class ListadoUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario iniciarSesion(String correo, String password){
        for(Usuario u : usuarios){
            if(u.getEmail().equalsIgnoreCase(correo)
                    && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;

    }


}
