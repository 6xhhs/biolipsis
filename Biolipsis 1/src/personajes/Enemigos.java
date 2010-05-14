
package personajes;

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import motor.AdministradorJuego;
import motor.Juego;
import personajes.Personaje;

public class Enemigos extends Personaje{

    public static final int ALTO = 41;
    public static final int ANCHO = 36;
    public static final int VELOCIDAD = 2;
    private static final int[] SECUENCIA_CAMINAR = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11};
    private static final int[] SECUENCIA_MORIR = {12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,
                                               30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,
                                               48,49,50,51,52,53,54,55};

    public Enemigos(String archivito) throws IOException{
        super(archivito, ANCHO, ALTO, SECUENCIA_CAMINAR);
    }

    public int[] getSecuenciaCaminar(){
        return SECUENCIA_CAMINAR;
    }

    public int[] getSecuenciaMorir(){
        return SECUENCIA_MORIR;
    }

    public void morir(Juego juego, AdministradorJuego admin, ZombieMoviles zombie) {
        if (getFrameSequenceLength() < SECUENCIA_MORIR.length) {
            setFrameSequence(SECUENCIA_MORIR);
        }
            nextFrame();
        if (getFrame() == getFrameSequenceLength() - 1) {
            setFrameSequence(getSecuenciaCaminar());
            setPosition(admin.getDesplazamiento() + (3 * juego.ANCHO), getY());
            zombie.setBanderaMorir(false);
        }
    }

}

