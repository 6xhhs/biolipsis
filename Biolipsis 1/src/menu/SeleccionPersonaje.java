package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Diego Zamora, Enrique Garcia, Fernanda Martinez
 */
public class SeleccionPersonaje extends GameCanvas {
    /**
     * Elementos que permiten animar al menu de seleccion de personaje
     */
    public Graphics g;
    private Imagenes fondo,regresar,highLight,highLight2,
                     seleccionMaryflu,seleccionDeflu;
    private SeleccionDeNivel nivel;
    private Menu menu;
    private JuegoNuevo juegoNuevo;
    private Inicio inicio;
    private boolean tecla, iniciarJuego;

    /**
     *
     * @param menu Permite el manejo del teclado
     * @param juegoNuevo Permite regresar al menu de juego nuevo si se vino desde ahi
     * @param inicio Permite regresar al menu de inicio si se vino desde ahi
     */
    public SeleccionPersonaje(Menu menu,JuegoNuevo juegoNuevo, Inicio inicio){
	super(true);
        this.menu = menu;
        this.juegoNuevo = juegoNuevo;
        this.inicio = inicio;
        this.iniciarJuego = false;
	g = getGraphics();
        tecla = true;
        nivel = null;
        crearImagenes();
    }

    /**
     * Permite dibujar constantemente a los elementos del menu de seleccion de personaje
     */
    public void dibujar() {
        if(fondo != null) {
            fondo.dibujar(g);
            seleccionMaryflu.dibujar(g);
            seleccionDeflu.dibujar(g);
            regresar.dibujar(g);
            if(highLight2.getPrioridad()==regresar.getPrioridad()) {
                highLight2.dibujar(g);
            }
            if(highLight.getPrioridad()==seleccionMaryflu.getPrioridad() || highLight.getPrioridad()==seleccionDeflu.getPrioridad()) {
                highLight.dibujar(g);
            }
        }
        if(nivel != null) {
            nivel.g = this.g;
            nivel.dibujar();
        }
	flushGraphics();
    }

    /**
     * Permite el manejo del teclado de este menu
     */
    public void actualizar() {
        if(nivel != null) {
            nivel.actualizar();
        }
        if (highLight != null) {
            int estado = menu.getKeyStates();
            if (estado == 0) {
                tecla = false;
            }
            if (highLight2.getPrioridad() == regresar.getPrioridad()) {
                if ((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight2.setPrioridad(seleccionDeflu.getPrioridad());
                    highLight.setPrioridad(seleccionDeflu.getPrioridad());
                    highLight.setPosicion(seleccionDeflu.getX()-5, seleccionDeflu.getY()-5);
                    tecla = true;
                }
                if ((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight2.setPrioridad(seleccionDeflu.getPrioridad());
                    highLight.setPrioridad(seleccionMaryflu.getPrioridad());
                    highLight.setPosicion(seleccionMaryflu.getX()-5, seleccionMaryflu.getY()-5);
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    if(inicio==null) {
                        juegoNuevo.crearImagenes();
                        borrarTodo();
                        tecla = true;
                    } else {
                        inicio.crearImagenes();
                        borrarTodo();
                        tecla = true;
                    }
                }
            } else if (highLight.getPrioridad() == seleccionMaryflu.getPrioridad()) {
                if ((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPrioridad(regresar.getPrioridad());
                    highLight2.setPrioridad(regresar.getPrioridad());
                    highLight2.setPosicion(regresar.getX()-5, regresar.getY()-5);
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    menu.setSeleccionPersonaje(true);
                    if(juegoNuevo == null) {
                        nivel = new SeleccionDeNivel(menu,this,inicio);
                        borrarTodo();
                        tecla = true;
                    }else{
                        menu.setInicioJuego(true);
                    }
                }
            } else if (highLight.getPrioridad() == seleccionDeflu.getPrioridad()) {
                if ((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPrioridad(regresar.getPrioridad());
                    highLight2.setPrioridad(regresar.getPrioridad());
                    highLight2.setPosicion(regresar.getX()-5, regresar.getY()-5);
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    menu.setSeleccionPersonaje(false);
                    if(juegoNuevo == null) {
                        nivel = new SeleccionDeNivel(menu,this,inicio);
                        borrarTodo();
                        tecla = true;
                    }else{
                        menu.setInicioJuego(true);
                    }
                }
            }
        }
    }

    /**
     * Apunta a null todos los elementos cuando se han dejado de utilizar
     */
    public void borrarTodo() {
        fondo = null;
        regresar = null;
        highLight = null;
        highLight2 = null;
        seleccionMaryflu = null;
        seleccionDeflu = null;
    }

    /**
     * Permite crear las imagenes de este menu
     */
    public void crearImagenes() {
        try {
            fondo = new Imagenes("/fondoMenuModoLibre.png",30,0,0);
            regresar = new Imagenes("/regresar2.jpg",31,5,95);
            highLight2 = new Imagenes("/cursorPersonajes1.png",31,0,90);
            highLight = new Imagenes("/cursorPersonajes2.png",31,0,0);
            seleccionMaryflu = new Imagenes("/seleccionMaryflu.jpg",32,5,5);
            seleccionDeflu = new Imagenes("/seleccionDeflu.jpg",33,5,130);
	} catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Para poder iniciar el juego
     */
    public boolean isIniciarJuego() {
        return iniciarJuego;
    }

    /**
     *
     * @param iniciarJuego Para poder cambiar la bandera de inicio de juego
     */
    public void setIniciarJuego(boolean iniciarJuego) {
        this.iniciarJuego = iniciarJuego;
    }
}