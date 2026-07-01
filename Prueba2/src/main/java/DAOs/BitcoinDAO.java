package DAOs;

import conexionDB.Conexion;
import estrategia.PagoBitcoin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BitcoinDAO {

    public boolean insertar(int idUsuario, PagoBitcoin bitcoin) {

        if (idUsuario <= 0) {
            return false;
        }
        if(bitcoin == null) {
            return false;
        }
        if(bitcoin.getDuiBitcoin() == null || bitcoin.getDuiBitcoin().isBlank()) {
            return false;
        }

        String sql = "INSERT INTO Bitcoin " +
                "(Id_Usuario, DUI_Bitcoin)" +
                "VALUES (?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, bitcoin.getDuiBitcoin());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }
    }
}