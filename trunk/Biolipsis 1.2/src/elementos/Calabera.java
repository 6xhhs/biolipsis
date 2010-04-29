package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Calabera extends ElementosDeNivel {

    private static final String archivo = "/calabera.png";
    private static int alto = 18;
    private static int ancho = 20;
    private static int[] secuencia = {0,0,0,0};

    public Calabera() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
