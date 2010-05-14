package menu;

import imagenes.Imagenes;
import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import motor.Animable;
import motor.AnimadorJuego;
import motor.Biolipsis;
import motor.Juego;

/**
 *
 * @author Diego Zamora, Enrique Garcia, Fernanda Martinez
 */
public class Pausa extends GameCanvas implements Animable{
    /**
     * Elementos que permiten darle una animacion al menu de pausa
     */
    public Graphics g;
    private boolean tecla;
    private AnimadorJuego animador;
    private Juego juego;
    private Biolipsis midlet;
    private FileConnection archivos;
    private String datos;
    private Imagenes fondo, highLight, continuar, reiniciar, salvar, irAMenu;

    /**
     *
     * @param juego Recibe este parametro para poder iniciar la pausa en el juego
     * @param midlet Para poder pausar el juego cuando exista algun evento del SO
     * @param datos Permite salvar los datos como son nivel y puntaje del usuario
     */
    public Pausa(Juego juego, Biolipsis midlet, String datos){
        super(true);
        setFullScreenMode(true);
        this.juego=juego;
        this.midlet=midlet;
        g=getGraphics();
        tecla=false;
        this.datos=datos;
        animador= new AnimadorJuego(this);
        animador.iniciar();
        try {
            fondo = new Imagenes("/fondoMenuSeleccionNivel.jpg",34,0,0);
            irAMenu = new Imagenes("/menuPrincipalOpcion.jpg",35,4,166);
            highLight = new Imagenes("/cursorSeleccionNivel.png",36,0,0);
            continuar = new Imagenes("/continuarOpcion.jpg",36,4,4);
            reiniciar = new Imagenes("/reiniciarOpcion.jpg",37,4,58);
            salvar = new Imagenes("/salvarOpcion.jpg",38,4,112);
	} catch(IOException e){
            System.out.println ("");
            e.printStackTrace();
        }
    }

    /**
     * Permite estar dibujando constantemente los elementos del menu
     */
    public void dibujar() {
        if(fondo != null) {
            fondo.dibujar(g);
            salvar.dibujar(g);
            irAMenu.dibujar(g);
            continuar.dibujar(g);
            reiniciar.dibujar(g);
            highLight.dibujar(g);
        }
	flushGraphics();
    }

    /**
     * Permite el manejo del teclado en este menu
     */
    public void actualizar() {
        if (highLight != null) {
            int estado = getKeyStates();
            if (estado == 0) {
                tecla = false;
            }
            if (highLight.getPrioridad() == irAMenu.getPrioridad()) {
                if ((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(salvar.getX() - 4, salvar.getY() - 4);
                    highLight.setPrioridad(salvar.getPrioridad());
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    try {
                        midlet.reiniciarDesdePausa();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    tecla = true;
                }
            } else if (highLight.getPrioridad() == salvar.getPrioridad()) {
                if ((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(irAMenu.getX() - 4, irAMenu.getY() - 4);
                    highLight.setPrioridad(irAMenu.getPrioridad());
                    tecla = true;
                }
                if ((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(reiniciar.getX() - 4, reiniciar.getY() - 4);
                    highLight.setPrioridad(reiniciar.getPrioridad());
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    try {
                        escrituraJuego();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    tecla = true;
                }
            } else if (highLight.getPrioridad() == reiniciar.getPrioridad()) {
                if ((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(salvar.getX() - 4, salvar.getY() - 4);
                    highLight.setPrioridad(salvar.getPrioridad());
                    tecla = true;
                }
                if ((estado & UP_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(continuar.getX() - 4, continuar.getY() - 4);
                    highLight.setPrioridad(continuar.getPrioridad());
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    juego.reiniciarNivel();
                    juego.reanudarJuego();
                    midlet.despausa();
                    tecla = true;
                }
            } else if (highLight.getPrioridad() == continuar.getPrioridad()) {
                if ((estado & DOWN_PRESSED) != 0 && !tecla) {
                    highLight.setPosicion(reiniciar.getX() - 4, reiniciar.getY() - 4);
                    highLight.setPrioridad(reiniciar.getPrioridad());
                    tecla = true;
                }
                if ((estado & FIRE_PRESSED) != 0 && !tecla) {
                    juego.reanudarJuego();
                    tecla = true;
                    midlet.despausa();
                }
            } else if((estado & GAME_D_PRESSED) != 0 && !tecla){

            }
        }

    }

    /**
     *
     * @throws IOException Lanza una excepcion en caso de no poder guardar ningun dato
     */
    public void escrituraJuego() throws IOException {
        byte[] data=this.datos.getBytes();
        this.archivos = (FileConnection) Connector.open(Menu.archivoSalvado, Connector.READ_WRITE);
        if(!archivos.exists()){
            archivos.create();
        }
        OutputStream printer = archivos.openOutputStream();
        printer.write(data);
        printer.close();
        archivos.close();

    }
}