
package personajes;

import elementos.Jeringa;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import mapas.MapaNivel1;
import mapas.MapaNivel2y3;
import motor.Juego;

public class ZombieMayorflu extends Enemigos implements ZombieMoviles{

    private static final String archivito="/hombreZombie.png";
    private static int vida=2;
    private static final int vidatotal=2;

    public ZombieMayorflu() throws IOException{
        super(archivito);
        setFrameSequence(getSecuenciaCaminar());
    }

    public void mover(Juego juego, boolean bandera, /*MapaNivel2y3 mapa2y3*/ MapaNivel1 mapa1, Heroes personaje){
        int x=getX();
        int y=getY();
        nextFrame();
        if(personaje.getX()<getX()){
            setTransform(TRANS_NONE);
            setPosition(x, y);
            moverX(-velocidad);
        }
        if(personaje.getX()>getX()){
            setTransform(TRANS_MIRROR);
            setPosition(x, y);
            moverX(velocidad);
        }
        if(personaje.getY()<getY()){
            setPosition(x, y);
            moverY(-velocidad);
        }
        if(personaje.getY()>getY()){
            setPosition(x, y);
            moverY(velocidad);
        }

    }

    public void calcularDanio(){
        vida=vida-1;
    }

    public int getVida() {
        return vida;
    }

    public void restaurarVida(){
        vida=vidatotal;
    }

    /*public void mover(Juego juego, boolean bandera, MapaNivel1 mapa1, Heroes personaje) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/

}

