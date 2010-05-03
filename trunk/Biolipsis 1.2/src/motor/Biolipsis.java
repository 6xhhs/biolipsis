package motor;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;
import menu.Menu;

public class Biolipsis extends MIDlet {
    private Menu menu;
    private Juego juego;
    private Musica musica;


    public Biolipsis() {
        menu = new Menu(this);
        //musica = new Musica();

    }

    public void startApp() {
        while(menu.getCambio()==false&&!menu.terminar) {
            Display.getDisplay(this).setCurrent(menu);
            musica = new Musica();
        }
        if(menu.getCambio()) {
            try {
                juego = new Juego(this, menu.isSeleccionPersonaje());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

    public void reproducir(String archivo) throws Exception {
        if (musica != null) {
            musica.reproducir(archivo);
        } else {
            System.out.println("Se va a crear la musica");
            musica = new Musica();
            return;
        }
    }

    public void detenerMusica(){
        musica.detenerMusica();
    }

    public boolean estaReproduciendo() {
        if(musica==null)
            System.out.println("La musica no sea ha creado");
        return this.musica.getReproduciendo();
    }
}