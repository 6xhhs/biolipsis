package motor;

import menu.Imagenes;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import personajes.Heroes;

public class Juego extends GameCanvas implements Animable
{
    private AdministradorJuego admin;
    private AnimadorJuego animador;
    private Imagenes fondo;
    private int velocidad=2;
    private boolean bandera=false;
    private Graphics g;
    private int ANCHO;
    private int ALTO;
    private Biolipsis midlet;
    private int[] secuenciaDisparo={4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7};
    private boolean banderaDisparo=false;

    public Juego(Biolipsis midlet) {

        super(true);
        g = getGraphics();
        ANCHO = getWidth();
        ALTO = getHeight();
        this.midlet = midlet;
        try {
            fondo = new Imagenes("/pin_soi_ninja.png",0,0,0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        admin = new AdministradorJuego(ANCHO,ALTO);

        animador = new AnimadorJuego(this);
        animador.iniciar();
    }

    public void dibujar() {
        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, getWidth(), getHeight());

        fondo.dibujar(g);
        admin.paint(g);


        g.setColor(0x00FF00);
        g.drawString("D-Salir", ANCHO/2, ALTO, Graphics.BOTTOM|Graphics.HCENTER);

        flushGraphics();
    }

    public void actualizar() {
        int tecla = getKeyStates();
        if(tecla==0){
            if(!banderaDisparo){
                admin.personaje.setFrameSequence(admin.personaje.getSecuencia());
            }
            bandera=false;
        }
        int x = admin.personaje.getX();
        int y = admin.personaje.getY();
        if ((tecla & RIGHT_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.setTransform(admin.personaje.TRANS_NONE);
            admin.personaje.setPosition(x, y);
            admin.personaje.nextFrame();
            admin.moverX(velocidad);
        } else if ((tecla & LEFT_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.setTransform(admin.personaje.TRANS_MIRROR);
            admin.personaje.setPosition(x, y);
            admin.personaje.nextFrame();
            admin.moverX(-velocidad);
        } else if((tecla & FIRE_PRESSED)!=0 && !bandera){
            bandera=true;
            banderaDisparo=true;
            admin.personaje.setFrameSequence(secuenciaDisparo);
        } if ((tecla & UP_PRESSED) != 0) {
            admin.personaje.nextFrame();
            admin.moverY(-velocidad);
        } if ((tecla & DOWN_PRESSED) != 0) {
            admin.personaje.nextFrame();
            admin.moverY(velocidad);
        } else if ((tecla & GAME_D_PRESSED) != 0) {
            terminar();
        }
        if (admin.personaje.collidesWith(admin.mapa, false)) {
            admin.personaje.setPosition(x, y);
        }
        if(banderaDisparo){
            admin.personaje.nextFrame();
            if(admin.personaje.getFrame()==admin.personaje.getFrameSequenceLength()-1){
                admin.personaje.setFrameSequence(admin.personaje.getSecuencia());
                banderaDisparo=false;
            }
        }
    }

    public void terminar() {

        animador.terminar();
        midlet.terminar();
    }
}
