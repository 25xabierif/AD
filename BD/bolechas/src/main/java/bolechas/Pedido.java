package bolechas;

import java.time.LocalDate;

public class Pedido {
    
    private int id;
    private LocalDate fecha;
    private int idCliente;

    
    public Pedido(int id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdCliente(){
        return this.idCliente;
    }
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        return "Pedido [getId()=" + getId() + ", getFecha()=" + getFecha() + "]";
    }

}
