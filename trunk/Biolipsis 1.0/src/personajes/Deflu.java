
package personajes;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Deflu extends Heroes{

    private static final String archivito="/CadenaDeflus.png";

    public Deflu() throws IOException{
        super(archivito);

    }
    public void dibujar(Graphics g){
        super.dibujar(g);
    }
    public void actualizar(){
        super.actualizar();
    }

}
