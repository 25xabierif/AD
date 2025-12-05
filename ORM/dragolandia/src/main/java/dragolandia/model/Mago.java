package dragolandia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mago")
public class Mago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int nivelMagia;

    public Mago(){}

    public Mago(String nombre, int vida, int nivelMagia){
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    @Override
    public String toString() {
        return "Mago [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", nivelMagia=" + nivelMagia + "]";
    }

    public void lanzarHechizo(Monstruo monstruo){
        int vidaActualizada = monstruo.getVida() - nivelMagia;
        monstruo.setVida(vidaActualizada);
        System.out.println(monstruo.getNombre()+" ha recibido "+nivelMagia+ " puntos de daño. Vida restante: "+monstruo.getVida()+".");
    }
}
