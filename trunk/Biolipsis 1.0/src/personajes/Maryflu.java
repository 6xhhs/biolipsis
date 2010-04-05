
package personajes;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Maryflu extends Heroes{

    private static final String archivito="/Mariflu.png";

    public Maryflu() throws IOException{
        super(archivito);

    }
    public void dibujar(Graphics g){
        super.dibujar(g);
    }
    public void actualizar(){
        super.actualizar();
    }

}
