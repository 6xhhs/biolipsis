package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Rayo extends ElementosDeNivel {

    private static final String archivo = "/rayotes.png";
    private static int alto = 182;
    private static int ancho = 60;
    private static int[] secuencia = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3};

    public Rayo() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
