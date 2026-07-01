package DAOs;

import conexionDB.Conexion;
import modeloUsuario.UsuarioPremium;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class UsuarioPremiumDAO {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean insertar(UsuarioPremium usuario) {

        int idGenerado = usuarioDAO.insertarUsuarioBase(usuario);
        if (idGenerado == -1) {
            return false;
        }
        usuario.setIdUsuario(idGenerado);

        if (usuario.getFechaSuscripcion() == null ||
                usuario.getFechaLimiteSuscripcion() == null) {
            return false;
        }
        if (usuario.getFechaLimiteSuscripcion()
                .isBefore(usuario.getFechaSuscripcion())) {
            return false;
        }

        String sql = """
                INSERT INTO UsuarioPremium
                (Id_Usuario, Fecha_Suscripcion, Fecha_Limite)
                VALUES (?, ?, ?)
                """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idGenerado);
            ps.setDate(2, java.sql.Date.valueOf(usuario.getFechaSuscripcion()));
            ps.setDate(3, java.sql.Date.valueOf(usuario.getFechaLimiteSuscripcion()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean convertirAPremium(int idUsuario,
                                     LocalDate fechaSuscripcion,
                                     LocalDate fechaLimite) {

        String sql = """
            INSERT INTO UsuarioPremium
            (Id_Usuario, Fecha_Suscripcion, Fecha_Limite)
            VALUES (?,?,?)
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setDate(2, java.sql.Date.valueOf(String.valueOf(fechaSuscripcion)));
            ps.setDate(3, java.sql.Date.valueOf(String.valueOf(fechaLimite)));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelarSuscripcion(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        String sql = """
            DELETE FROM UsuarioPremium
            WHERE Id_Usuario = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}