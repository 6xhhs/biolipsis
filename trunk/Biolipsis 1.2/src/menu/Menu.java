package menu;

import imagenes.Imagenes;
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
    private Inicio menuInicio;
    private Ayuda menuAyuda;
    private Creditos menuCreditos;
    private Puntajes menuPuntajes;
    private Salir menuSalir;
    private Imagenes logoTec,puntajes, ayuda, creditos, salir, juego, highLight, titulo, tituloJuego1,
                     tituloJuego2, tituloJuego3, tituloJuego4, tituloPuntajes, tituloAyuda, tituloCreditos,
                     tituloSalir, fondo1, fondo2, fondo3, fondo4;
    private boolean cambio;
    private boolean bandera;
    public boolean terminar;
    private boolean seleccionPersonaje;
    private boolean inicioJuego;

    public Menu(Biolipsis midlet){
	super(true);
	this.midlet = midlet;
	g = this.getGraphics();
        setFullScreenMode(true);
        bandera = false;
        terminar = false;
        cambio = false;
        seleccionPersonaje = false;
        menuInicio = null;
        menuAyuda = null;
        menuCreditos = null;
        menuPuntajes = null;
        menuSalir = null;
        crearImagenes();
	try {
            logoTec = new Imagenes("/logoTec.jpg",0,0,0);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
        animador = new AnimadorJuego(this);
	animador.iniciar();
    }

    public void dibujarLogoTec() {
        try {
            logoTec.dibujar(g);
            Thread.sleep(1500);
            flushGraphics();
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
    }
    public void dibujar() {
        if(logoTec != null) {
            dibujarLogoTec();
        }
        logoTec = null;
        if (fondo4 != null) {
            fondo1.dibujar(g);
            fondo2.dibujar(g);
            fondo3.dibujar(g);
            fondo4.dibujar(g);
            if (fondo4.getY() >= 108) {
                puntajes.dibujar(g);
                if (highLight.getPrioridad() == puntajes.getPrioridad()) {
                    tituloPuntajes.dibujar(g);
                }

                ayuda.dibujar(g);
                if (highLight.getPrioridad() == ayuda.getPrioridad()) {
                        tituloAyuda.dibujar(g);
                }
                
                creditos.dibujar(g);
                if (highLight.getPrioridad() == creditos.getPrioridad()) {
                    tituloCreditos.dibujar(g);
                }

                salir.dibujar(g);
                if (highLight.getPrioridad() == salir.getPrioridad()) {
                    tituloSalir.dibujar(g);
                }
                
                juego.dibujar(g);
                if (puntajes.getX() <= 20) {
                    if (highLight.getPrioridad() == juego.getPrioridad()) {
                        tituloJuego1.dibujar(g);
                        tituloJuego2.dibujar(g);
                        tituloJuego3.dibujar(g);
                        tituloJuego4.dibujar(g);
                    }
                }

                highLight.dibujar(g);
                titulo.dibujar(g);
            }
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

        if(menuInicio != null) {
            menuInicio.g = this.getGraphics();
            menuInicio.dibujar();
        }

	flushGraphics();
    }

    public void actualizar() {
        if(fondo1 != null) {
            fondo1.mover(10);
            fondo2.mover(10);
            fondo3.mover(10);
            fondo4.mover(10);
            puntajes.mover(2);
            ayuda.mover(2);
            creditos.mover(2);
            salir.mover(2);
            titulo.mover(2);
            menuPuntajes = null;
            menuAyuda = null;
            menuCreditos = null;
            menuSalir = null;
            menuInicio = null;
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
        if(menuInicio != null) {
            menuInicio.actualizar();
            /*if(menuInicio.isBanderaInicioJuego()) {
                animador.terminar();
                cambio = true;
            }*/
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
                    menuInicio = new Inicio(this);
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
        logoTec = null;
        puntajes = null;
        ayuda = null;
        creditos = null;
        salir = null;
        juego = null;
        highLight = null;
        titulo = null;
        fondo1 = null;
        fondo2 = null;
        fondo3 = null;
        fondo4 = null;
        tituloJuego1 = null;
        tituloJuego2 = null;
        tituloJuego3 = null;
        tituloJuego4 = null;
        tituloPuntajes = null;
        tituloAyuda = null;
        tituloCreditos = null;
        tituloSalir = null;
    }

    public void crearImagenes() {
        try {
            titulo = new Imagenes("/titulo.png",0,33,-30);
            puntajes = new Imagenes("/puntajes.png",1,75,125);
            ayuda = new Imagenes("/ayuda.png",2,75,125);
            creditos = new Imagenes("/creditos.png",3,75,125);
            salir = new Imagenes("/salir.png",4,75,125);
            juego = new Imagenes("/inicio.png",5,75,125);
            highLight = new Imagenes("/cursor.png",5,75,125);
            tituloJuego1 = new Imagenes("/tituloInicio1.png",7,60,76);
            tituloJuego2 = new Imagenes("/tituloInicio2.png",8,135,109);
            tituloJuego3 = new Imagenes("/tituloInicio2.png",9,25,108);
            tituloJuego4 = new Imagenes("/tituloInicio1.png",10,60,185);
            tituloPuntajes = new Imagenes("/tituloPuntajes.png",11,60,76);
            tituloAyuda = new Imagenes("/tituloAyuda.png",12,135,109);
            tituloCreditos = new Imagenes("/tituloCreditos.png",13,60,185);
            tituloSalir = new Imagenes("/tituloSalir.png",14,25,108);
            fondo1 = new Imagenes("/fondoPrincipal1.jpg",50,0,280);
            fondo2 = new Imagenes("/fondoPrincipal2.jpg",51,-180,0);
            fondo3 = new Imagenes("/fondoPrincipal3.jpg",52,280,110);
            fondo4 = new Imagenes("/fondoPrincipal4.jpg",53,90,-180);
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

    public boolean isSeleccionPersonaje() {
        return seleccionPersonaje;
    }

    public void setSeleccionPersonaje(boolean seleccionPersonaje) {
        this.seleccionPersonaje = seleccionPersonaje;
        if(this.isSeleccionPersonaje()) {
            animador.terminar();
            cambio = true;
        }
    }

    /*public boolean isInicioJuego() {
        return inicioJuego;
    }

    public void setInicioJuego(boolean inicioJuego) {
        this.inicioJuego = inicioJuego;
        if(this.isSeleccionPersonaje()) {
            animador.terminar();
            cambio = true;
        }
    }*/

}