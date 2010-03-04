package Menu;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

public class AppGameCanvas extends MIDlet {
    private PantallaMenu lienzo;

    public AppGameCanvas() {
        lienzo = new PantallaMenu(this);
    }

    public void startApp() {
        Display.getDisplay(this).setCurrent(lienzo);
        for(int i=0;i<=200;i++) {
            lienzo.dibujar();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void terminar() {
        destroyApp(true);
        notifyDestroyed();
    }
}