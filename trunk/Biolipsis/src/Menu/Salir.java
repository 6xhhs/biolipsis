package menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Salir extends GameCanvas {
    public Graphics g;
    private Imagenes fondoSalir, si, no;
    private Menu menu;
    private boolean bandera;

    public Salir(AppGameCanvas midlet, Menu menu){
	super(true);
	g = this.getGraphics();
        bandera = true;
        this.menu = menu;
	try {
            fondoSalir = new Imagenes("/fondoSalir.png",10);
            si = new Imagenes("/si.jpg",15);
            no = new Imagenes("/no.jpg",16);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    public void dibujar() {
        fondoSalir.dibujar7(g);
        si.dibujar11(g);
	flushGraphics();
    }

    public void actualizar() {
        fondoSalir.mover(4);
        si.mover(5);
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