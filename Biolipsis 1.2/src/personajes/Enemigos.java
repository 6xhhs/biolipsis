
package personajes;

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import motor.AdministradorJuego;
import motor.Juego;
import personajes.Personaje;

public class Enemigos extends Personaje{

    public static final int alto=41;
    public static final int ancho=36;
    public static final int velocidad=1;
    private static final int[] secuenciacaminar={0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11};
    private static final int[] secuenciamorir={12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,
                                               30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,
                                               48,49,50,51,52,53,54,55};
    private Random r;

    public Enemigos(String archivito) throws IOException{
        super(archivito, ancho, alto, secuenciacaminar);
        r = new Random();
    }

    public int[] getSecuenciaCaminar(){
        return secuenciacaminar;
    }

    public int[] getSecuenciaMorir(){
        return secuenciamorir;
    }

    public void morir(Juego juego, AdministradorJuego admin){
        if(getFrameSequenceLength()<secuenciamorir.length){
            System.out.println("cambio secuencia");
            setFrameSequence(secuenciamorir);
        }
        nextFrame();
        if (getFrame() == getFrameSequenceLength() - 1) {
            setFrameSequence(getSecuenciaCaminar());
            setPosition(r.nextInt(1900)+200, getY());
            //setPosition(admin.getDesplazamiento()+(2*juego.ANCHO), getY());
            juego.setBanderaMorir(false);
        }
    }

}

