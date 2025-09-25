public class Mago {
    private String nombre;
    private int vida;
    private int nivelMagia;
    private Hechizo hechizo;

    Mago(String nombre, int vida, int nivelMagia, Hechizo hechizo){
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
        this.hechizo = hechizo;
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

    public void setNivelMagia(int nivelMagia){
        this.nivelMagia = nivelMagia;
    }
    public int getNivelMagia(){
        return this.nivelMagia;
    }

    public void setHechizo(Hechizo hechizo){
        this.hechizo = hechizo;
    }
    public Hechizo getHechizo(){
        return this.hechizo;
    }

    //Métodos

    public void lanzarHechizo(Monstruo monstruo){
        monstruo.recibirDaño(this.nivelMagia);
    }

    public void aprenderHechizo(Hechizo hechizo){
        this.hechizo = hechizo;
    }
}