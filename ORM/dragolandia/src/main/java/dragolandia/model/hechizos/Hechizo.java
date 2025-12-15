package dragolandia.model.hechizos;

import java.util.List;

import dragolandia.model.Monstruo;

public interface Hechizo {
    void efecto(List<Monstruo> objetivos);
}
