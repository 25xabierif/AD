package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Monstruo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RAYO")
public class Rayo extends Conjuro{

    private final int damage;


    public Rayo(int damage){
        super("Rayo");
        this.damage = damage;
    }

    @Override
    public void efecto(List<Monstruo> objetivos) {
        objetivos.forEach(monstruo -> monstruo.setVida(damage));
    }
    
}
