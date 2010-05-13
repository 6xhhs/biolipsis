package menu;

import imagenes.Imagenes;
import motor.Animable;
import motor.AnimadorJuego;
import motor.Biolipsis;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Diego Zamora, Enrique Garcia, Fernanda Martinez
 */
public class Menu extends GameCanvas implements Animable {
    private Biolipsis midlet;
    private Graphics g;
    private AnimadorJuego animador;
    private Inicio menuInicio;
    private Ayuda menuAyuda;
    private Creditos menuCreditos;
    private Puntajes menuPuntajes;
    private Salir menuSalir;
    private FileConnection archivos;
    private InputStream scanner;
    private byte [] data;
    private FileConnection archivos1;
    private InputStream scanner1;
    private byte [] data1;
    private Imagenes logoTec,puntajes, ayuda, creditos, salir, juego, highLight, titulo, tituloJuego1,
                     tituloJuego2, tituloJuego3, tituloJuego4, tituloPuntajes, tituloAyuda, tituloCreditos,
                     tituloSalir, fondo1, fondo2, fondo3, fondo4;
    private boolean cambio;
    private boolean bandera;
    /**
     * La bandera terminar te permite finalizar el menu e iniciar el juego
     * La seleccion de personaje te permite elegir entre dos personajes
     */
    public boolean terminar;
    private boolean seleccionPersonaje;
    private boolean inicioJuego;
    private int nivel;
    private int highscores;
    /**
     * Te permite hacer lectura de archivo
     */
    public static final String archivoScores = "file:///e:/Score.txt";
    /**
     * Las constantes de abajo te permiten posicionar cada una de las opciones
     * del menu principal.
     */
    public static final String archivoSalvado ="file:///e:/Scorcito.txt";
    private static final int POSICION_A = 75;
    private static final int POSICION_B = 125;
    private static final int POSICION_C = 60;
    private static final int POSICION_D = 0;
    private static final int POSICION_E = 33;
    private static final int POSICION_F = -30;
    private static final int POSICION_G = 76;
    private static final int POSICION_H = 135;
    private static final int POSICION_I = 109;
    private static final int POSICION_J = 25;
    private static final int POSICION_K = 108;
    private static final int POSICION_L = 185;
    private static final int POSICION_M = 180;
    private static final int POSICION_N = 280;
    private static final int POSICION_O = 110;

    /**
     *
     * @param midlet Esto te permite poder correr en el midlet el menu
     * @throws Exception Esta excepcion te permite detectar si una imagen no
     * se cargo.
     */
    public Menu(Biolipsis midlet) throws Exception{
	super(true);
	this.midlet = midlet;
	g = this.getGraphics();
        setFullScreenMode(true);
        bandera = false;
        terminar = false;
        cambio = false;
        seleccionPersonaje = false;
        inicioJuego=false;
        nivel = 1;
        highscores=0;
        menuInicio = null;
        menuAyuda = null;
        menuCreditos = null;
        menuPuntajes = null;
        menuSalir = null;
        crearImagenes();
	try {
            logoTec = new Imagenes("/logoTec.jpg",0,0,60);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
        animador = new AnimadorJuego(this);
	animador.iniciar();
    }

    /**
     * Te permite dibujar el splash screen del Tec
     */
    public void dibujarLogoTec() {
        try {
            logoTec.dibujar(g);
            Thread.sleep(1500);
            flushGraphics();
            if(!midlet.estaReproduciendo()) {
                midlet.reproducir("/Menu.mid");
            }
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
    }
    /**
     * Este metodo te permite dibujar todas las opciones del menu
     * asi como los respectivos fondos.
     */
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

    /**
     * El metodo actualizar se encarga del manejo del teclado y de movimiento
     * de las imagenes
     */
    public void actualizar() {
        if(fondo1 != null) {
            fondo1.mover(10);
            fondo2.mover(10);
            fondo3.mover(10);
            fondo4.mover(10);
            puntajes.mover(1);
            ayuda.mover(1);
            creditos.mover(1);
            salir.mover(1);
            titulo.mover(1);
            menuPuntajes = null;
            menuAyuda = null;
            menuCreditos = null;
            menuSalir = null;
            menuInicio = null;
        }
        if(menuPuntajes != null) {
            try {
                menuPuntajes.actualizar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(menuAyuda != null) {
            try {
                menuAyuda.actualizar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(menuCreditos != null) {
            try {
                menuCreditos.actualizar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(menuSalir != null) {
            try {
                menuSalir.actualizar();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(menuSalir.getSalir()) {
                midlet.terminar();
                animador.terminar();
                midlet.detenerMusica();
                terminar = true;
            }
        }
        if(menuInicio != null) {
            try {
                menuInicio.actualizar();
            } catch (Exception ex) {
                ex.printStackTrace();
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
                    highLight.setPosicion(salir.getX(), salir.getY());
                    highLight.setPrioridad(salir.getPrioridad());
                    bandera = true;
                }
                if((estado&FIRE_PRESSED) !=0 && !bandera) {
                    menuInicio = new Inicio(this);
                    borrarTodo();
                    bandera = true;
                }
            } else if(highLight.getPrioridad()==creditos.getPrioridad()) {
                if((estado&UP_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(juego.getX(), juego.getY());
                    highLight.setPrioridad(juego.getPrioridad());
                    bandera = true;
                }
                if((estado&LEFT_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(salir.getX(), salir.getY());
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
                    highLight.setPosicion(ayuda.getX(), ayuda.getY());
                    highLight.setPrioridad(ayuda.getPrioridad());
                    bandera = true;
                }
                if((estado&DOWN_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(juego.getX(), juego.getY());
                    highLight.setPrioridad(juego.getPrioridad());
                    bandera = true;
                }
                if((estado&FIRE_PRESSED)!=0) {
                    try {
                        menuPuntajes = new Puntajes(this, lecturaScores());
                    } catch (IOException ex) {
                        menuPuntajes = new Puntajes(this, "0");
                    }
                    borrarTodo();
                    bandera = true;
                }
            } else if(highLight.getPrioridad()==salir.getPrioridad()) {
                if((estado&RIGHT_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(creditos.getX(), creditos.getY());
                    highLight.setPrioridad(creditos.getPrioridad());
                    bandera = true;
                }
                if((estado&UP_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(juego.getX(), juego.getY());
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
                    highLight.setPosicion(puntajes.getX(), puntajes.getY());
                    highLight.setPrioridad(puntajes.getPrioridad());
                    bandera = true;
                }
                if((estado&DOWN_PRESSED)!=0 && !bandera) {
                    highLight.setPosicion(juego.getX(), juego.getY());
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

    /**
     * El metodo borrar todo te permite eliminar las imagenes en pantalla
     */
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

    /**
     * El metodo crearImagenes crea las imagenes del juego
     */
    public void crearImagenes() {
        try {
        titulo = new Imagenes("/titulo.png", 0, POSICION_E, POSICION_F);
        puntajes = new Imagenes("/puntajes.png",1,POSICION_A,POSICION_B);
        ayuda = new Imagenes("/ayuda.png",2,POSICION_A,POSICION_B);
        creditos = new Imagenes("/creditos.png",3,POSICION_A,POSICION_B);
        salir = new Imagenes("/salir.png",4,POSICION_A,POSICION_B);
        juego = new Imagenes("/inicio.png",5,POSICION_A,POSICION_B);
        highLight = new Imagenes("/cursor.png",5,POSICION_A,POSICION_B);
        tituloJuego1 = new Imagenes("/tituloInicio1.png",7,POSICION_C,POSICION_G);
        tituloJuego2 = new Imagenes("/tituloInicio2.png",8,POSICION_H,POSICION_I);
        tituloJuego3 = new Imagenes("/tituloInicio2.png",9,POSICION_J,POSICION_K);
        tituloJuego4 = new Imagenes("/tituloInicio1.png",10,POSICION_C,POSICION_L);
        tituloPuntajes = new Imagenes("/tituloPuntajes.png",11,POSICION_C,POSICION_G);
        tituloAyuda = new Imagenes("/tituloAyuda.png",12,POSICION_H,POSICION_I);
        tituloCreditos = new Imagenes("/tituloCreditos.png",13,POSICION_C,POSICION_L);
        tituloSalir = new Imagenes("/tituloSalir.png",14,POSICION_J,POSICION_K);
        fondo1 = new Imagenes("/fondoPrincipal1.jpg",50,POSICION_D,POSICION_N);
        fondo2 = new Imagenes("/fondoPrincipal2.jpg",51,-POSICION_M,POSICION_D);
        fondo3 = new Imagenes("/fondoPrincipal3.jpg",52,POSICION_N,POSICION_O);
        fondo4 = new Imagenes("/fondoPrincipal4.jpg",53,POSICION_M/2,-POSICION_M);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * El metodo getCambio elimina del midlet el menu y corre el juego
     * @return
     */
    public boolean getCambio() {
        return cambio;
    }

    /**
     * Se encarga del manejo de teclado
     * @return
     */
    public boolean getBandera() {
        return bandera;
    }

    /**
     * Se encarga de la seleccion de personaje
     * @return
     */
    public boolean isSeleccionPersonaje() {
        return seleccionPersonaje;
    }

    /**
     * se encarga de cambiar la seleccion del personaje
     * @param seleccionPersonaje
     */
    public void setSeleccionPersonaje(boolean seleccionPersonaje) {
        this.seleccionPersonaje = seleccionPersonaje;
    }

    /**
     * Cambia la bandera para iniciar el juego
     * @param inicioJuego
     */
    public void setInicioJuego(boolean inicioJuego) {
        this.inicioJuego = inicioJuego;
        if(this.inicioJuego){
            animador.terminar();
            cambio = true;
        }
    }

    /**
     * Este metodo recoje los datos para almacenar los puntajes
     * @return
     * @throws IOException
     */
    public String lecturaScores() throws IOException {
        this.archivos = (FileConnection) Connector.open(archivoScores, Connector.READ_WRITE);
        this.scanner = archivos.openInputStream();
        data = new byte[512];
        scanner.read(data);
        scanner.close();
        archivos.close();
        String score=new String(data);
        return score;
    }

    /**
     * Este metodo contiene los daots guardados
     * @return
     * @throws IOException
     */
    public String lecturaSalvado() throws IOException{
        this.archivos1 = (FileConnection) Connector.open(archivoSalvado, Connector.READ_WRITE);
        this.scanner1 = archivos1.openInputStream();
        data1 = new byte[512];
        scanner1.read(data1);
        scanner1.close();
        archivos1.close();
        String score=new String(data1);
        return score;
    }

    /**
     * Convierte un string a entero
     * @param s
     * @return
     */
    public int convertirSAI(String s){
        return Integer.parseInt(s);
    }

    /**
     * Regresa el puntaje
     * @return
     */
    public int getHighscores() {
        return highscores;
    }

    /**
     * Contiene el valor de la variable highscores
     * @param highscores
     */
    public void setHighscores(int highscores) {
        this.highscores = highscores;
    }

    /**
     * Regresa el nivel en el que te encuentras
     * @return
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Cambia el nivel
     * @param nivel
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

}