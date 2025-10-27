package bolechas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import conexiones.MySQLConnection;

public class GestorBD {
    
    private static final String BD = "bolechasX";

    private static MySQLConnection conexion;
    private static Connection conn;

    static{
        conexion = new MySQLConnection();
        conn = conexion.getConnectionName(BD);
    }

    public static void createDB(){

        try (Connection conn = conexion.getConnection()) {
            
            String createDB = """
                    CREATE DATABASE IF NOT EXISTS bolechasX
                        CHARACTER SET utf8mb4
                        COLLATE utf8mb4_general_ci;
                    """;

            String createUser = """
                    CREATE USER IF NOT EXISTS 'usuario'@'%' IDENTIFIED BY 'usuario123';
                    """;
            String grantPriv = """
                    GRANT ALL PRIVILEGES ON bolechasX.* TO 'usuario'@'%';
                    """;
            String flushPriv = """
                    FLUSH PRIVILEGES;
                    """;

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createDB);
            stmt.executeUpdate(createUser);
            stmt.executeUpdate(grantPriv);
            stmt.executeUpdate(flushPriv);
            stmt.close();

            System.out.println("BD creada con éxito!");

        } catch (Exception e) {
            System.err.println("No se ha podido conectar a mysql: "+e.getMessage());
        }

    }

    public static void createTableProducto(){

        try {

            String createT = """
                    CREATE TABLE Producto(
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `descripcion` VARCHAR(255) NOT NULL,
                        `precio` SMALLINT NOT NULL,
                        `nombre` VARCHAR(100) NOT NULL,
                        `PRIMARY` KEY (id)
                        UNIQUE `nombre_unico` (nombre(100)))
                    """;
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createT);
            stmt.close();

        } catch (Exception e) {
            System.err.println("La creación de la tabla producto ha fallado: "+e.getMessage());
        }

    }

    public static void createTableCliente(){

        try {

            String createT = """
                    CREATE TABLE IF NOT EXISTS Cliente(
                        `dni` VARCHAR(9) NOT NULL,
                        `nombre` VARCHAR(255) NOT NULL,
                        PRIMARY KEY (`dni`))
                    """;
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createT);
            stmt.close();

        } catch (Exception e) {
            System.err.println("La creación de la tabla Cliente ha fallado: "+e.getMessage());
        }

    }

    public static void createTablePedido(){

        try {

            String createT = """
                CREATE TABLE IF NOT EXISTS Pedido(
	                id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	                fecha DATE NOT NULL,
	                dni VARCHAR(9) NOT NULL,
	                CONSTRAINT fk_cliente FOREIGN KEY (dni) REFERENCES cliente(dni))
                """;
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createT);
            stmt.close();

        } catch (Exception e) {
            System.err.println("La creación de la tabla Pedido ha fallado: "+e.getMessage());
        }

    }

    public static void createTableProductoPedido(){

        try {
            String createT = """
                CREATE TABLE IF NOT EXISTS ProductoPedido(
                    id_producto INT NOT NULL,
                    id_pedido INT NOT NULL,
                    cantidad INT NOT NULL,
                    CONSTRAINT fk_producto FOREIGN KEY (id_producto) REFERENCES Producto(id),
                    CONSTRAINT fk_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id),
                    CONSTRAINT pk_pedido PRIMARY KEY (id_producto, id_pedido)
                """;
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createT);
            stmt.close();

        } catch (Exception e) {
            System.err.println("La creación de la tabla producto ha fallado: "+e.getMessage());
        }

    }

    public static void cerrarConexion(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("La conexión no se ha cerrado: "+e.getMessage());
        }

    }

}
