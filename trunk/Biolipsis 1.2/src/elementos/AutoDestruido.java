package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class AutoDestruido extends ElementosDeNivel {

    private static final String archivo = "/autoDestruido.png";
    private static int alto = 42;
    private static int ancho = 99;
    private static int[] secuencia = {0,0,0,0};

    public AutoDestruido() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
