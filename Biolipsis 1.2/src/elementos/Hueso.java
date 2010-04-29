package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Hueso extends ElementosDeNivel {

    private static final String archivo = "/hueso.png";
    private static int alto = 13;
    private static int ancho = 14;
    private static int[] secuencia = {0,0,0,0};

    public Hueso() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
