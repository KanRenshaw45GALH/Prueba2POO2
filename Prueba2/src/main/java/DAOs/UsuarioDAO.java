package DAOs;

import modeloUsuario.Usuario;
import conexionDB.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;

public class UsuarioDAO {

    /**
     * Inserta los datos comunes del usuario.
     * Devuelve el Id generado por SQL Server.
     */
    public int insertarUsuarioBase(Usuario usuario) {

        String sql = """
                INSERT INTO Usuario
                (Nombre_Usuario, Email_Usuario, Password_Usuario, Edad_Usuario)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNombreCompleto());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getEdad());

            int filas = ps.executeUpdate();

            if (filas > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Actualiza los datos básicos de un usuario.
     * Se recibe el ID como parámetro porque el objeto Usuario no lo almacena.
     */
    public boolean actualizarDatosBasicos(int idUsuario, Usuario usuario) {

        String sql = """
                UPDATE Usuario
                SET Nombre_Usuario = ?,
                    Email_Usuario = ?,
                    Password_Usuario = ?,
                    Edad_Usuario = ?
                WHERE Id_Usuario = ?
                """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombreCompleto());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getEdad());
            ps.setInt(5, idUsuario);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Elimina un usuario por su ID.
     */
    public boolean eliminarUsuario(int idUsuario) {

        String sql = "DELETE FROM Usuario WHERE Id_Usuario = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Verifica si un correo ya existe.
     */
    public boolean existeEmail(String email) {

        String sql = """
                SELECT COUNT(*)
                FROM Usuario
                WHERE Email_Usuario = ?
                """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Obtiene el ID de un usuario a partir de su correo.
     */
    public int obtenerIdPorEmail(String email) {

        String sql = """
                SELECT Id_Usuario
                FROM Usuario
                WHERE Email_Usuario = ?
                """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("Id_Usuario");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}