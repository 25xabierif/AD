package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.alumnado.ConfigLoader;

public class MySQLConnection implements DBConnection{

    /* Sin archivo aplicacion.properties */

    /* private static final String URL = "jdbc:mysql://localhost:5432/";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin123"; */

    @Override
    public Connection getConnectionServer() {
        /* Con archivo application.properties */
        String URL = ConfigLoader.get("mysql.url");
        String USER = ConfigLoader.get("mysql.user");
        String PASSWORD = ConfigLoader.get("mysql.password");

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MySQL: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Connection getConnection() {
        /* Con archivo application.properties */
        String URL = ConfigLoader.get("mysql.url");
        String BD = ConfigLoader.get("mysql.bd");
        String USER = ConfigLoader.get("mysql.user");
        String PASSWORD = ConfigLoader.get("mysql.password");

        try {
            return DriverManager.getConnection(URL+BD, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MySQL: "+e.getMessage());
            return null;
        }
    }
    
}
