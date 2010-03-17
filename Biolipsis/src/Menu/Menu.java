package menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Menu extends GameCanvas {
    private AppGameCanvas midlet;
    private JuegoNuevo menuJuego;
    private Ayuda menuAyuda;
    private Creditos menuCreditos;
    private Puntajes menuPuntajes;
    private Salir menuSalir;
    private Graphics g;
    private Imagenes puntajes, ayuda, creditos,
                     salir, juego, highLight,
                     titulo, fondo;
    private Animador animar;
    private boolean bandera;

    public Menu(AppGameCanvas midlet){
	super(true);
	this.midlet = midlet;
	g = this.getGraphics();
        bandera = false;
        menuJuego = null;
        menuAyuda = null;
        menuCreditos = null;
        menuPuntajes = null;

	try {
            titulo = new Imagenes("/titulo.jpg",0);
            puntajes = new Imagenes("/puntaje.jpg",1);
            ayuda = new Imagenes("/ayuda.jpg",2);
            creditos = new Imagenes("/creditos.jpg",3);
            salir = new Imagenes("/salir.jpg",4);
            juego = new Imagenes("/juego.jpg",5);
            highLight = new Imagenes("/highLight.png",5);
            fondo = new Imagenes("/fondo.png",6);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }

        animar = new Animador(this);
	animar.iniciar();
    }

    public void dibujar() {
	g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if(fondo != null) {
            fondo.dibujar4(g);
        }
        if(puntajes != null) {
            puntajes.dibujar(g);
        }
        if(ayuda != null) {
            ayuda.dibujar(g);
        }
        if(creditos != null) {
            creditos.dibujar(g);
        }
        if(salir != null) {
            salir.dibujar(g);
        }
        if(juego != null) {
            juego.dibujar(g);
        }
        if(highLight != null) {
            highLight.dibujar(g);
        }
        if(titulo != null) {
            titulo.dibujar3(g);
        }
        if(menuPuntajes != null) {
            menuPuntajes.g = this.getGraphics();
            menuPuntajes.dibujar();
        }
        if(menuAyuda != null) {
            menuAyuda.g = this.getGraphics();
            menuAyuda.dibujar();
        }
        if(menuCreditos != null) {
            menuCreditos.g = this.getGraphics();
            menuCreditos.dibujar();
        }
        if(menuSalir != null) {
            menuSalir.g = this.getGraphics();
            menuSalir.dibujar();
        }
        if(menuJuego != null) {
            menuJuego.g = this.getGraphics();
            menuJuego.dibujar();
        }
	flushGraphics();
    }

    public void actualizar() {
        if(juego != null) {
            puntajes.mover(1);
            ayuda.mover(1);
            creditos.mover(1);
            salir.mover(1);
            titulo.mover(1);
            menuPuntajes = null;
            menuAyuda = null;
            menuCreditos = null;
            menuSalir = null;
            menuJuego = null;
        }
        if(menuPuntajes != null) {
            menuPuntajes.actualizar();
        }
        if(menuAyuda != null) {
            menuAyuda.actualizar();
        }
        if(menuCreditos != null) {
            menuCreditos.actualizar();
        }
        if(menuSalir != null) {
            menuSalir.actualizar();
        }
        if(menuJuego != null) {
            menuJuego.actualizar();
        }
        if(highLight != null) {
            int estado=getKeyStates();
             if(estado==0) {
                bandera = false;
            }
            if(highLight.getPrioridad()==juego.getPrioridad()) {
                if((estado&UP_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(puntajes.getX()-1, puntajes.getY()-1);
                    highLight.setPrioridad(puntajes.getPrioridad());
                    bandera = true;
                }
                if((estado&DOWN_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(creditos.getX()-1, creditos.getY()-1);
                    highLight.setPrioridad(creditos.getPrioridad());
                    bandera = true;
                }
                if((estado&FIRE_PRESSED) !=0 && !bandera) {
                    menuJuego = new JuegoNuevo(midlet, this);
                    this.borrarTodo();
                    bandera = true;
                }
            } else if(highLight.getPrioridad()==creditos.getPrioridad()) {
                if((estado&UP_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(juego.getX()-1, juego.getY()-1);
                    highLight.setPrioridad(juego.getPrioridad());
                    bandera = true;
                }
                if((estado&RIGHT_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(salir.getX()-1, salir.getY()-1);
                    highLight.setPrioridad(salir.getPrioridad());
                    bandera = true;
                }
                if((estado&FIRE_PRESSED)!=0) {
                    menuCreditos = new Creditos(midlet, this);
                    this.borrarTodo();
                    bandera = true;
                }
            } else if(highLight.getPrioridad()==puntajes.getPrioridad()) {
                if((estado&RIGHT_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(ayuda.getX()-1, ayuda.getY()-1);
                    highLight.setPrioridad(ayuda.getPrioridad());
                    bandera = true;
                }
                if((estado&DOWN_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(juego.getX()-1, juego.getY()-1);
                    highLight.setPrioridad(juego.getPrioridad());
                    bandera = true;
                }
                if((estado&FIRE_PRESSED)!=0) {
                    menuPuntajes = new Puntajes(midlet, this);
                    this.borrarTodo();
                    bandera = true;
                }
            } else if(highLight.getPrioridad()==salir.getPrioridad()) {
                if((estado&LEFT_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(creditos.getX()-1, creditos.getY()-1);
                    highLight.setPrioridad(creditos.getPrioridad());
                    bandera = true;
                }
                if((estado&UP_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(juego.getX()-1, juego.getY()-1);
                    highLight.setPrioridad(juego.getPrioridad());
                    bandera = true;
                }
                if((estado&FIRE_PRESSED)!=0) {
                    menuSalir = new Salir(midlet, this);
                    this.borrarTodo();
                    bandera = true;
                }
            } else if(highLight.getPrioridad()==ayuda.getPrioridad()) {
                if((estado&LEFT_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(puntajes.getX()-1, puntajes.getY()-1);
                    highLight.setPrioridad(puntajes.getPrioridad());
                    bandera = true;
                }
                if((estado&DOWN_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(juego.getX()-1, juego.getY()-1);
                    highLight.setPrioridad(juego.getPrioridad());
                    bandera = true;
                }
                if((estado&FIRE_PRESSED)!=0) {
                    menuAyuda = new Ayuda(midlet, this);
                    this.borrarTodo();
                    bandera = true;
                }
            }
        }
    }
    public boolean  getBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public void borrarTodo() {
        fondo = null;
        titulo = null;
        puntajes = null;
        ayuda = null;
        creditos = null;
        salir = null;
        juego = null;
        highLight = null;
    }

    public void pintar() {
        g = this.getGraphics();
        try {
            titulo = new Imagenes("/titulo.jpg",0);
            puntajes = new Imagenes("/puntaje.jpg",1);
            ayuda = new Imagenes("/ayuda.jpg",2);
            creditos = new Imagenes("/creditos.jpg",3);
            salir = new Imagenes("/salir.jpg",4);
            juego = new Imagenes("/juego.jpg",5);
            highLight = new Imagenes("/highLight.png",5);
            fondo = new Imagenes("/fondo.png",6);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}