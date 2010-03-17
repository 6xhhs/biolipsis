package menu;

import menu.Imagenes;
import menu.Menu;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Creditos extends GameCanvas {
    public Graphics g;
    private Imagenes fondoCreditos;
    private Menu menu;
    private boolean bandera;

    public Creditos(AppGameCanvas midlet, Menu menu){
	super(true);
	g = this.getGraphics();
        bandera = true;
        this.menu = menu;
	try {
            fondoCreditos = new Imagenes("/fondoCreditos.png",9);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    public void dibujar() {
        fondoCreditos.dibujar5(g);
	flushGraphics();
    }

    public void actualizar() {
        fondoCreditos.mover(4);
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
