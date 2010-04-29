package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Salir extends GameCanvas {
    public Graphics g;
    private Imagenes fondoSalir, si, no, highLight,
                        tituloDeseas, tituloSalir;
    private Menu menu;
    private boolean bandera, salir;

    public Salir(Menu menu) {
	super(true);
	g = this.getGraphics();
        bandera = true;
        salir = false;
        this.menu = menu;
	try {
            fondoSalir = new Imagenes("/fondoSalir.jpg",18,280,0);
            si = new Imagenes("/si.png",19,-35,10);
            no = new Imagenes("/no.png",20,150,150);
            highLight = new Imagenes("/highlight2.png",20,150,150);
            tituloDeseas = new Imagenes("/tituloDeseas.png",21,15,90); 
            tituloSalir = new Imagenes("/tituloSalir2.png",22,15,180); 

	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    public void dibujar() {
        fondoSalir.dibujar(g);
        if(fondoSalir.getX()<=-1) {
            si.dibujar(g);
            no.dibujar(g);
            highLight.dibujar(g);
            tituloDeseas.dibujar(g);
            tituloSalir.dibujar(g);
        }
	flushGraphics();
    }

    public void actualizar() {
        fondoSalir.mover(8);
        si.mover(1);
        highLight.mover(1);
        no.mover(1);
        int estado = menu.getKeyStates();
        if(estado == 0) {
            bandera = false;
        }
        if(highLight.getPrioridad()==si.getPrioridad()) {
            if((estado & DOWN_PRESSED) !=0 && !bandera) {
                highLight.setPosicion(no.getX(), no.getY());
                highLight.setPrioridad(no.getPrioridad());
                bandera = true;
            }
            if((estado & FIRE_PRESSED) !=0 && !bandera) {
                salir = true;
                bandera = true;
            }
        } else if(highLight.getPrioridad()==no.getPrioridad()) {
            if((estado & UP_PRESSED) !=0 && !bandera) {
                highLight.setPosicion(si.getX(), si.getY());
                highLight.setPrioridad(si.getPrioridad());
                bandera = true;
            }
            if((estado & FIRE_PRESSED) !=0 && !bandera) {
                menu.crearImagenes();
                bandera = true;
            }
        }
    }

    public boolean getSalir() {
        return salir;
    }
}