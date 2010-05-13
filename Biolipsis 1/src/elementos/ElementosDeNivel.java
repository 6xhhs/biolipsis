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
public class ElementosDeNivel extends Sprite
{
    /**
     *
     * @param archivo Indica el archivo que se va a cargar
     * @param ancho Envía como parámetro el ancho del sprite
     * @param alto Envía como parámetro el alto del sprite
     * @param secuencia Indica la secuencia de sprites que se va a manejar
     * @throws IOException Arroja una excepción en caso de no poder cargar la imagen
     */
    public ElementosDeNivel(String archivo,int ancho, int alto, int[] secuencia) throws IOException {

        super(Image.createImage(archivo),ancho,alto);
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
     */
    public void actualizar() {
        super.nextFrame();
    }
}
