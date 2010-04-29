package personajes;

import java.io.IOException;
import mapas.MapaNivel1;
import mapas.MapaNivel2y3;
import motor.Juego;

public class Zombieflu extends Enemigos implements ZombieMoviles {

    private static final String archivito = "/mujerZombie.png";
    private static int vida=1;
    private static final int vidatotal=1;

    public Zombieflu() throws IOException {
        super(archivito);
        setFrameSequence(getSecuenciaCaminar());
    }

    public void mover(Juego juego, boolean bandera, /*MapaNivel2y3 mapa2y3*/MapaNivel1 mapa1, Heroes personaje) {
        int x=getX();
        int y=getY();
        if (bandera) {
            setPosition(x, y);
            nextFrame();
            moverX(velocidad);
        } else {
            setPosition(x, y);
            nextFrame();
            moverX(-velocidad);
        }
        if (this.collidesWith(mapa1, false)) {
            bandera = !bandera;
            juego.setBanderaMoviemientoZombie(bandera);
            if (bandera) {
                setTransform(TRANS_NONE);
                setPosition(x, y);
            } else {
                setTransform(TRANS_MIRROR);
                setPosition(x, y);
            }
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
