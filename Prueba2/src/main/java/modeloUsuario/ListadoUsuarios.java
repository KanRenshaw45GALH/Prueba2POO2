package modeloUsuario;

import java.util.ArrayList;
import java.util.List;

public class ListadoUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

<<<<<<< HEAD
    public Usuario iniciarSesion(String nombre, String password){

        for (Usuario u : usuarios){
            System.out.println("----------------");
            System.out.println("Correo guardado: " + u.getNombreCompleto());
            System.out.println("Password guardado: " + u.getPassword());
            if(u.getNombreCompleto().equalsIgnoreCase(nombre)
                    && u.getPassword().equals(password)){
=======
    public Usuario iniciarSesion(String correo, String password) {
        System.out.println("Cantidad de usuarios: " + usuarios.size());
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(correo)
                    && u.getPassword().equals(password)) {
>>>>>>> 81442813e920c7c3c034b4770341d4654f45803a
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
