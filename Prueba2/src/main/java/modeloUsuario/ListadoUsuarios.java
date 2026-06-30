package modeloUsuario;

import java.util.ArrayList;
import java.util.List;

public class ListadoUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario iniciarSesion(String nombre, String password) {

        for (Usuario u : usuarios) {
            System.out.println("----------------");
            System.out.println("Correo guardado: " + u.getNombreCompleto());
            System.out.println("Password guardado: " + u.getPassword());
            if (u.getNombreCompleto().equalsIgnoreCase(nombre)
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
