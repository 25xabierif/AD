package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.alumnado.ConfigLoader;

public class PostgreSQLConnection implements DBConnection{

    /* Sin archivo aplicacion.properties */

    /* private static final String URL = "jdbc:postgresql://localhost:5432/dam2";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin123"; */

    @Override
    public Connection getConnectionServer() {
        /* Con archivo application.properties */
        String URL = ConfigLoader.get("postgresql.urlInicial");
        String USER = ConfigLoader.get("postgresql.user");
        String PASSWORD = ConfigLoader.get("postgresql.password");

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a PostgreSQL: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Connection getConnection() {
        /* Con archivo application.properties */
        String URL = ConfigLoader.get("postgresql.url");
        String BD = ConfigLoader.get("postgresql.bd");
        String USER = ConfigLoader.get("postgresql.user");
        String PASSWORD = ConfigLoader.get("postgresql.password");

        try {
            return DriverManager.getConnection(URL+BD, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a PostgreSQL: "+e.getMessage());
            return null;
        }
    }
    
}
