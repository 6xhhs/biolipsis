package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class Flecha2 extends ElementosDeNivel {

    private static final String archivo = "/nivel2/flecha2.png";
    private static int alto = 45;
    private static int ancho = 46;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4,5,5};

    /**
     * 
     * @throws IOException
     * Esta excepción es arrojada cuando no se encuentra la imagen
     */
    public Flecha2() throws IOException {
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
