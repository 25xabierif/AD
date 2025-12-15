package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Monstruo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOLA_FUEGO")
public class BolaFuego extends Conjuro{

    private final int damage;

    public BolaFuego(int damage){
        super("Bola de fuego");
        this.damage = damage;
    }

    @Override
    public void efecto(List<Monstruo> objetivos) {
       objetivos.forEach(monstruo -> monstruo.setVida(monstruo.getVida()-damage)); 
    }
    
}