package elementos;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class ElementosDeNivel extends Sprite
{
    public ElementosDeNivel(String archivo,int x, int y, int[] secuencia) throws IOException {

        super(Image.createImage(archivo),x,y);
        super.setFrameSequence(secuencia);
    }
    public void dibujar(Graphics g) {
        super.paint(g);
    }

    public void actualizar() {
        super.nextFrame();
    }
}
