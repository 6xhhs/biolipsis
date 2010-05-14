package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Diego Zamora, Enrique Garcia, Fernanda Martinez
 */
public class JuegoNuevo extends GameCanvas {
    /**
     * Elementos que permiten poder animar al menu de juego nuevo
     */
    public Graphics g;
    private Imagenes fondoJuego1,fondoJuego2,regresar,
                     highLight,juegoNuevo,continuar, tituloContinuar, tituloRegresar, tituloJuegoNuevo;
    private Menu menu;
    private Inicio inicio;
    private SeleccionPersonaje seleccion;
    private boolean tecla;

    /**
     *
     * @param menu Recibe este parametro para poder hacer uso del teclado
     * @param inicio Recibe este parametro para poder hacer un regreso al menu de inicio
     * si se encuentra en la seleccion de personaje
     */
    public JuegoNuevo(Menu menu, Inicio inicio){
	super(true);
        setFullScreenMode(true);
        this.menu = menu;
        this.inicio = inicio;
	g = getGraphics();
        tecla = true;
        seleccion = null;
	crearImagenes();
    }

    /**
     * Permite esta dibujando constantemente a los elementos del menu de juego nuevo
     */
    public void dibujar() {
        if(fondoJuego1 != null) {
            fondoJuego1.dibujar(g);
            fondoJuego2.dibujar(g);
            if(fondoJuego2.getX()<=5) {
                juegoNuevo.dibujar(g);
                if(highLight.getPrioridad() == juegoNuevo.getPrioridad()) {
                    tituloJuegoNuevo.dibujar(g);
                }
                continuar.dibujar(g);
                if(highLight.getPrioridad() == continuar.getPrioridad()) {
                    tituloContinuar.dibujar(g);
                }
                regresar.dibujar(g);
                if(continuar.getY() <= 20) {
                   if(highLight.getPrioridad() == regresar.getPrioridad()) {
                       tituloRegresar.dibujar(g);
                   }
                }
                highLight.dibujar(g);
            }
        }
        if(seleccion != null) {
            seleccion.g = this.g;
            seleccion.dibujar();
        }
	flushGraphics();
    }

    /**
     * Permite el manejo del teclado en este menu
     */
    public void actualizar() {
        if(fondoJuego1 != null) {
            fondoJuego1.mover(10);
            fondoJuego2.mover(10);
            juegoNuevo.mover(2);
            continuar.mover(2);
            seleccion = null;
        }
        if(seleccion !=  null) {
            seleccion.actualizar();
        }

        if(highLight != null) {
            int estado = menu.getKeyStates();
            if(estado == 0) {
                tecla = false;
            }
            if(highLight.getPrioridad()==regresar.getPrioridad()) {
                if((estado & DOWN_PRESSED) !=0 && !tecla) {
                    highLight.setPosicion(juegoNuevo.getX(), juegoNuevo.getY());
                    highLight.setPrioridad(juegoNuevo.getPrioridad());
                    tecla = true;
                }
                if((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(continuar.getX(), continuar.getY());
                    highLight.setPrioridad(continuar.getPrioridad());
                    tecla = true;
                }
                if((estado & FIRE_PRESSED) != 0 && !tecla) {
                    inicio.crearImagenes();
                    borrarTodo();
                    tecla = true;
                }
            } else if(highLight.getPrioridad()==juegoNuevo.getPrioridad()) {
                if((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(regresar.getX(), regresar.getY());
                    highLight.setPrioridad(regresar.getPrioridad());
                    tecla = true;
                }
                if((estado & FIRE_PRESSED) != 0 && !tecla) {
                    seleccion = new SeleccionPersonaje(menu,this,null);
                    borrarTodo();
                    tecla = true;
                }
            } else if(highLight.getPrioridad()==continuar.getPrioridad()) {
                if((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(regresar.getX(), regresar.getY());
                    highLight.setPrioridad(regresar.getPrioridad());
                    tecla = true;
                }
                if((estado & FIRE_PRESSED) != 0 && !tecla) {
                    try {
                        menu.setHighscores(menu.convertirSAI(menu.lecturaSalvado()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    menu.setSeleccionPersonaje(true);
                    menu.setInicioJuego(tecla);
                    tecla = true;
                }
            }
        }
    }

    /**
     * Apunta todo a null cuando no se esta utilizando ninguna imagen de este menu
     */
    public void borrarTodo() {
        fondoJuego1 = null;
        fondoJuego2 = null;
        regresar = null;
        highLight = null;
        juegoNuevo = null;
        continuar = null;
    }

    /**
     * Crea las imagenes necesarias para este menu
     */
    public void crearImagenes() {
        try {
            fondoJuego1 = new Imagenes("/fondoJuego1.png",39,-150,-151);
            fondoJuego2 = new Imagenes("/fondoJuego2.png",40,150,149);
            regresar = new Imagenes("/regresar.png",43,90,80);
            highLight = new Imagenes("/cursorSubmenus.png",43,90,80);
            juegoNuevo = new Imagenes("/juegoNuevo.png",44,90,80);
            continuar = new Imagenes("/continuar.png",45,90,80);
            tituloJuegoNuevo = new Imagenes("/tituloJuegoNuevo.png",200,100,165);
            tituloContinuar = new Imagenes("/tituloContinuar.png",201,100,40);
            tituloRegresar = new Imagenes("/tituloRegresar.png",202,20,100);
	} catch(IOException e){
            e.printStackTrace();
        }
    }
}
