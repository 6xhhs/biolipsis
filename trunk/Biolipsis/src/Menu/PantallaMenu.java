//version 1.0
package Menu;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class PantallaMenu extends GameCanvas implements CommandListener {
    private AppGameCanvas midlet;
    private Command comandoSalir;
    private Graphics g;
    private Imagenes a, b, c, d, e, f,titulo;
    private Animador animar;
    private boolean bandera=false;

    public PantallaMenu(AppGameCanvas midlet){
	super(true);
	this.midlet = midlet;
	g = this.getGraphics();
	try {
            titulo = new Imagenes("/titulo.jpg",0);
            a = new Imagenes("/a.jpg",1);
            b = new Imagenes("/b.jpg",3);
            c = new Imagenes("/c.jpg",2);
            d = new Imagenes("/d.jpg",4);
            e = new Imagenes("/e.jpg",5);
            f = new Imagenes("/jailai.jpg",5);
	} catch(IOException e){
            System.out.println ("nelll");
            e.printStackTrace();
        }

        animar = new Animador(this);
	animar.iniciar();
    }

    public void dibujar() {
	g.setColor(0x000000);
	g.fillRect(0, 0, this.getWidth(), this.getHeight());
        a.dibujar(g);
        b.dibujar(g);
        c.dibujar(g);
        d.dibujar(g);
        e.dibujar(g);
        f.dibujar2(g);
        titulo.dibujar3(g);

	flushGraphics();
    }

   public void actualizar() {
        a.mover(1);
        b.mover(1);
        c.mover(1);
        d.mover(1);
        titulo.mover2(1);
        int estado = getKeyStates();
        if(estado==0){
            bandera=false;
        }
        if (f.getPrioridad() == e.getPrioridad()) {
            if ((estado & UP_PRESSED) != 0 && !bandera) {

                f.setPrioridad(a.getPrioridad());
                f.setPosicion(a.getX(), a.getY());
                bandera = true;
            }
            if ((estado & DOWN_PRESSED) != 0 && !bandera) {
                f.setPrioridad(c.getPrioridad());
                f.setPosicion(c.getX(), c.getY());
                bandera = true;
            }
            if ((estado & FIRE_PRESSED) != 0 && !bandera) {
            }
        } else if (f.getPrioridad() == c.getPrioridad()) {
            if ((estado & UP_PRESSED) != 0 && !bandera) {

                f.setPrioridad(e.getPrioridad());
                f.setPosicion(e.getX(), e.getY());
                bandera = true;
            }
            if ((estado & RIGHT_PRESSED) != 0 && !bandera) {
                f.setPrioridad(d.getPrioridad());
                f.setPosicion(d.getX(), d.getY());
                bandera = true;
            }
            if ((estado & FIRE_PRESSED) != 0) {
            }
        } else if (f.getPrioridad() == a.getPrioridad()) {
            if ((estado & RIGHT_PRESSED) != 0 && !bandera) {

                f.setPrioridad(b.getPrioridad());
                f.setPosicion(b.getX(), b.getY());
                bandera = true;
            }
            if ((estado & DOWN_PRESSED) != 0 && !bandera) {
                f.setPrioridad(e.getPrioridad());
                f.setPosicion(e.getX(), e.getY());
                bandera = true;
            }
            if ((estado & FIRE_PRESSED) != 0 && !bandera) {
            }
        } else if (f.getPrioridad() == d.getPrioridad()) {
            if ((estado & LEFT_PRESSED) != 0 && !bandera) {

                f.setPrioridad(c.getPrioridad());
                f.setPosicion(c.getX(), c.getY());
                bandera = true;
            }
            if ((estado & UP_PRESSED) != 0 && !bandera) {
                f.setPrioridad(e.getPrioridad());
                f.setPosicion(e.getX(), e.getY());
                bandera = true;
            }
            if ((estado & FIRE_PRESSED) != 0 && !bandera) {
            }
        } else if (f.getPrioridad() == b.getPrioridad()) {
            if ((estado & LEFT_PRESSED) != 0 && !bandera) {

                f.setPrioridad(a.getPrioridad());
                f.setPosicion(a.getX(), a.getY());
                bandera = true;
            }
            if ((estado & DOWN_PRESSED) != 0 && !bandera) {
                f.setPrioridad(e.getPrioridad());
                f.setPosicion(e.getX(), e.getY());
                bandera = true;
            }
            if ((estado & FIRE_PRESSED) != 0 && !bandera) {
            }
        }

    }

    public void commandAction(Command c, Displayable d){
        if(c==comandoSalir){
            midlet.terminar();
        }
    }
}
