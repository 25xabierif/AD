package conexiones;

import java.io.ObjectInputFilter.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.biblioteca.ConfigLoader;

public class MySQLConnection implements DBConnection{

    /* Sin archivo aplicacion.properties */

    /* private static final String URL = "jdbc:mysql://localhost:3306/bibliotecaXabierI";
    private static final String USER = "usuario";
    private static final String PASSWORD = "usuario123"; */

    @Override
    public Connection getConnection() {
        /* Con archivo application.properties */
        String URL = ConfigLoader.get("mysql.url");
        String USER = ConfigLoader.get("mysql.root");
        String PASSWORD = ConfigLoader.get("mysql.rootPass");

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MySQL: "+e.getMessage());
            return null;
        }
    }

    public Connection getConnectionName(String dbName) {
        /* Con archivo application.properties */
        String URL = ConfigLoader.get("mysql.url");
        String USER = ConfigLoader.get("mysql.user");
        String PASSWORD = ConfigLoader.get("mysql.password");

        try {
            return DriverManager.getConnection(URL+dbName, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MySQL: "+e.getMessage());
            return null;
        }
    }
    
}
