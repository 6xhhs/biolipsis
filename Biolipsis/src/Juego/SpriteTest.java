package juego;
import Juego.Sprites;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class SpriteTest extends MIDlet implements CommandListener {

    private Command exitCommand, playCommand, endCommand;
    private Display display;
    private SSCanvas screen;

    public SpriteTest() {
        display = Display.getDisplay(this);
        exitCommand = new Command("Salir", Command.SCREEN, 2);
        screen = new SSCanvas();
        screen.addCommand(exitCommand);
        screen.setCommandListener(this);
    }

    public void startApp() throws MIDletStateChangeException {
        display.setCurrent(screen);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {
            destroyApp(false);
            notifyDestroyed();
        }
    }
}

class SSCanvas extends Canvas {

    private Sprites miSprite = new Sprites(1);

    public SSCanvas() {
// Cargamos los sprites
        miSprite.addFrames(1, "/hero.png");
// Iniciamos los Sprites
        miSprite.prendido();
    }

    public void paint(Graphics g) {
// Borrar pantalla
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
// situar y dibujar sprite
        miSprite.setX(50);
        miSprite.setY(50);
        miSprite.draw(g);
    }
}
