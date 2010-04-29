package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class NativoUno extends ElementosDeNivel {

    private static final String archivo = "/nativoCorriendo.png";
    private static int alto = 41;
    private static int ancho = 42;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};

    public NativoUno() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }

    public void mover(int x) {
        super.move(x, 0);
    }
}
