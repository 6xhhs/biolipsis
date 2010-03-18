package menu;

import menu.Menu;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class JuegoNuevo extends GameCanvas {
    public Graphics g;
    private Imagenes fondoJuego1,
                     fondoJuego2,
                     fondoJuego3,
                     fondoJuego4;
    private Menu menu;
    private boolean bandera;

    public JuegoNuevo(AppGameCanvas midlet, Menu menu){
	super(true);
        this.menu = menu;
	g = this.getGraphics();
        bandera = true;
	try {
            fondoJuego1 = new Imagenes("/fondoJuego1.png",11);
            fondoJuego2 = new Imagenes("/fondoJuego2.png",12);
            fondoJuego3 = new Imagenes("/fondoJuego3.png",13);
            fondoJuego4 = new Imagenes("/fondoJuego4.png",14);

	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    public void dibujar() {
        fondoJuego1.dibujar8(g);
        fondoJuego2.dibujar5(g);
        fondoJuego3.dibujar10(g);
        fondoJuego4.dibujar9(g);
	flushGraphics();
    }

    public void actualizar() {
        fondoJuego1.mover(5);
        fondoJuego2.mover(5);
        fondoJuego3.mover(5);
        fondoJuego4.mover(5);
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