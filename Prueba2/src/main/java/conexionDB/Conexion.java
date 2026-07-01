package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=DB_APP_ELEMENTOS;encrypt=true;trustServerCertificate=true";

    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public static Connection conectar() throws SQLException {

        System.out.println(URL);

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

        System.out.println("Conectado correctamente");

        return con;
    }

}

