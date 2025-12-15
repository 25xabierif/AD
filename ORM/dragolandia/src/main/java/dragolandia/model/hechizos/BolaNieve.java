package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Monstruo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BOLA_NIEVE")
public class BolaNieve extends Conjuro{

    public BolaNieve(){
        super("Bola de nieve");
    }

    @Override
    public void efecto(List<Monstruo> objetivos) {
        objetivos.forEach(monstruo -> monstruo.setVida(0));
    }
}
