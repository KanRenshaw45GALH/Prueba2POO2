package conexionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Prueba {

    public static void main(String[] args) {

        try {

            Connection con = Conexion.conectar();
            System.out.println("Conectado correctamente.");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}