package menu;

import motor.Animable;
import motor.AnimadorJuego;
import motor.Biolipsis;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public class Menu extends GameCanvas implements Animable {
    private Biolipsis midlet;
    private Graphics g;
    private AnimadorJuego animador;
    private JuegoNuevo menuJuego;
    private Ayuda menuAyuda;
    private Creditos menuCreditos;
    private Puntajes menuPuntajes;
    private Salir menuSalir;
    private Imagenes puntajes, ayuda, creditos,
                     salir, juego, highLight,
                     titulo, fondo, tituloJuego1,
                     tituloJuego2, tituloJuego3,
                     tituloJuego4, tituloPuntajes,
                     tituloAyuda, tituloCreditos,
                     tituloSalir;
    private boolean cambio;
    private boolean bandera;
    private boolean cambio2;
    public boolean terminar;

    public Menu(Biolipsis midlet){
	super(true);
	this.midlet = midlet;
	g = this.getGraphics();
        setFullScreenMode(true);
        bandera = false;
        terminar = false;
        cambio = false;
        cambio2 = false;
        menuJuego = null;
        menuAyuda = null;
        menuCreditos = null;
        menuPuntajes = null;
        menuSalir = null;

	try {
            titulo = new Imagenes("/titulo2.png",0,33,0);
            puntajes = new Imagenes("/puntaje.jpg",1,75,125);
            ayuda = new Imagenes("/ayuda.jpg",2,75,125);
            creditos = new Imagenes("/creditos.jpg",3,75,125);
            salir = new Imagenes("/salir.jpg",4,75,125);
            juego = new Imagenes("/juego.jpg",5,75,125);
            highLight = new Imagenes("/highLight.png",5,75,125);
            fondo = new Imagenes("/fondoJuego.png",6,0,0);
            tituloJuego1 = new Imagenes("/tituloJuego.png",7,60,76);
            tituloJuego2 = new Imagenes("/tituloNuevo.png",8,135,109);
            tituloJuego3 = new Imagenes("/tituloNuevo.png",9,25,108);
            tituloJuego4 = new Imagenes("/tituloJuego.png",10,60,185);
            tituloPuntajes = new Imagenes("/tituloPuntajes.png",11,60,76);
            tituloAyuda = new Imagenes("/tituloAyuda.png",12,135,109);
            tituloCreditos = new Imagenes("/tituloCreditos.png",13,25,108);
            tituloSalir = new Imagenes("/tituloSalir.png",14,60,185);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
        animador = new AnimadorJuego(this);
	animador.iniciar();
    }

    public void dibujar() {
	g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if(fondo != null) {
            fondo.dibujar(g);
        }
        if(puntajes != null) {
            puntajes.dibujar(g);
            if(highLight.getPrioridad()==puntajes.getPrioridad()) {
                tituloPuntajes.dibujar(g);
            }
        }
        if(ayuda != null) {
            ayuda.dibujar(g);
            if(highLight.getPrioridad()==ayuda.getPrioridad()) {
                tituloAyuda.dibujar(g);
            }
        }

        if(creditos != null) {
            creditos.dibujar(g);
            if(highLight.getPrioridad()==creditos.getPrioridad()) {
                tituloCreditos.dibujar(g);
            }
        }

        if(salir != null) {
            salir.dibujar(g);
            if(highLight.getPrioridad()==salir.getPrioridad()) {
                tituloSalir.dibujar(g);
            }
        }
        if(juego != null) {
            juego.dibujar(g);
            if(puntajes.getX()<=20)
            if(highLight.getPrioridad()==juego.getPrioridad()) {
                tituloJuego1.dibujar(g);
                tituloJuego2.dibujar(g);
                tituloJuego3.dibujar(g);
                tituloJuego4.dibujar(g);
            }
        }
        if(highLight != null) {
            highLight.dibujar(g);
        }
        if(titulo != null) {
            titulo.dibujar(g);
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
            puntajes.mover(2);
            ayuda.mover(2);
            creditos.mover(2);
            salir.mover(2);
            titulo.mover(2);
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
            if(menuSalir.getSalir()) {
                midlet.terminar();
                animador.terminar();
                terminar = true;
            }
        }
        if(menuJuego != null) {
            menuJuego.actualizar();
            if(menuJuego.getBanderaLibre()) {
                animador.terminar();
                cambio = true;
            }
        }
        if(highLight != null) {
            int estado = getKeyStates();
            if(estado == 0) {
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
                    menuJuego = new JuegoNuevo(this);
                    borrarTodo();
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
                    menuCreditos = new Creditos(this);
                    borrarTodo();
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
                    menuPuntajes = new Puntajes(this);
                    borrarTodo();
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
                    menuSalir = new Salir(this);
                    borrarTodo();
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
                    menuAyuda = new Ayuda(this);
                    borrarTodo();
                    bandera = true;
                }
            }
        }
    }

    public void borrarTodo() {
        puntajes = null;
        ayuda = null;
        creditos = null;
        salir = null;
        juego = null;
        highLight = null;
        titulo = null;
        fondo = null;
        tituloJuego1 = null;
        tituloJuego2 = null;
        tituloJuego3 = null;
        tituloJuego4 = null;
        tituloPuntajes = null;
        tituloAyuda = null;
        tituloCreditos = null;
        tituloSalir = null;
    }

    public void pintar() {
        g = this.getGraphics();
        try {
            titulo = new Imagenes("/titulo2.png",0,33,0);
            puntajes = new Imagenes("/puntaje.jpg",1,75,125);
            ayuda = new Imagenes("/ayuda.jpg",2,75,125);
            creditos = new Imagenes("/creditos.jpg",3,75,125);
            salir = new Imagenes("/salir.jpg",4,75,125);
            juego = new Imagenes("/juego.jpg",5,75,125);
            highLight = new Imagenes("/highLight.png",5,75,125);
            fondo = new Imagenes("/fondoJuego.png",6,0,0);
            tituloJuego1 = new Imagenes("/tituloJuego.png",7,60,76);
            tituloJuego2 = new Imagenes("/tituloNuevo.png",8,135,109);
            tituloJuego3 = new Imagenes("/tituloNuevo.png",9,25,108);
            tituloJuego4 = new Imagenes("/tituloJuego.png",10,60,185);
            tituloPuntajes = new Imagenes("/tituloPuntajes.png",11,60,76);
            tituloAyuda = new Imagenes("/tituloAyuda.png",12,135,109);
            tituloCreditos = new Imagenes("/tituloCreditos.png",13,25,108);
            tituloSalir = new Imagenes("/tituloSalir.png",14,60,185);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean getCambio() {
        return cambio;
    }

    public boolean getBandera() {
        return bandera;
    }

    public boolean getCambio2() {
        return cambio2;
    }
}