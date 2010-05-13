package elementos;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class Armas extends Sprite{

    /**
     *
     * @param archivo El archivo que llegar como parámetro
     * @param alto El alto del sprite
     * @param ancho El ancho del sprite
     * @param secuencia La secuencia de imágenes que se manejará
     * @throws IOException La excepción en caso de no encontrar el archivo
     */
    public Armas(String archivo,int alto, int ancho, int[] secuencia) throws IOException {

        super(Image.createImage(archivo),alto,ancho);
        super.setFrameSequence(secuencia);
    }
    /**
     * 
     * @param g
     * Es el objeto tipo graphics para dibujar
     */
    public void dibujar(Graphics g) {
        super.paint(g);
    }

    /**
     * Actualiza la animación del sprite
      * @param dx
      */
    public void actualizar() {
        super.nextFrame();
    }
    
     /**
     * Actualiza la animación del sprite
      * @param dx
      */
    public void moverX(int dx){
        this.move(dx, 0);
    }

}