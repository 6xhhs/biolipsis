
package personajes;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Heroes extends Personaje{

    private static final int alto=37;
    private static final int ancho=53;
    private static final int[] secuenciacaminar={0,0,1,1,2,2,3,3};

    public Heroes(String archivito) throws IOException{
        super(archivito, ancho, alto, secuenciacaminar);

    }
    public void dibujar(Graphics g){
        super.dibujar(g);
    }
    public void actualizar(){
        super.actualizar();
    }
    public int[] getSecuencia(){
        return secuenciacaminar;
    }

}
