import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import controller.AppTareas;
import view.Interfaz;

public class Principal {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new AppTareas(new Interfaz());
        });

    }
}
