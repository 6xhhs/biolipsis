package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Diego Zamora, Enrique Garcia, Fernanda Martinez
 */
public class SeleccionDeNivel extends GameCanvas {
    /**
     *
     */
    public Graphics g;
    private Imagenes fondo,regresar,highLight,
                     nivelUno,nivelDos,nivelTres;
    private SeleccionPersonaje seleccion;
    private Menu menu;
    private Inicio inicio;
    private boolean tecla;

    /**
     *
     * @param menu
     * @param seleccion
     * @param inicio
     */
    public SeleccionDeNivel(Menu menu, SeleccionPersonaje seleccion, Inicio inicio){
	super(true);
        this.seleccion = seleccion;
        this.menu = menu;
        this.inicio = inicio;
	g = getGraphics();
        tecla = true;
	try {
            fondo = new Imagenes("/fondoMenuSeleccionNivel.jpg",34,0,0);
            regresar = new Imagenes("/regresar3.jpg",35,4,166);
            highLight = new Imagenes("/cursorSeleccionNivel.png",36,0,0);
            nivelUno = new Imagenes("/botonNivelUno.jpg",36,4,4);
            nivelDos = new Imagenes("/botonNivelDos.jpg",37,4,58);
            nivelTres = new Imagenes("/botonNivelTres.jpg",38,4,112);
	} catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void dibujar() {
        if(fondo != null) {
            fondo.dibujar(g);
            regresar.dibujar(g);
            nivelUno.dibujar(g);
            nivelDos.dibujar(g);
            nivelTres.dibujar(g);
            highLight.dibujar(g);
        }
	flushGraphics();
    }

    /**
     *
     */
    public void actualizar() {
        if (highLight != null) {
            int estado = menu.getKeyStates();
            if (estado == 0) {
                tecla = false;
            }
            if (highLight.getPrioridad() == regresar.getPrioridad()) {
                if ((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(nivelTres.getX() - 4, nivelTres.getY() - 4);
                    highLight.setPrioridad(nivelTres.getPrioridad());
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    seleccion.crearImagenes();
                    borrarTodo();
                    tecla = true;
                }
            } else if (highLight.getPrioridad() == nivelTres.getPrioridad()) {
                if ((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(regresar.getX() - 4, regresar.getY() - 4);
                    highLight.setPrioridad(regresar.getPrioridad());
                    tecla = true;
                }
                if ((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(nivelDos.getX() - 4, nivelDos.getY() - 4);
                    highLight.setPrioridad(nivelDos.getPrioridad());
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    menu.setNivel(3);
                    menu.setInicioJuego(true);
                }
            } else if (highLight.getPrioridad() == nivelDos.getPrioridad()) {
                if ((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(nivelTres.getX() - 4, nivelTres.getY() - 4);
                    highLight.setPrioridad(nivelTres.getPrioridad());
                    tecla = true;
                }
                if ((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(nivelUno.getX() - 4, nivelUno.getY() - 4);
                    highLight.setPrioridad(nivelUno.getPrioridad());
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    menu.setNivel(2);
                    menu.setInicioJuego(true);
                }
            } else if (highLight.getPrioridad() == nivelUno.getPrioridad()) {
                if ((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(nivelDos.getX() - 4, nivelDos.getY() - 4);
                    highLight.setPrioridad(nivelDos.getPrioridad());
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    menu.setNivel(1);
                    menu.setInicioJuego(true);
                }
            }
        }
    }

    /**
     *
     */
    public void borrarTodo() {
        fondo = null;
        regresar = null;
        highLight = null;
        nivelUno = null;
        nivelDos = null;
        nivelTres = null;
    }

    /**
     *
     */
    public void pintar() {
        try {
            fondo = new Imagenes("/fondoMenuModoLibre.jpg",34,0,0);
            regresar = new Imagenes("/regresar3.jpg",35,4,166);
            highLight = new Imagenes("/cursorSeleccionNivel.png",36,0,0);
            nivelUno = new Imagenes("/botonNivelUno.jpg",36,4,4);
            nivelDos = new Imagenes("/botonNivelDos.jpg",37,4,58);
            nivelTres = new Imagenes("/botonNivelTres.jpg",38,4,112);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }
}