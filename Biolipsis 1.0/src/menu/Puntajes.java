package menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Puntajes extends GameCanvas {
    public Graphics g;
    private Imagenes fondoPuntajes;
    private Menu menu;
    private boolean bandera;

    public Puntajes(Menu menu){
	super(true);
	g = this.getGraphics();
        bandera = true;
        this.menu = menu;
	try {
            fondoPuntajes = new Imagenes("/fondoPuntajes.png",15,0,280);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    public void dibujar() {
        fondoPuntajes.dibujar(g);
	flushGraphics();
    }

    public void actualizar() {
        fondoPuntajes.mover(4);
        int estado = menu.getKeyStates();
        if(estado == 0) {
            bandera = false;
        }
        if((estado & GAME_B_PRESSED) != 0 && !bandera) {
            menu.pintar();
            bandera = true;
        }
    }

    public boolean getBandera() {
        return bandera;
    }
}