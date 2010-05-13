package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class Compu extends ElementosDeNivel {

    private static final String archivo = "/nivel2/compu.png";
    private static int alto = 40;
    private static int ancho = 52;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,12,12,13,13,14,14,15,15,16,16,17,17,
                                        18,18,19,19,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29,29};

    /**
     * 
     * @throws IOException
     * Esta excepción es arrojada cuando no se encuentra la imagen
     */
    public Compu() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    /**
     *
     * @param g
     * Es el objeto tipo graphics para dibujar
     */
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    /**
     * Actualiza la animación del sprite
     */
    public void actualizar() {
        super.actualizar();
    }
}
