package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Ayuda extends GameCanvas {
    public Graphics g;
    private Imagenes fondoAyuda;
    private Menu menu;
    private boolean bandera;

    public Ayuda(Menu menu){
	super(true);
	g = this.getGraphics();
        bandera = true;
        this.menu = menu;
	try {
            fondoAyuda = new Imagenes("/fondoSubmenus.jpg",16,0,-180);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    public void dibujar() {
        fondoAyuda.dibujar(g);
	flushGraphics();
    }

   public void actualizar() {
        fondoAyuda.mover(8);
        int estado = menu.getKeyStates();
        if(estado == 0) {
            bandera = false;
        }
        if((estado & GAME_D_PRESSED) != 0 && !bandera) {
            menu.crearImagenes();
            bandera = true;
        }
    }

    public boolean getBandera() {
        return bandera;
    }
}