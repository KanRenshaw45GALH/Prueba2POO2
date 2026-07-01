package DAOs;

import conexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CompartidosDAO {

    public boolean insertar(int idUsuarioOrigen, int idUsuarioDestino, int idElemento, LocalDate fechaCompartido) {

                if (idUsuarioOrigen <= 0) {
                    return false;
                }
                if (idUsuarioDestino <= 0) {
                    return false;
                }
                if (idElemento <= 0) {
                    return false;
                }
                if (fechaCompartido == null) {
                    return false;
                }

                String sql = """
                        INSERT INTO Elementos_Compartidos
                        (
                            Id_Usuario_Origen,
                            Id_Usuario_Destino,
                            Id_Elemento,
                            Fecha_Compartido
                        )
                        VALUES (?,?,?,?)
                        """;

                try (Connection con = Conexion.conectar();
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setInt(1, idUsuarioOrigen);
                    ps.setInt(2, idUsuarioDestino);
                    ps.setInt(3, idElemento);
                    ps.setDate(4, java.sql.Date.valueOf(fechaCompartido));

                    return ps.executeUpdate() > 0;

                } catch (SQLException e) {

                    e.printStackTrace();
                    return false;

                }
        }

}