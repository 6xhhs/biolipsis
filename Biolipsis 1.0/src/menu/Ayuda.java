package menu;

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
            fondoAyuda = new Imagenes("/fondoAyuda.png",16,0,-180);
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
        fondoAyuda.mover(4);
        int estado = menu.getKeyStates();
        if(estado == 0) {
            bandera = false;
        }
        if((estado & FIRE_PRESSED) != 0 && !bandera) {
            menu.pintar();
            bandera = true;
        }
    }

    public boolean getBandera() {
        return bandera;
    }
}