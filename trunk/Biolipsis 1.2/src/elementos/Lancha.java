package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Lancha extends ElementosDeNivel {

    private static final String archivo = "/lancha.png";
    private static int alto = 40;
    private static int ancho = 160;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4,5,5,6,6};

    public Lancha() throws IOException {
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
