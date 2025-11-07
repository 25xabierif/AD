package bolechas;
public class Producto {
    
    private String descripcion;
    private float precio;
    private int id;
    private String nombre;

    

    public Producto(String nombre, String descripcion, float precio) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public float getPrecio() {
        return precio;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Producto [getDescripcion()=" + getDescripcion() + ", getPrecio()=" + getPrecio() + ", getId()="
                + getId() + ", getNombre()=" + getNombre() + "]";
    }

}
