import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Enemigo extends Sprite {

    public Enemigo(String archivo, int ancho, int alto,int[] secuencia) throws IOException {

        super(Image.createImage(archivo),ancho,alto);
        super.setFrameSequence(secuencia);
        super.setFrame(0);
    }

    public void dibujar(Graphics g) {

        super.paint(g);
    }

    public void actualizar() {

        super.nextFrame();
    }
}
