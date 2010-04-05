
package personajes;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Zombieflu extends Personaje{

    private static final String archivito="";
    private static final int alto=62;
    private static final int ancho=31;
    private static final int[] secuencia={0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3};

    public Zombieflu() throws IOException{
        super(archivito, ancho, alto, secuencia);

    }
    public void dibujar(Graphics g){
        super.dibujar(g);
    }
    public void actualizar(){
        super.actualizar();
    }

}
