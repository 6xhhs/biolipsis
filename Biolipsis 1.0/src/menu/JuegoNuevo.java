// FALTA IMPLEMENTAR EL TRUE DENTRO DE ESTA CLASE

package menu;

import motor.Biolipsis;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class JuegoNuevo extends GameCanvas {
    public Graphics g;
    private Imagenes fondoJuego1,fondoJuego2,fondoJuego3,
                     fondoJuego4,regresar,highLight,
                     modoHistoria,modoLibre;
    private Menu menu;
    private boolean bandera;
    private boolean nuevo;

    public JuegoNuevo(Menu menu){
	super(true);
        this.menu = menu;
	g = this.getGraphics();
        bandera = true;
        nuevo = false;
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
        fondoJuego1.dibujar(g);
        fondoJuego2.dibujar(g);
        fondoJuego3.dibujar(g);
        fondoJuego4.dibujar(g);
        if(fondoJuego4.getY()>=108) {
            modoLibre.dibujar(g);
            modoHistoria.dibujar(g);
            regresar.dibujar(g);
            highLight.dibujar(g);
        }
	flushGraphics();
    }

    public void actualizar() {
        fondoJuego1.mover(5);
        fondoJuego2.mover(5);
        fondoJuego3.mover(5);
        fondoJuego4.mover(5);
        modoHistoria.mover(1);
        modoLibre.mover(1);
        int estado = menu.getKeyStates();
        if(estado == 0) {
            bandera = false;
        }
        if(highLight.getPrioridad()==regresar.getPrioridad()) {
            if((estado & DOWN_PRESSED) !=0 && !bandera) {
                highLight.setPosicion(modoHistoria.getX(), modoHistoria.getY());
                highLight.setPrioridad(modoHistoria.getPrioridad());
                bandera = true;
            }
            if((estado & UP_PRESSED) != 0 && !bandera) {
                highLight.setPosicion(modoLibre.getX(), modoLibre.getY());
                highLight.setPrioridad(modoLibre.getPrioridad());
                bandera = true;
            }
            if((estado & FIRE_PRESSED) != 0 && !bandera) {
                menu.pintar();
                bandera = true;
            }
        } else if(highLight.getPrioridad()==modoHistoria.getPrioridad()) {
            if((estado & UP_PRESSED) != 0 && !bandera) {
                highLight.setPosicion(regresar.getX(), regresar.getY());
                highLight.setPrioridad(regresar.getPrioridad());
                bandera = true;
            }
            if((estado & FIRE_PRESSED) != 0 && !bandera) {
                bandera=true;//
                nuevo = true;
            }
        } else if(highLight.getPrioridad()==modoLibre.getPrioridad()) {
            if((estado & DOWN_PRESSED) != 0 && !bandera) {
                highLight.setPosicion(regresar.getX(), regresar.getY());
                highLight.setPrioridad(regresar.getPrioridad());
                bandera = true;
            }
            if((estado & FIRE_PRESSED) != 0 && !bandera) {
            }
        }
    }

    public boolean getBandera() {
        return bandera;
    }

    public boolean getNuevo() {
        return nuevo;
    }
}