package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Creditos extends GameCanvas {
    public Graphics g;
    private Imagenes fondoCreditos;
    private Menu menu;
    private boolean bandera;

    public Creditos(Menu menu){
	super(true);
	g = this.getGraphics();
        bandera = true;
        this.menu = menu;
	try {
            fondoCreditos = new Imagenes("/fondoSubmenus.jpg",17,-180,0);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    public void dibujar() {
        fondoCreditos.dibujar(g);
	flushGraphics();
    }

    public void actualizar() {
        fondoCreditos.mover(8);
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