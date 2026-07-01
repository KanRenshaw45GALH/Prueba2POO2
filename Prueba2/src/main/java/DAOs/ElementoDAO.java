package DAOs;
import conexionDB.Conexion;
import modeloElemento.Elemento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ElementoDAO {


    public int insertarElementoBase(int idUsuario, Elemento elemento) {

        String sql = """
                INSERT INTO Elemento
                (Id_Usuario,
                 Titulo_Elemento,
                 Descripcion_Elemento,
                 Prioridad_Elemento,
                 Fecha_Creacion,
                 Fecha_Limite_Elemento)
                VALUES (?,?,?,?,?,?)
                """;

        try(Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, elemento.getTitulo());
            ps.setString(3, elemento.getDescripcion());
            ps.setString(4, elemento.getPrioridad().toString());
            ps.setDate(5, java.sql.Date.valueOf(elemento.getFechaCreacion()));
            ps.setDate(6, java.sql.Date.valueOf(elemento.getFechaLimite()));

            int filas = ps.executeUpdate();

            if(filas > 0){

                ResultSet rs = ps.getGeneratedKeys();

                if(rs.next()){
                    return rs.getInt(1);
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    public boolean eliminarElemento(int idElemento){

        String sql =
                "DELETE FROM Elemento WHERE Id_Elemento=?";

        try(Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,idElemento);

            return ps.executeUpdate()>0;

        }catch(SQLException e){

            e.printStackTrace();

        }

        return false;
    }

}