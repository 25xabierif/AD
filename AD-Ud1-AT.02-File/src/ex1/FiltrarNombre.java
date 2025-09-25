package ex1;
import java.io.File;
import java.io.FilenameFilter;

public class FiltrarNombre implements FilenameFilter{

    private String extension;

    public FiltrarNombre(String extension){
        this.extension = extension;
    }

    public void setExtension(String extension){
        this.extension = extension;
    }
    public String getExtension(){
        return extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
    
}
