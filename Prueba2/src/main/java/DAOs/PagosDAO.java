package DAOs;

import conexionDB.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PagosDAO {

    public boolean insertar(int idUsuario,
                            Integer idTarjeta,
                            double monto,
                            String metodoPago,
                            LocalDate fechaPago) {

        if (idUsuario <= 0) {
            return false;
        }
        if (monto <= 0) {
            return false;
        }
        if (fechaPago == null) {
            return false;
        }
        if (metodoPago == null ||
                (!metodoPago.equals("TARJETA")
                        && !metodoPago.equals("BITCOIN"))) {
            return false;
        }

        String sql = """
                INSERT INTO Pagos
                (
                    Id_Usuario,
                    Id_Tarjeta,
                    Monto,
                    Metodo_Pago,
                    Fecha_Pago
                )
                VALUES (?,?,?,?,?)
                """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            if (idTarjeta == null) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setInt(2, idTarjeta);
            }

            ps.setDouble(3, monto);
            ps.setString(4, metodoPago);
            ps.setDate(5, Date.valueOf(fechaPago));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}