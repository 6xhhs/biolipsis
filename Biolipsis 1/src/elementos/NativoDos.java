package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class NativoDos extends ElementosDeNivel {

    private static final String archivo = "/nivel1/nativoAyuda.png";
    private static int alto = 75;
    private static int ancho = 48;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14};

    /**
     * 
     * @throws IOException
     * Esta excepción es arrojada cuando no se encuentra la imagen
     */
    public NativoDos() throws IOException {
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
     * 
     */
    public void actualizar() {
        super.actualizar();
    }
}
