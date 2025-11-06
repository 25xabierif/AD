package bolechas;



public class Principal {
    public static void main(String[] args) {

        GestorBD.createDB();
        GestorBD.createTableCliente();
        GestorBD.createTableProducto();
        GestorBD.createTablePedido();
        GestorBD.createTableProductoPedido();

        
    }
}