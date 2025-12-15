package dragolandia;

import java.util.ArrayList;
import java.util.List;

import dragolandia.controller.GestorMago;
import dragolandia.controller.HibernateUtil;
import dragolandia.model.hechizos.BolaFuego;
import dragolandia.model.hechizos.Conjuro;
import dragolandia.model.hechizos.Debilitar;

public class Main {
    public static void main(String[] args) {
        
        GestorMago gestorMago = new GestorMago();

        List<Conjuro> conjuros = new ArrayList<Conjuro>();
        conjuros.add(new BolaFuego(50));
        conjuros.add(new Debilitar(35));

        gestorMago.addMago("Gandalf", 1500, 200, conjuros);
        gestorMago.addMago("Saruman", 1300, 180, conjuros);
        gestorMago.addMago("Harry Potter", 700, 100, conjuros);

        HibernateUtil.shutdown();
    }
}