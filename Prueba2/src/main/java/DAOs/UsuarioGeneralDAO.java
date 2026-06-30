package DAOs;
import conexionDB.Conexion;
import modeloUsuario.UsuarioGeneral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioGeneralDAO {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean insertar(UsuarioGeneral usuario) {

        int idGenerado = usuarioDAO.insertarUsuarioBase(usuario);

        if (idGenerado == -1) {
            return false;
        }

        String sql = """
                INSERT INTO UsuarioGeneral(Id_Usuario)
                VALUES(?)
                """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idGenerado);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}