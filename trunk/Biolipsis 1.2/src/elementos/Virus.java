package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class Virus extends ElementosDeNivel {

    private static final String archivo = "/superManchis.png";
    private static int alto = 15;
    private static int ancho = 15;
    private static int[] secuencia = {0,0,1,1,2,2};

    public Virus() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
