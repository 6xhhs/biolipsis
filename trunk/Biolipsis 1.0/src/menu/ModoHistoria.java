package menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class ModoHistoria extends GameCanvas {
    public Graphics g;
    private Imagenes fondoJuego1,fondoJuego2,fondoJuego3,
                     fondoJuego4,regresar,highLight,
                     modoHistoria,modoLibre;
    private JuegoNuevo juego;
    private boolean tecla;

    public ModoHistoria(JuegoNuevo juego){
	super(true);
        this.juego = juego;
	g = super.getGraphics();
        tecla = true;
	try {
            fondoJuego1 = new Imagenes("/fondoJuego1.png",21,0,280);
            fondoJuego2 = new Imagenes("/fondoJuego2.png",22,-180,0);
            fondoJuego3 = new Imagenes("/fondoJuego3.png",23,280,110);
            fondoJuego4 = new Imagenes("/fondoJuego4.png",24,90,-180);
            regresar = new Imagenes("/regresar.png",25,90,80);
            highLight = new Imagenes("/highlight2.png",25,90,80);
            modoHistoria = new Imagenes("/modoHistoria.png",26,90,80);
            modoLibre = new Imagenes("/modoLibre.png",27,90,80);

	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    public void dibujar() {
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if(fondoJuego1 != null) {
            fondoJuego1.dibujar(g);
        }
        if(fondoJuego2 != null) {
            fondoJuego2.dibujar(g);
        }
        if(fondoJuego3 != null) {
            fondoJuego3.dibujar(g);
        }
        if(fondoJuego4 != null) {
            fondoJuego4.dibujar(g);
            if(fondoJuego4.getY()>=108) {
                modoLibre.dibujar(g);
                modoHistoria.dibujar(g);
                regresar.dibujar(g);
                highLight.dibujar(g);
            }
        }
	flushGraphics();
    }

    public void actualizar() {
        if(fondoJuego1 != null) {
            fondoJuego1.mover(10);
            fondoJuego2.mover(10);
            fondoJuego3.mover(10);
            fondoJuego4.mover(10);
            modoHistoria.mover(2);
            modoLibre.mover(2);
        }

        int estado = juego.getKeyStates();
        if(estado == 0) {
            tecla = false;
        }
        if(highLight.getPrioridad()==regresar.getPrioridad()) {
            if((estado & DOWN_PRESSED) !=0 && !tecla) {
                highLight.setPosicion(modoHistoria.getX(), modoHistoria.getY());
                highLight.setPrioridad(modoHistoria.getPrioridad());
                tecla = true;
            }
            if((estado & UP_PRESSED) != 0 && !tecla) {
                highLight.setPosicion(modoLibre.getX(), modoLibre.getY());
                highLight.setPrioridad(modoLibre.getPrioridad());
                tecla = true;
            }
            if((estado & FIRE_PRESSED) != 0 && !tecla) {
            }
        } else if(highLight.getPrioridad()==modoHistoria.getPrioridad()) {
            if((estado & UP_PRESSED) != 0 && !tecla) {
                highLight.setPosicion(regresar.getX(), regresar.getY());
                highLight.setPrioridad(regresar.getPrioridad());
                tecla = true;
            }
            if((estado & FIRE_PRESSED) != 0 && !tecla) {
            }
        } else if(highLight.getPrioridad()==modoLibre.getPrioridad()) {
            if((estado & DOWN_PRESSED) != 0 && !tecla) {
                highLight.setPosicion(regresar.getX(), regresar.getY());
                highLight.setPrioridad(regresar.getPrioridad());
                tecla = true;
            }
            if((estado & FIRE_PRESSED) != 0 && !tecla) {
            }
        }
    }

    public void borrarTodo() {
        fondoJuego1 = null;
        fondoJuego2 = null;
        fondoJuego3 = null;
        fondoJuego4 = null;
        regresar = null;
        highLight = null;
        modoHistoria = null;
        modoLibre = null;
    }

    public void pintar() {
        try {
            fondoJuego1 = new Imagenes("/fondoJuego1.png",21,0,280);
            fondoJuego2 = new Imagenes("/fondoJuego2.png",22,-180,0);
            fondoJuego3 = new Imagenes("/fondoJuego3.png",23,280,110);
            fondoJuego4 = new Imagenes("/fondoJuego4.png",24,90,-180);
            regresar = new Imagenes("/regresar.png",25,90,80);
            highLight = new Imagenes("/highlight2.png",25,90,80);
            modoHistoria = new Imagenes("/modoHistoria.png",26,90,80);
            modoLibre = new Imagenes("/modoLibre.png",27,90,80);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }
    public Graphics getGraphics() {
        return g;
    }
}