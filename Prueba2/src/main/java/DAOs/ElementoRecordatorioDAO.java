package DAOs;

import conexionDB.Conexion;
import modeloElemento.ElementoRecordatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElementoRecordatorioDAO {

    private final ElementoDAO elementoDAO = new ElementoDAO();

    public boolean insertar(int idUsuario, ElementoRecordatorio recordatorio){
        if (idUsuario <= 0) {
            return false;
        }
        if (recordatorio.getFechaRecordatorio() == null) {
            return false;
        }
        if (recordatorio.getFechaRecordatorio().isAfter(recordatorio.getFechaLimite())) {
            return false;
        }
        int idElemento = elementoDAO.insertarElementoBase(idUsuario, recordatorio);
        if(idElemento==-1){
            return false;
        }

        String sql = """
                INSERT INTO Elemento_Recordatorio
                (Id_Elemento,
                 Fecha_Recordatorio)
                VALUES (?,?)
                """;

        try(Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,idElemento);

            ps.setDate(2,
                    java.sql.Date.valueOf(
                            recordatorio.getFechaRecordatorio()));

            return ps.executeUpdate()>0;

        }catch(SQLException e){

            e.printStackTrace();
            return false;

        }
    }



    public boolean actualizar(ElementoRecordatorio recordatorio) {
        ElementoDAO elementoDAO = new ElementoDAO();
        if (!elementoDAO.actualizarElemento(recordatorio)) {
            return false;
        }

        String sql = """
            UPDATE Elemento_Recordatorio
            SET Fecha_Recordatorio = ?
            WHERE Id_Elemento = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(recordatorio.getFechaRecordatorio()));
            ps.setInt(2, recordatorio.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
