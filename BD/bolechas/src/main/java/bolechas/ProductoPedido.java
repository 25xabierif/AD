package bolechas;

public class ProductoPedido {
    
    private int cantidad;
    private int id_producto;
    private int id_pedido;

    public ProductoPedido(int cantidad, int id_producto, int id_pedido) {
        this.cantidad = cantidad;
        this.id_producto = id_producto;
        this.id_pedido = id_pedido;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getId_producto() {
        return id_producto;
    }
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    public int getId_pedido() {
        return id_pedido;
    }
    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    @Override
    public String toString() {
        return "ProductoPedido [getCantidad()=" + getCantidad() + ", getId_producto()=" + getId_producto()
                + ", getId_pedido()=" + getId_pedido() + "]";
    }

}
