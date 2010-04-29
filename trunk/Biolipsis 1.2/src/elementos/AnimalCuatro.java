package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public class AnimalCuatro extends ElementosDeNivel {

    private static final String archivo = "/animal4.png";
    private static int alto = 27;
    private static int ancho = 31;
    private static int[] secuencia = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14};

    public AnimalCuatro() throws IOException {
        super(archivo,ancho,alto,secuencia);
    }
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }

    public void actualizar() {
        super.actualizar();
    }
}
