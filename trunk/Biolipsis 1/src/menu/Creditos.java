package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Diego Zamora, Enrique Garcia, Fernanda Martinez
 */
public class Creditos extends GameCanvas {
    /**
     * Elementos que permiten animar al menu de creditos
     */
    public Graphics g;
    private Imagenes credito,credito1,credito2,credito3,credito4,credito5;
    private Menu menu;
    private boolean bandera;
    private Imagenes [] creditos;
    private int i;

    /**
     *
     * @param menu Recibe como parametro al menu para poder hacer uso del teclado
     */
    public Creditos(Menu menu){
	super(true);
	g = this.getGraphics();
        bandera = true;
        this.menu = menu;
        i=0;
	crearImagenes();
        creditos = new Imagenes[6];
        creditos[0]=credito;
        creditos[1]=credito1;
        creditos[2]=credito2;
        creditos[3]=credito3;
        creditos[4]=credito4;
        creditos[5]=credito5;
        
    }

    /**
     * Permite estar dibujando constantemente al menu de ayuda
     */
    public void dibujar() {
        try{
            creditos[i].dibujar(g);
        }catch (ArrayIndexOutOfBoundsException e){
            i=0;
        }
    }

    /**
     *
     * @throws Exception
     */
    public void actualizar() throws Exception {
        credito.mover(8);
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
     *Permite la creacion de las imagenes del menu de creditos
     */
    public void crearImagenes() {
        try {
            credito = new Imagenes("/creditos1.jpg",17,-180,0);
            credito1 = new Imagenes("/creditos2.jpg",0,0,0);
            credito2 = new Imagenes("/creditos3.jpg",0,0,0);
            credito3 = new Imagenes("/creditos4.jpg",0,0,0);
            credito4 = new Imagenes("/creditos5.jpg",0,0,0);
            credito5 = new Imagenes("/creditos6.jpg",0,0,0);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }
}