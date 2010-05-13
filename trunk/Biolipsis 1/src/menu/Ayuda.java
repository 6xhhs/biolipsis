package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Diego Zamora, Enrique Garcia, Fernanda Martinez
 */
public class Ayuda extends GameCanvas {
    /**
     *
     */
    public Graphics g;
    private Imagenes ayuda1, ayuda2, ayuda3, ayuda4, ayuda5;
    private Menu menu;
    private boolean bandera;
    private Imagenes[] ayudas;
    private int i;

    /**
     *
     * @param menu
     */
    public Ayuda(Menu menu){
	super(true);
	g = this.getGraphics();
        bandera = true;
        this.menu = menu;
        i=0;
	try {
            ayuda1 = new Imagenes("/ayuda1.jpg",16,0,-180);
            ayuda2 = new Imagenes("/ayuda2.jpg",0,0,0);
            ayuda3 = new Imagenes("/ayuda3.jpg",0,0,0);
            ayuda4 = new Imagenes("/ayuda4.jpg",0,0,0);
            ayuda5 = new Imagenes("/ayuda5.jpg",0,0,0);
	} catch(IOException e){
            e.printStackTrace();
        }
        ayudas = new Imagenes[5];
        ayudas[0]=ayuda1;
        ayudas[1]=ayuda2;
        ayudas[2]=ayuda3;
        ayudas[3]=ayuda4;
        ayudas[4]=ayuda5;

    }

    /**
     *
     */
    public void dibujar() {
        try{
            ayudas[i].dibujar(g);
        }catch (ArrayIndexOutOfBoundsException e){
            i=0;
        }
	flushGraphics();
    }

    /**
     *
     * @throws Exception
     */
    public void actualizar() throws Exception {
        ayuda1.mover(8);
        int estado = menu.getKeyStates();
        if(estado == 0) {
            bandera = false;
        }
        if((estado & RIGHT_PRESSED) != 0 && !bandera) {
            i++;
            bandera = true;
        }
        if((estado & LEFT_PRESSED) !=0 && !bandera){
            i--;
            bandera = true;
        }
        if((estado & GAME_D_PRESSED) != 0 && !bandera) {
            menu.crearImagenes();
            bandera = true;
        }
    }

    /**
     *
     * @return
     */
    public boolean getBandera() {
        return bandera;
    }
}