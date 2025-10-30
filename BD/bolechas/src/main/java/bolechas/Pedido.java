package bolechas;

import java.time.LocalDate;

public class Pedido {
    
    private int id;
    private LocalDate fecha;
    private String dniCliente;

    
    public Pedido(LocalDate fecha, String dniCliente) {
        this.fecha = fecha;
        this.dniCliente = dniCliente;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDniCliente(){
        return this.dniCliente;
    }
    public void setDniCliente(String dniCliente){
        this.dniCliente = dniCliente;
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
