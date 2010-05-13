package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author vicius thel
 */
public class AnimalSeis extends ElementosDeNivel {

    private static final String archivo = "/nivel1/animal6.png";
    private static int alto = 35;
    private static int ancho = 45;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11};

    /**
     *
     * @throws IOException
     */
    public AnimalSeis() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }
     /**
     * Actualiza la animación del sprite
      * @param dx
      */
    public void actualizar() {
        super.actualizar();
    }

    /**
     *
     * @param x
     */
    public void mover(int x) {
        super.move(x, 0);
    }
}
