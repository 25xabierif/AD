public class Bosque {
    
    private String nombre;
    private int nivelPeligro;
    private Monstruo monstruoJefe;

    Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe){
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
    }

    //Getters y setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setNivelPeligro(int nivelPeligro){
        this.nivelPeligro = nivelPeligro;
    }
    public int getNivelPeligro(){
        return this.nivelPeligro;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe){
        this.monstruoJefe = monstruoJefe;
    }

    public Monstruo getMonstruo(){
        return this.monstruoJefe;
    }

    //Métodos

    public void mostrarJefe(){
        System.out.println("Jefe: "+this.monstruoJefe.getNombre()+"\n Puntos de vida: "+this.monstruoJefe.getVida()+"\n Puntos de ataque: "+this.monstruoJefe.getFuerza());
    }

    public void cambiarJefe(Monstruo monstruoJefe){
        this.monstruoJefe = monstruoJefe;
    }
}
