package DAOs;

import conexionDB.Conexion;
import estrategia.PagoTarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class TarjetaDAO {

    public int insertar(int idUsuario, PagoTarjeta tarjeta) {

        if (idUsuario <= 0) {
            return -1;
        }
        if (tarjeta == null) {
            return -1;
        }
        if (tarjeta.getTitular() == null || tarjeta.getTitular().isBlank()) {
            return -1;
        }
        if (tarjeta.getNumeroTarjeta() == null || tarjeta.getNumeroTarjeta().length() != 16) {
            return -1;
        }
        if (tarjeta.getCVV() == null || tarjeta.getCVV().length() != 3) {
            return -1;
        }
        if (tarjeta.getFechaVencimiento() == null) {
            return -1;
        }
        if (tarjeta.getFechaVencimiento().isBefore(LocalDate.now())) {
            return -1;
        }

        String sql = """
                INSERT INTO Tarjeta
                            (Id_Usuario,
             Titular_Tarjeta,
             Numero_Tarjeta,
             CVV_Tarjeta,
             Fecha_Vencimiento)
                            VALUES
                (?,?, ?,?,?)
            """;

        try(Connection con = Conexion.conectar();
            PreparedStatement ps = con. prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, idUsuario);
                ps .setString(2,tarjeta.getTitular());
                ps .setString(3,tarjeta.getNumeroTarjeta());
                ps .setString(4,tarjeta.getCVV());
                ps.setDate(5, java.sql.Date.valueOf(tarjeta.getFechaVencimiento()));

                int filas = ps.executeUpdate();
                if (filas > 0){
                    ResultSet rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            int idTarjeta = rs.getInt(1);
                            tarjeta. setIdTarjeta(idTarjeta);
                            return idTarjeta;
                        }
                }
        }catch(SQLException e){
        e.printStackTrace();
        }

        return -1;
    }
}
