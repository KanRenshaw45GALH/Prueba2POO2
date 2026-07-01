package modeloUsuario;

import DAOs.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;

public class ListadoUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario iniciarSesion(String nombre, String password) {

        UsuarioDAO dao = new UsuarioDAO();

        return dao.buscarPorCredenciales(nombre, password);

    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuarios registrados: " + usuarios.size());
    }

    public void BuscarUsuario(String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombreCompleto().equalsIgnoreCase(nombre)) {
                System.out.println("Usuario encontrado: " + u.getNombreCompleto());
                return;
            }
        }
    }


    public Usuario buscarPorNombre(String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombreCompleto().equalsIgnoreCase(nombre)) {
                return u;
            }
        }
        return null;
    }

}
