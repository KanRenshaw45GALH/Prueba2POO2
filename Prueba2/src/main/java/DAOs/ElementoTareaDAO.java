package DAOs;

import conexionDB.Conexion;
import modeloElemento.ElementoTarea;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElementoTareaDAO {

    private final ElementoDAO elementoDAO = new ElementoDAO();

    public boolean insertar(int idUsuario, ElementoTarea tarea){

        System.out.println("Entrando a ElementoTareaDAO");
        if (idUsuario <= 0) {
            System.out.println("idUsuario incorrecto");
            return false;
        }
        if (tarea.getEstado() == null) {
            System.out.println("Estado nulo");
            return false;
        }
        int idElemento = elementoDAO.insertarElementoBase(idUsuario,tarea);
        System.out.println("idElemento recibido = " + idElemento);

        if (idElemento == -1) {
            System.out.println("ElementoDAO devolvió -1");
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

            int filas = ps.executeUpdate();
            System.out.println("Filas insertadas en Elemento_Tarea = " + filas);

            return filas > 0;

        }catch(SQLException e){

            e.printStackTrace();
            return false;

        }

    }

}