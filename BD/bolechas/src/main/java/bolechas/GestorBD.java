package bolechas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern patronDni = Pattern.compile("\\d{8}[A-Z a-z]");
        Matcher matcher = patronDni.matcher(dni);
        return matcher.matches();
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
                    CREATE TABLE IF NOT EXISTS `Producto`
                        (`id` INT NOT NULL AUTO_INCREMENT,
                        `nombre` VARCHAR(32) NOT NULL ,
                        `precio` DECIMAL(8,2) NOT NULL ,
                        `descripcion` TINYTEXT NULL ,
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

            System.out.println("Tabla Cliente creada con éxito!");

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

        try {
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

    public static void cerrarConexion(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("La conexión no se ha cerrado: "+e.getMessage());
        }

    }

    public static void altaCliente(Cliente cliente){
        try {

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
        try {
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
        try {
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
        try {
            String nuevoP = """
                    INSERT INTO Producto (nombre,precio,descripcion)
                    VALUES (?,?,?);
                    """;
            PreparedStatement ps = conn.prepareStatement(nuevoP);
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getPrecio());
            ps.setString(3, producto.getDescripcion());
            ps.executeUpdate();
            ps.close();

            //Asignamos id de producto al objeto
            String select = "SELECT id FROM Producto WHERE nombre = "+producto.getNombre();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select);
            producto.setId(rs.getInt(1));

        } catch (SQLException e) {
            System.err.println("No se ha podiddo registrar el nuevo producto: "+e.getMessage());
        }
    }

    public static void borrarProducto (int id){
        try {
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
        try {
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
        try {
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
            String consultaId = "SELECT id FROM Pedido WHERE dni = "+pedido.getDniCliente();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(consultaId);
            pedido.setId(rs.getInt(1));

        } catch (SQLException e) {
            System.err.println("La creación del pedido ha fallado: "+e.getMessage());
        }
    }

    public static void borrarPedido(int id){
        try {
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
        try {
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
        try {
            String consultaPedidos = """
                    
                    """;
            PreparedStatement ps = conn.prepareStatement(consultaPedidos);
            ps.setString(1, dni);
            ps.executeQuery();
            ps.close();
                    
        } catch (Exception e) {
            System.err.println("La consulta de los pedidos del cliente ha fallado: "+e.getMessage());
        }
    }
}

/* ProductoPedido
    id_producto
    id_pedido
    cantidad */
/* Producto
    id
    nombre
    precio FLOAT
    descripcion */
/* Cliente
    dni 
    nombre 
/* Pedido
	id 
	fecha DATE 
	dni VARCHAR(9) */