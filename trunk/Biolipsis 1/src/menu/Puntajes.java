package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Diego Zamora, Enrique Garcia, Fernanda Martinez
 */
public class Puntajes extends GameCanvas {
    /**
     *
     */
    public Graphics g;
    private Imagenes fondoPuntajes;
    private Menu menu;
    private boolean bandera;
    private String highScores;

    /**
     *Se utiliza el teclado desde menu
     * @param menu
     * @param highScores
     */
    public Puntajes(Menu menu, String highScores){
	super(true);
	g = this.getGraphics();
        bandera = true;
        this.highScores = highScores;
        this.menu = menu;
	try {
            fondoPuntajes = new Imagenes("/fondoPuntajes.jpg",15,0,280);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    /**
     *Dibuja las imagenes en pantalla
     */
    public void dibujar() {
        fondoPuntajes.dibujar(g);
        g.setColor(0xFFFFFF);
        g.drawString("PUNTAJES ALTOS", 30, 0, Graphics.TOP | Graphics.LEFT);
        g.drawString("1 puntaje mayor: " + highScores, 0, 30, Graphics.TOP | Graphics.LEFT);
        g.drawString("2 puntaje mayor: 120", 0, 60, Graphics.TOP | Graphics.LEFT);
        g.drawString("3 puntaje mayor: 50", 0, 90, Graphics.TOP | Graphics.LEFT);
	flushGraphics();
    }

    /**
     *Se encarga de actualizar pantallas y revisa si algun archivo
     * si no se cargo correctamente
     * @throws Exception
     */
    public void actualizar() throws Exception {
        fondoPuntajes.mover(8);
        int estado = menu.getKeyStates();
        if(estado == 0) {
            bandera = false;
        }
        if((estado & GAME_D_PRESSED) != 0 && !bandera) {
            menu.crearImagenes();
            bandera = true;
        }
    }

    /**
     *Bandera de manejo de teclado
     * @return
     */
    public boolean getBandera() {
        return bandera;
    }
}