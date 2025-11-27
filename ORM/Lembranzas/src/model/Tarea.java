package model;

public class Tarea{
    
    private static int contadorId = 0;

    private final int ID;
    private String titulo;
    private String descripcion;
    private boolean completada;

    /**
     * Constructor de la clase Tarea.
     * Tendremos un atributo static que nos permitirá ir incrementando el id y que este sea constante en la clase.
     * @param ID
     * @param titulo
     * @param descripcion
     * @param completada
     */
    public Tarea(String titulo, String descripcion){
        super();
        this.ID = ++contadorId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completada = false;
    }

    public int getId() {
        return this.ID;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return "Tarea id => " + getId() + ", titulo => " + getTitulo() + ", descripcion => " + getDescripcion()
                + ", completada => " + isCompletada() + ".";
    }
    
}
