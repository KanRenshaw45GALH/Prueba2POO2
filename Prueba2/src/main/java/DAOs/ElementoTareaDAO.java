package DAOs;
import conexionDB.Conexion;
import modeloElemento.ElementoTarea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElementoTareaDAO {

    private final ElementoDAO elementoDAO = new ElementoDAO();

    public boolean insertar(int idUsuario, ElementoTarea tarea){

        int idElemento =
                elementoDAO.insertarElementoBase(idUsuario,tarea);

        if(idElemento==-1){
            return false;
        }

        String sql = """
                INSERT INTO Elemento_Tarea
                (Id_Elemento,
                 Estado_Elemento)
                VALUES (?,?)
                """;

        try(Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1,idElemento);
            ps.setString(2,tarea.getEstado().toString());

            return ps.executeUpdate()>0;

        }catch(SQLException e){

            e.printStackTrace();
            return false;

        }

    }

}