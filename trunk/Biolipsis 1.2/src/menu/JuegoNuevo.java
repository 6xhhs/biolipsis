package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class JuegoNuevo extends GameCanvas {
    public Graphics g;
    private Imagenes fondoJuego1,fondoJuego2,regresar,
                     highLight,juegoNuevo,continuar;
    private Menu menu;
    private Inicio inicio;
    private SeleccionPersonaje seleccion;
    private boolean tecla;

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

    public void dibujar() {
        //g.setColor(0xFFFFFFF);
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if(fondoJuego1 != null) {
            fondoJuego1.dibujar(g);
            fondoJuego2.dibujar(g);
            if(fondoJuego2.getX()<=5) {
                juegoNuevo.dibujar(g);
                continuar.dibujar(g);
                regresar.dibujar(g);
                highLight.dibujar(g);
            }
        }
        if(seleccion != null) {
            seleccion.g = this.g;
            seleccion.dibujar();
        }
	flushGraphics();
    }

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
                }
            }
        }
    }

    public void borrarTodo() {
        fondoJuego1 = null;
        fondoJuego2 = null;
        regresar = null;
        highLight = null;
        juegoNuevo = null;
        continuar = null;
    }

    public void crearImagenes() {
        try {
            fondoJuego1 = new Imagenes("/fondoJuego1.png",39,-150,-151);
            fondoJuego2 = new Imagenes("/fondoJuego2.png",40,150,149);
            regresar = new Imagenes("/regresar.png",43,90,80);
            highLight = new Imagenes("/cursorSubmenus.png",43,90,80);
            juegoNuevo = new Imagenes("/juegoNuevo.png",44,90,80);
            continuar = new Imagenes("/continuar.png",45,90,80);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }
}
