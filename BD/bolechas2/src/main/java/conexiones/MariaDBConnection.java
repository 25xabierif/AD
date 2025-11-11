package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import bolechas.ConfigLoader;

public class MariaDBConnection implements DBConnection{

    /* Sin archivo aplicacion.properties */

    /* private static final String URL = "jdbc:mariadb://localhost:3307/bolechas";
    private static final String USER = "usuario";
    private static final String PASSWORD = "usuario123"; */

    @Override
    public Connection getConnection() {
        /* Con archivo application.properties */
        String URL = ConfigLoader.get("mariadb.url");
        String USER = ConfigLoader.get("mariadb.root");
        String PASSWORD = ConfigLoader.get("mariadb.rootPass");

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MariaDB: "+e.getMessage());
            return null;
        }
    }

    public Connection getConnectionName(String dbName) {
        /* Con archivo application.properties */
        String URL = ConfigLoader.get("mariadb.url");
        String USER = ConfigLoader.get("mariadb.user");
        String PASSWORD = ConfigLoader.get("mariadb.password");

        try {
            return DriverManager.getConnection(URL+dbName, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MariaDB: "+e.getMessage());
            return null;
        }
    }
    
}
