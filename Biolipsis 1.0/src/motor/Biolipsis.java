package motor;

import menu.Menu;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;
import menu.Menu;

public class Biolipsis extends MIDlet {
    private Menu lienzo;
    private Juego juego;


    public Biolipsis() {
        lienzo = new Menu(this);
    }

    public void startApp() {
        while(lienzo.getCambiar()==false&&!lienzo.terminar) {
            Display.getDisplay(this).setCurrent(lienzo);
        }
        if(lienzo.getCambiar()) {
            juego = new Juego(this);
            lienzo = null;
            ponerPantalla(juego);
        }
    }

    public void pauseApp() {
    }

    public void ponerPantalla(Displayable nuevo) {
        Display anterior = Display.getDisplay(this);
        Display.getDisplay(this).setCurrent(nuevo);
        anterior = null;
    }

    public void destroyApp(boolean unconditional) {
    }

    public void terminar() {
        destroyApp(true);
        notifyDestroyed();
    }
}