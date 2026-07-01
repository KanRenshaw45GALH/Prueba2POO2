package DAOs;

import conexionDB.Conexion;
import estrategia.PagoTarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class TarjetaDAO {

    public boolean insertar(int idUsuario, PagoTarjeta tarjeta) {

        if (idUsuario <= 0) {
            return false;
        }
        if (tarjeta == null) {
            return false;
        }
        if (tarjeta.getTitular() == null ||
                tarjeta.getTitular().isBlank()) {
            return false;
        }
        if (tarjeta.getNumeroTarjeta() == null ||
                tarjeta.getNumeroTarjeta().length() != 16) {
            return false;
        }
        if (tarjeta.getCVV() == null ||
                tarjeta.getCVV().length() != 3) {
            return false;
        }
        if (tarjeta.getFechaVencimiento() == null) {
            return false;
        }
        if (tarjeta.getFechaVencimiento().isBefore(LocalDate.now())) {
            return false;
        }


        String sql = """
                INSERT INTO Tarjeta
            (Id_Usuario,
             Titular_Tarjeta,
             Numero_Tarjeta,
             CVV_Tarjeta,
             Fecha_Vencimiento)
            VALUES (?,?,?,?,?)
            """;

        try(Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1,idUsuario);
                ps .setString(2,tarjeta.getTitular());
                ps .setString(3,tarjeta.getNumeroTarjeta());
                ps .setString(4,tarjeta.getCVV());
                ps.setDate(5, java.sql.Date.valueOf(tarjeta.getFechaVencimiento()));

                return ps.executeUpdate() > 0;

        }catch(SQLException e){
        e.printStackTrace();
        return false;

    }
        }
}
