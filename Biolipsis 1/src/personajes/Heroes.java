package personajes;

import java.io.IOException;
import motor.Juego;

public class Heroes extends Personaje {

    public static final int alto = 37;
    public static final int ancho = 56;
    private static final int[] secuenciaCaminar = {0, 0, 1, 1, 2, 2, 3, 3};
    private static final int[] secuenciaDisparo = {4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7};
    private static final int[] secuenciaBomba = {8,8,8,8,8,9,9,9,9,9,10,10,10,10,10,11,11,11,11,11,12,12,12,12,12};
    private static final int[] secuenciaMuerto = {13,13,13,14,14,14,15,15,15};

    public static int vida;
    public static final int velocidad = 3;
    private boolean banderaColision;

    public Heroes(String archivito) throws IOException {
        super(archivito, ancho, alto, secuenciaCaminar);
        vida = 7;
        banderaColision = false;
    }

    public void calcularDano() {
        vida = vida - 1;
    }

    public void calcularRecuperacion() {
        if (vida < 7) {
            vida = vida + 1;
        } else if (vida >= 7);
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
            juego.setBandera(false);
            juego.setBanderaDisparo(false);
        }
    }

    public void colisionesDanio(Enemigos zombie1, Enemigos zombie2, Enemigos zombie3){
        if((collidesWith(zombie1, true)||collidesWith(zombie2, true)||collidesWith(zombie3, true))&&!banderaColision){
            banderaColision=true;
            calcularDano();
        }
        if(!(collidesWith(zombie1, true)||collidesWith(zombie2, true)||collidesWith(zombie3, true))){
            banderaColision=false;
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

    public int [] getSecuenciaMuerto() {
        return secuenciaMuerto;
    }

    public int [] getSecuenciaBomba() {
        return secuenciaBomba;
    }

}
