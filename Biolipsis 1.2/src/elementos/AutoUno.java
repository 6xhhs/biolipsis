package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class AutoUno extends ElementosDeNivel {

    private static final String archivo = "/auto1.png";
    private static int alto = 39;
    private static int ancho = 64;
    private static int[] secuencia = {0,0,0,0};

    public AutoUno() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
