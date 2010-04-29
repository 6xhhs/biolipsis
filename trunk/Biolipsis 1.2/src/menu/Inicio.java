package menu;

import imagenes.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Inicio extends GameCanvas {
    public Graphics g;
    private Imagenes fondoInicio1,fondoInicio2,fondoInicio3,
                     fondoInicio4,regresar,highLight,
                     modoHistoria,modoLibre;
    private Menu menu;
    private JuegoNuevo juego;
    private SeleccionPersonaje seleccion;
    private boolean tecla;

    public Inicio(Menu menu){
	super(true);
        setFullScreenMode(true);
        this.menu = menu;
	g = getGraphics();
        tecla = true;
        juego = null;
        seleccion = null;
        crearImagenes();
    }

    public void dibujar() {
        
        if (fondoInicio4 != null) {
            fondoInicio1.dibujar(g);
            fondoInicio2.dibujar(g);
            fondoInicio3.dibujar(g);
            fondoInicio4.dibujar(g);
            if(fondoInicio4.getX() >= 79) {
                modoLibre.dibujar(g);
                modoHistoria.dibujar(g);
                regresar.dibujar(g);
                highLight.dibujar(g);
            }
        }
        if(juego != null) {
            juego.g = this.g;
            juego.dibujar();
        }
        if(seleccion != null) {
            seleccion.g = this.g;
            seleccion.dibujar();
        }
	flushGraphics();
    }

    public void actualizar() {
        if(fondoInicio1 != null) {
            fondoInicio1.mover(10);
            fondoInicio2.mover(10);
            fondoInicio3.mover(10);
            fondoInicio4.mover(10);
            modoHistoria.mover(2);
            modoLibre.mover(2);
            juego = null;
            seleccion = null;
        }
        if(juego !=  null) {
            juego.actualizar();
        }
        if(seleccion != null) {
            seleccion.actualizar();
        }
        if(highLight != null) {
            int estado = menu.getKeyStates();
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
                if((estado & FIRE_PRESSED) != 0 && !tecla) {  // regresar
                    menu.crearImagenes();
                    borrarTodo();
                    tecla = true;
                }
            } else if(highLight.getPrioridad()==modoHistoria.getPrioridad()) {
                if((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(regresar.getX(), regresar.getY());
                    highLight.setPrioridad(regresar.getPrioridad());
                    tecla = true;
                }
                if((estado & FIRE_PRESSED) != 0 && !tecla) {
                    juego = new JuegoNuevo(menu,this);
                    borrarTodo();
                    tecla = true;
                }
            } else if(highLight.getPrioridad()==modoLibre.getPrioridad()) {
                if((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(regresar.getX(), regresar.getY());
                    highLight.setPrioridad(regresar.getPrioridad());
                    tecla = true;
                }
                if((estado & FIRE_PRESSED) != 0 && !tecla) {
                    seleccion = new SeleccionPersonaje(menu,null,this);
                    borrarTodo();
                    tecla = true;
                }
            }
        }
    }

    public void borrarTodo() {
        fondoInicio1 = null;
        fondoInicio2 = null;
        fondoInicio3 = null;
        fondoInicio4 = null;
        regresar = null;
        highLight = null;
        modoHistoria = null;
        modoLibre = null;
    }

    public void crearImagenes() {
        try {
            fondoInicio1 = new Imagenes("/fondoInicio1.jpg",23,90,0);
            fondoInicio2 = new Imagenes("/fondoInicio2.jpg",24,88,110);
            fondoInicio3 = new Imagenes("/fondoInicio3.jpg",25,0,0);
            fondoInicio4 = new Imagenes("/fondoInicio4.jpg",26,-2,110);
            regresar = new Imagenes("/regresar.png",27,90,80);
            highLight = new Imagenes("/cursorSubmenus.png",27,90,80);
            modoHistoria = new Imagenes("/modoHistoria.png",28,90,80);
            modoLibre = new Imagenes("/modoLibre.png",29,90,80);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }
}