package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Taxi extends ElementosDeNivel {

    private static final String archivo = "/taxi.png";
    private static int alto = 51;
    private static int ancho = 87;
    private static int[] secuencia = {0,0,0,0};

    public Taxi() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
