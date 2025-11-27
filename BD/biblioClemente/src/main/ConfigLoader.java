package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("resources/application.properties");
        if (input == null) {
            // Intentar alternativa común: fichero en la raíz del classpath
            input = ConfigLoader.class.getClassLoader().getResourceAsStream("application.properties");
        }

        // Si no está en el classpath, intentar rutas habituales en el sistema de ficheros (proyecto local)
        if (input == null) {
            String[] candidates = {"src/resources/application.properties", "resources/application.properties", "application.properties"};
            for (String p : candidates) {
                try {
                    java.io.File f = new java.io.File(p);
                    if (f.exists() && f.isFile()) {
                        input = new java.io.FileInputStream(f);
                        break;
                    }
                } catch (java.io.FileNotFoundException e) {
                    // seguir intentando otras rutas
                }
            }
        }

        if (input == null) {
            throw new RuntimeException("No se encontró el fichero application.properties");
        }

        try (InputStream in = input) {
            properties.load(in);
        } catch (IOException ex) {
            throw new RuntimeException("ERROR cargando el fichero de configuración: " + ex.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
