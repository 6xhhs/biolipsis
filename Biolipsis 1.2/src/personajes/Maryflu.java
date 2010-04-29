
package personajes;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Maryflu extends Heroes{

    private static final String archivito="/maryflu.png";

    public Maryflu() throws IOException{
        super(archivito);
    }

}
