package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class NativoTres extends ElementosDeNivel {

    private static final String archivo = "/nivel1/nativoAyuda2.png";
    private static int alto = 49;
    private static int ancho = 49;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11};

    /**
     * 
     * @throws IOException
     * Esta excepción es arrojada cuando no se encuentra la imagen
     */
    public NativoTres() throws IOException {
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
