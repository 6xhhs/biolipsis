package motor;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;
import menu.Menu;

public class Biolipsis extends MIDlet {
    private Menu menu;
    private Juego juego;


    public Biolipsis() {
        menu = new Menu(this);
    }

    public void startApp() {
        while(menu.getCambio()==false&&!menu.terminar) {
            Display.getDisplay(this).setCurrent(menu);
        }
        if(menu.getCambio()) {
            juego = new Juego(this, menu.isSeleccionPersonaje());
            menu = null;
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