package DAOs;

import conexionDB.Conexion;
import modeloElemento.Elemento;
import catalogo.Prioridad;
import modeloElemento.ElementoRecordatorio;
import modeloElemento.ElementoTarea;
import catalogo.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ElementoDAO {


    public int insertarElementoBase(int idUsuario, Elemento elemento) {
        if (idUsuario <= 0) {
            System.out.println("Fallo idUsuario");
            return -1;
        }
        if (elemento.getPrioridad() == null) {
            System.out.println("Fallo prioridad");
            return -1;
        }
        if (elemento.getFechaCreacion() == null ||
                elemento.getFechaLimite() == null) {
            System.out.println("Fallo fechas");
            return -1;
        }
        if (elemento.getFechaLimite().isBefore(elemento.getFechaCreacion())) {
            System.out.println("Fecha límite menor");
            return -1;
        }

        System.out.println("ID Usuario: " + idUsuario);
        System.out.println("Titulo: " + elemento.getTitulo());
        System.out.println("Descripcion: " + elemento.getDescripcion());
        System.out.println("Prioridad: " + elemento.getPrioridad());
        System.out.println("Fecha Creacion: " + elemento.getFechaCreacion());
        System.out.println("Fecha Limite: " + elemento.getFechaLimite());

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
            ///
            System.out.println("Filas insertadas en Elemento = " + filas);
            ///


            if(filas > 0){
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idElemento = rs.getInt(1);
                        System.out.println("ID del Elemento generado: " + idElemento);
                        elemento.setId(idElemento);
                        return idElemento;
                    }
                }
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }



    public boolean eliminarElemento(int idElemento){
        try (Connection con = Conexion.conectar()) {
            String sqlTarea = "DELETE FROM Elemento_Tarea WHERE Id_Elemento=?";

            try (PreparedStatement ps = con.prepareStatement(sqlTarea)) {
                ps.setInt(1, idElemento);
                int filas = ps.executeUpdate();
            }
            String sqlRecordatorio = "DELETE FROM Elemento_Recordatorio WHERE Id_Elemento=?";

            try (PreparedStatement ps = con.prepareStatement(sqlRecordatorio)) {
                ps.setInt(1, idElemento);
                int filas = ps.executeUpdate();
            }
            String sqlElemento = "DELETE FROM Elemento WHERE Id_Elemento=?";

            try (PreparedStatement ps = con.prepareStatement(sqlElemento)) {
                ps.setInt(1, idElemento);
                int filas = ps.executeUpdate();
                return filas > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public List<Elemento> listarElementos(int idUsuario) {
        List<Elemento> elementos = new ArrayList<>();
        String sql = """
        SELECT *
        FROM Elemento
        WHERE Id_Usuario = ?
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    int idElemento = rs.getInt("Id_Elemento");
                    Elemento elemento = null;

                    String sqlTarea = """
                                    SELECT Estado_Elemento
                                    FROM Elemento_Tarea
                                    WHERE Id_Elemento = ?
                                    """;

                    PreparedStatement psTarea = con.prepareStatement(sqlTarea);
                    psTarea.setInt(1, idElemento);
                    ResultSet rsTarea = psTarea.executeQuery();

                    if (rsTarea.next()) {
                        ElementoTarea tarea = new ElementoTarea();
                        tarea.setEstado(Estado.valueOf(rsTarea.getString("Estado_Elemento")));
                        elemento = tarea;
                    }else{
                        String sqlRecordatorio = """
                                        SELECT Fecha_Recordatorio
                                        FROM Elemento_Recordatorio
                                        WHERE Id_Elemento = ?
                                        """;

                        PreparedStatement psRecordatorio = con.prepareStatement(sqlRecordatorio);
                        psRecordatorio.setInt(1, idElemento);

                        ResultSet rsRecordatorio = psRecordatorio.executeQuery();
                        if (rsRecordatorio.next()) {
                            ElementoRecordatorio recordatorio = new ElementoRecordatorio();
                            recordatorio.setFechaRecordatorio(rsRecordatorio.getDate("Fecha_Recordatorio").toLocalDate());
                            elemento = recordatorio;
                        }
                    }

                    if (elemento != null) {
                        elemento.setId(idElemento);
                        elemento.setTitulo(rs.getString("Titulo_Elemento"));
                        elemento.setDescripcion(rs.getString("Descripcion_Elemento"));
                        elemento.setPrioridad(Prioridad.valueOf(rs.getString("Prioridad_Elemento")));
                        elemento.setFechaCreacion(rs.getDate("Fecha_Creacion").toLocalDate());
                        elemento.setFechaLimite(rs.getDate("Fecha_Limite_Elemento").toLocalDate());
                        elementos.add(elemento);
                    }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return elementos;
    }


    public boolean actualizarElemento(Elemento elemento) {
        String sql = """
            UPDATE Elemento
            SET
                Titulo_Elemento = ?,
                Descripcion_Elemento = ?,
                Prioridad_Elemento = ?,
                Fecha_Limite_Elemento = ?
            WHERE Id_Elemento = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, elemento.getTitulo());
            ps.setString(2, elemento.getDescripcion());
            ps.setString(3, elemento.getPrioridad().toString());
            ps.setDate(4,
                    java.sql.Date.valueOf(elemento.getFechaLimite()));
            ps.setInt(5, elemento.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}