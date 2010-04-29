package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Destruido extends ElementosDeNivel {

    private static final String archivo = "/destruido.png";
    private static int alto = 41;
    private static int ancho = 64;
    private static int[] secuencia = {0,0,0,0};

    public Destruido() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
