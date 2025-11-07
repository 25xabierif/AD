package bolechas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.mysql.cj.protocol.Resultset;

import conexiones.MySQLConnection;

public class GestorBD {

    private static final String BD = "bolechasX";

    private static MySQLConnection conexion;
    private static Connection conn;

    static{
        conexion = new MySQLConnection();
        conn = conexion.getConnectionName(BD);
    }


    public static boolean dniValido(String dni){
        return dni != null && dni.matches("\\d{8}[A-Za-z]");
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

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createDB);
                stmt.executeUpdate(createUser);
                stmt.executeUpdate(grantPriv);
                stmt.executeUpdate(flushPriv);
            }

            System.out.println("BD creada con éxito!");

        } catch (SQLException e) {
            System.err.println("No se ha podido conectar a mysql: "+e.getMessage());
        }

    }

    public static void innitConnections(){
        conn = conexion.getConnectionName(BD);
        if(conn == null){
            System.err.println("No se ha podido conectar a la BD " + BD + ". Comprueba credenciales y que la BD exista.");
        } else {
            System.out.println("Conexión a " + BD + " inicializada.");
        }
    }

    public static void createTableProducto(){

        try (Connection conn = conexion.getConnectionName(BD)) {

            String createT = """
                    CREATE TABLE IF NOT EXISTS Producto
                        (`id` INT NOT NULL AUTO_INCREMENT,
                        nombre VARCHAR(32) NOT NULL ,
                        precio DECIMAL(8,2) NOT NULL ,
                        descripcion TINYTEXT NULL ,
                        PRIMARY KEY (`id`), UNIQUE (`nombre`))
                    """;
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createT);
            stmt.close();

            System.out.println("Tabla Producto creada con éxito!");

        } catch (Exception e) {
            System.err.println("La creación de la tabla producto ha fallado: "+e.getMessage());
        }

    }

    public static void createTableCliente(){

        try (Connection conn = conexion.getConnectionName(BD)) {

            String createT = """
                    CREATE TABLE IF NOT EXISTS Cliente(
                        `dni` VARCHAR(9) NOT NULL,
                        `nombre` VARCHAR(255) NOT NULL,
                        PRIMARY KEY (`dni`))
                    """;
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createT);
            stmt.close();

            System.out.println("Tabla Cliente creada con éxito!");

        } catch (Exception e) {
            System.err.println("La creación de la tabla Cliente ha fallado: "+e.getMessage());
        }

    }

    public static void createTablePedido(){

        try (Connection conn = conexion.getConnectionName(BD)) {

            String createT = """
                CREATE TABLE IF NOT EXISTS Pedido(
	                id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	                fecha DATE NOT NULL,
	                dni VARCHAR(9) NOT NULL,
	                CONSTRAINT `fk_cliente` FOREIGN KEY (dni) REFERENCES Cliente(dni))
                """;
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createT);
            stmt.close();

            System.out.println("Tabla Pedido creada con éxito!");

        } catch (Exception e) {
            System.err.println("La creación de la tabla Pedido ha fallado: "+e.getMessage());
        }

    }

    public static void createTableProductoPedido(){

        try (Connection conn = conexion.getConnectionName(BD)) {
            String createT = """
                CREATE TABLE IF NOT EXISTS ProductoPedido(
                    id_producto INT NOT NULL,
                    id_pedido INT NOT NULL,
                    cantidad INT NOT NULL,
                    PRIMARY KEY (id_producto, id_pedido),
                    CONSTRAINT `fk_producto` FOREIGN KEY (id_producto) REFERENCES Producto(id),
                    CONSTRAINT `fk_pedido` FOREIGN KEY (id_pedido) REFERENCES Pedido(id))
                """;
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createT);
            stmt.close();

            System.out.println("Tabla ProductoPedido creada con éxito!");

        } catch (Exception e) {
            System.err.println("La creación de la tabla ProductoPedido ha fallado: "+e.getMessage());
        }

    }

    public static void altaCliente(Cliente cliente){
        try (Connection conn = conexion.getConnectionName(BD)) {

            String alta = """
                    INSERT INTO Cliente (nombre, dni)
                    VALUES (?,?);
                    """;
            PreparedStatement ps = conn.prepareStatement(alta);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDni());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.err.println("No se ha podido registrar el cliente: "+e.getMessage());
        }
    }

    public static void bajaCliente(String dni){
        try (Connection conn = conexion.getConnectionName(BD)) {
            String baja = """
                DELETE FROM Cliente WHERE dni = ?;
                """;
            PreparedStatement ps = conn.prepareStatement(baja);
            ps.setString(1, dni);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("No se ha podido dar de baja el cliente: "+e.getMessage());
        }
        
    }

    public static void modificarCliente(String nombre, String dni){
        try (Connection conn = conexion.getConnectionName(BD)) {
            String alterC = """
                    UPDATE Cliente
                    SET dni = ?, nombre = ?
                    """;
            PreparedStatement ps = conn.prepareStatement(alterC);
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("No se han podido modificar los datos del cliente: "+e.getMessage());
        }
    }

    public static void registrarProducto(Producto producto){
        try (Connection conn = conexion.getConnectionName(BD)) {
            String nuevoP = """
                    INSERT INTO Producto (nombre, precio, descripcion)
                    VALUES (?,?,?);
                    """;
            PreparedStatement ps = conn.prepareStatement(nuevoP);
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.executeUpdate();
            ps.close();

            //Asignamos id de producto al objeto
            String select = """
            SELECT id FROM Producto WHERE nombre = ?
                    """;
            PreparedStatement ps2 = conn.prepareStatement(select);
            ps2.setString(1, producto.getNombre());
            ResultSet rs = ps2.executeQuery();

            if(rs.next()){
                producto.setId(rs.getInt(1));
            }

            rs.close();
            ps2.close();

        } catch (SQLException e) {
            System.err.println("No se ha podido registrar el nuevo producto: "+e.getMessage());
        }
    }

    public static void borrarProducto (int id){
        try (Connection conn = conexion.getConnectionName(BD)) {
            String borrarP = """
                    DELETE FROM Producto WHERE id = ?;
                    """;
            PreparedStatement ps = conn.prepareStatement(borrarP);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("No se ha podido borrar el producto: "+e.getMessage());
        }
    }

    public static void consultaCliente(String dni){
        try (Connection conn = conexion.getConnectionName(BD)) {
            String consulta = """
                    SELECT * FROM Cliente WHERE dni = ?;
                    """;

            PreparedStatement ps = conn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            System.err.println("La consulta ha fallado: "+e.getMessage());
        }
    }

    public static void crearPedido(Pedido pedido){
        try (Connection conn = conexion.getConnectionName(BD)) {
            //Transformamos la fecha de pedido a una fecha legible para MySQL
            LocalDate fechaLocal = pedido.getFecha();
            Date fechaSQL = Date.valueOf(fechaLocal);

            String crearPedido = """
                    INSERT INTO Pedido (fecha, dni) VALUES(?,?);
                    """;
            PreparedStatement ps = conn.prepareStatement(crearPedido);
            ps.setDate(1, fechaSQL);
            ps.setString(2, pedido.getDniCliente());
            ps.executeUpdate();
            ps.close();

            //Asignamos id al pedido en java
            String consultaId = "SELECT id FROM Pedido WHERE dni = ?";
            PreparedStatement ps2 = conn.prepareStatement(consultaId);
            ps2.setString(1, pedido.getDniCliente());
            ResultSet rs = ps2.executeQuery();
            while(rs.next()){
                pedido.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.err.println("La creación del pedido ha fallado: "+e.getMessage());
        }
    }

    public static void borrarPedido(int id){
        try (Connection conn = conexion.getConnectionName(BD)) {
            String borrarPedido = """
                    DELETE FROM Pedido WHERE id = ?
                    """;

            PreparedStatement ps = conn.prepareStatement(borrarPedido);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.err.println("El borrado del pedido ha fallado: "+e.getMessage());
        }
    }

    public static void altaProductoPedido(ProductoPedido productoPedido){
        try (Connection conn = conexion.getConnectionName(BD)) {
            String altaProductoPedido = """
                    INSERT INTO ProductoPedido (id_producto, id_pedido, cantidad)
                    VALUES (?,?,?);
                    """;
            PreparedStatement ps = conn.prepareStatement(altaProductoPedido);
            ps.setInt(1, productoPedido.getIdProducto());
            ps.setInt(2, productoPedido.getIdPedido());
            ps.setInt(3, productoPedido.getCantidad());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.err.println("El alta de ProductoPedido ha fallado: "+e.getMessage());
        }
    }
    

    public static void consultarPedidoCliente(String dni){
        try (Connection conn = conexion.getConnectionName(BD)) {
            String consultaPedidos = """
                    SELECT 
                        c.nombre AS nombre_cliente,
                        p.id AS id_pedido,
                        p.fecha,
                        GROUP_CONCAT(pr.nombre SEPARATOR ', ') AS productos,
                        SUM(pr.precio * pp.cantidad) AS total_pedido
                    FROM Cliente AS c
                    JOIN Pedido AS p ON c.dni = p.dni
                    JOIN ProductoPedido AS pp ON p.id = pp.id_pedido
                    JOIN Producto AS pr ON pp.id_producto = pr.id
                    WHERE c.dni = ? 
                    GROUP BY c.nombre, p.id, p.fecha
                    ORDER BY p.fecha DESC;
                    """;
            PreparedStatement ps = conn.prepareStatement(consultaPedidos);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            System.out.println("==========================================================================");
            System.out.printf("%-20s %-10s %-12s %-40s %-10s%n",
                    "Cliente", "ID Pedido", "Fecha", "Productos", "Total (€)");
            System.out.println("==========================================================================");

            // Filas
            while (rs.next()) {
                String cliente = rs.getString("nombre_cliente");
                int idPedido = rs.getInt("id_pedido");
                Date fecha = rs.getDate("fecha");
                String productos = rs.getString("productos");
                double total = rs.getDouble("total_pedido");

                // Formato con printf: columnas alineadas
                System.out.printf("%-20s %-10d %-12s %-40s %-10.2f%n",
                        cliente, idPedido, fecha.toString(), productos, total);
            }

            System.out.println("==========================================================================");


            ps.close();

                    
        } catch (Exception e) {
            System.err.println("La consulta de los pedidos del cliente ha fallado: "+e.getMessage());
        }
    }

    public static void eliminarBD(){
        try (Connection conn = conexion.getConnectionName(BD)){
            String eraseBD = "DROP DATABASE bolechasX;";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(eraseBD);
            stmt.close();

            System.out.println("La BD ha sido eliminada con éxito.");

        } catch (SQLException e){
            System.err.println("La BD no ha podido ser borrada: "+e.getMessage());
        }
    }
}