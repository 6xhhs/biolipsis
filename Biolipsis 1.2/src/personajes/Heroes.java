package personajes;

import elementos.Virus;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import motor.AdministradorJuego;
import motor.Juego;

public class Heroes extends Personaje {

    public static final int alto = 37;
    public static final int ancho = 53;
    private static final int[] secuenciaCaminar = {0, 0, 1, 1, 2, 2, 3, 3};
    private static final int[] secuenciaDisparo={4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7};
    private static final int[] secuenciaHerido = {0,0,8,8,1,1,8,8,2,2,8,8,3,3,8,8,0,0,8,8,1,1,8,8,2,2,8,8,3,3,8,8};
    public static int vida;
    public static final int velocidad = 3;

    public Heroes(String archivito) throws IOException {
        super(archivito, ancho, alto, secuenciaCaminar);
        vida = 7;
    }

    public void calcularDano() {
        vida = vida - 1;
    }

    public void calcularRecuperacion() {
        if (vida < 7) {
            vida = vida + 1;
        } else if (vida == 7);
            vida = 7;
    }

        
    public void moverDerecha() {
        int x = getX();
        int y = getY();
        setTransform(TRANS_NONE);
        setPosition(x, y);
        nextFrame();
        moverX(velocidad);
    }

    public void moverIzquierda() {
        int x = getX();
        int y = getY();
        setTransform(TRANS_MIRROR);
        setPosition(x, y);
        nextFrame();
        moverX(-velocidad);
    }

    public void moverArriba() {
        nextFrame();
        moverY(-velocidad);
    }

    public void moverAbajo() {
        nextFrame();
        moverY(velocidad);
    }

    public void disparo(Juego juego) {
        nextFrame();
        if (getFrame() == getFrameSequenceLength() - 1) {
            setFrameSequence(getSecuencia());
            vida++;
            juego.setBandera(false);
            juego.setBanderaDisparo(false);
        }
    }

    public void herido(Juego juego) {
        nextFrame();
        if(getFrame() == getFrameSequenceLength() - 1) {
            setFrameSequence(getSecuencia());
            juego.setBanderaParpadeo(false);
            juego.setBanderaVirus(false);
        }
    }

    public int[] getSecuencia() {
        return secuenciaCaminar;
    }

    public static int[] getSecuenciaDisparo() {
        return secuenciaDisparo;
    }

    public int [] getSecuenciaHerido() {
        return secuenciaHerido;
    }

}
