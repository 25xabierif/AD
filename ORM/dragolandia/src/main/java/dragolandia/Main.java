package dragolandia;

import dragolandia.controller.GestorMago;
import dragolandia.controller.HibernateUtil;
import dragolandia.model.Mago;

public class Main {
    public static void main(String[] args) {
        
        GestorMago gestorMago = new GestorMago();

        gestorMago.addMago("Gandalf", 1500, 200);
        gestorMago.addMago("Saruman", 1300, 180);
        gestorMago.addMago("Harry Potter", 700, 100);

        HibernateUtil.shutdown();
    }
}