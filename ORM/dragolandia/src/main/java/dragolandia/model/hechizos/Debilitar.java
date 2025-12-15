package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Monstruo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEBILITAR")
public class Debilitar extends Conjuro{

    private final int debilitar;

    public Debilitar(int debilitar){
        super("Debilitar");
        this.debilitar = debilitar;
    }

    @Override
    public void efecto(List<Monstruo> objetivos) {
        
        objetivos.forEach(monstruo -> {
            int fuerzaFinal = (int)monstruo.getFuerza()*debilitar/100;
            monstruo.setFuerza(fuerzaFinal);
        });
    }
    
}
