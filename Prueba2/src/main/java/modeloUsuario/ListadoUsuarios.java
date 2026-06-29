package modeloUsuario;

import java.util.ArrayList;
import java.util.List;

public class ListadoUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario iniciarSesion(String correo, String password) {
        System.out.println("Cantidad de usuarios: " + usuarios.size());
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(correo)
                    && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuarios registrados: " + usuarios.size());
    }

    // Nuevo método requerido por CompartirElementoControlador
    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
