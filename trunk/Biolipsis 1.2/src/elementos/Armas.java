package elementos;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Armas extends Sprite{

    public static final int alto=10;
    public static final int ancho=15;
    private static final int[] secuencia={0,0,0,0};

    public Armas(String archivo) throws IOException {

        super(Image.createImage(archivo),ancho,alto);
        super.setFrameSequence(secuencia);
        super.setFrame(0);
    }

    public void moverX(int dx){
        this.move(dx, 0);
    }

}