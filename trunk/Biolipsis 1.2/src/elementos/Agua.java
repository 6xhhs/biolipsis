package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Agua extends ElementosDeNivel {

    private static final String archivo = "/agua1.jpg";
    private static int alto = 22;
    private static int ancho = 380;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4};

    public Agua() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
