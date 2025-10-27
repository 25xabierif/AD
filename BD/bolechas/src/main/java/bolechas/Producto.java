package bolechas;
public class Producto {
    
    private String descripcion;
    private float precio;
    private String id;
    private String nombre;

    

    public Producto(String descripcion, float precio, String id, String nombre) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.id = id;
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public float getPrecio() {
        return precio;
    }
    public String getId() {
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
    public void setId(String id) {
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
