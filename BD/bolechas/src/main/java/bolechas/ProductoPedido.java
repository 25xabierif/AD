package bolechas;

public class ProductoPedido {
    
    private int cantidad;
    private int idProducto;
    private int idPedido;

    public ProductoPedido(int cantidad, int idProducto, int idPedido) {
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.idPedido = idPedido;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public int getidPedido() {
        return idPedido;
    }
    public void setidPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String toString() {
        return "ProductoPedido [getCantidad()=" + getCantidad() + ", getIdProducto()=" + getIdProducto()
                + ", getidPedido()=" + getidPedido() + "]";
    }

}
