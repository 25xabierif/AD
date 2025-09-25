public class Monstruo {
    
    private String nombre;
    private int vida;
    private int fuerza;
    private Tipo tipo;

    Monstruo(String nombre, int vida, int fuerza, Tipo tipo){
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
        this.tipo = tipo;
    }

    //Getters y setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setVida(int vida){
        this.vida = vida;
    }
    public int getVida(){
        return this.vida;
    }

    public void setFuerza(int fuerza){
        this.fuerza = fuerza;
    }
    public int getFuerza(){
        return this.fuerza;
    }

    //Métodos

    public void atacar(Mago mago){
        mago.setVida(mago.getVida()-this.getFuerza());
    }

    public void recibirDaño(int daño){
        this.setVida(this.getVida()-daño);
    }
}
