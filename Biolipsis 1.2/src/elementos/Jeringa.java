package elementos;

import java.io.IOException;
import mapas.MapaNivel1;
import mapas.MapaNivel2y3;
import motor.AdministradorJuego;
import motor.Juego;
import personajes.Enemigos;
import personajes.Heroes;
import personajes.ZombieMoviles;
import personajes.Zombieflu;

public class Jeringa extends Armas {

    private static final String archivito = "/bala.png";
    public static final int velocidad = 4;

    public Jeringa() throws IOException {
        super(archivito);
    }

    public void movimiento(/*MapaNivel2y3 mapa*/MapaNivel1 mapa1, Enemigos zombie1, Enemigos zombie2, Enemigos zombie3, Juego juego, AdministradorJuego admin, boolean direccion) {
        int x = getX();
        int y = getY();
        if (direccion) {
            setTransform(TRANS_MIRROR);
            setPosition(x, y);
            moverX(-velocidad);
            nextFrame();
        } else {
            setTransform(TRANS_NONE);
            setPosition(x, y);
            moverX(velocidad);
            nextFrame();
        }
        if((getX()<admin.getDesplazamiento()||getX()>admin.getDesplazamiento()+juego.ANCHO)){
            juego.setBanderaBala(false);
            setPosition(admin.getDesplazamiento(), -10);
        }
        if (collidesWith(zombie1, true) || collidesWith(zombie2, true)|| collidesWith(zombie3, true) || collidesWith(mapa1, false)) {
            juego.setBanderaBala(false);
        }
    }
}
