package conexiones;

import main.ConfigLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class OracleDBConnection implements DBConnection {

    private final String USER = ConfigLoader.get("oracle.user");
    private final String PASS = ConfigLoader.get("oracle.password");
    private final String URL = ConfigLoader.get("oracle.url");
    private final String DB = ConfigLoader.get("oracle.db");
    private final String SUPERUSER = ConfigLoader.get("oracle.superUser");
    private final String SPASS = ConfigLoader.get("oracle.rootPass");

    @Override
    public Connection getConnection() {
        
        try {
            return DriverManager.getConnection(URL+DB, USER, PASS);
        } catch (SQLException e) {
            System.err.println("La conexión a la BD ha fallado: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Connection getConnectionServer() {
        
        try {
            return DriverManager.getConnection(URL, SUPERUSER, SPASS);
        } catch (SQLException e) {
            System.err.println("La conexión al servidor de Oracle ha fallado: "+e.getMessage());
            return null;
        }

    }
    
}
