package elementos;

import java.io.IOException;
import mapas.MapaNivel2y3;
import motor.AdministradorJuego;
import motor.Juego;
import personajes.Enemigos;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class Bomba extends Armas {

    private static final String archivito = "/bombaDeflu.png";
    /**
     *
     */
    public static final int velocidad = 4;
    /**
     *
     */
    public static final int alto=37;
    /**
     *
     */
    public static final int ancho=75;
    private static final int[] secuencia={0,0,0,0,1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5};

    /**
     * 
     * @throws IOException
     * Esta excepción es arrojada cuando no se encuentra la imagen
     */
    public Bomba() throws IOException {
        super(archivito,ancho,alto,secuencia);
    }

    /**
     * 
     * @param mapa2y3 Permite checar las colisiones del sprite con el mapa
     * @param zombie1 Permite checar si el sprite colisiona con el zombie
     * @param zombie2 Permite checar si el sprite colisiona con el zombie
     * @param zombie3 Permite checar si el sprite colisiona con el zombie
     * @param juego Permite poder hacer cambios de posición en el juego
     * @param admin Permite crear los objetos de tipo jeringaLaser
     * @param direccion Permite checar hacia que dirección está orientado el laser
     */
    public void movimiento(MapaNivel2y3 mapa2y3, Enemigos zombie1, Enemigos zombie2, Enemigos zombie3, Juego juego, AdministradorJuego admin, boolean direccion) {
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
        if((getX()<admin.getDesplazamiento()-ancho||getX()>admin.getDesplazamiento()+juego.ANCHO)){
            setFrame(0);
            setPosition(admin.getDesplazamiento(), -alto);
            juego.setBanderaBala(false);
        }
        if (collidesWith(zombie1, true) || collidesWith(zombie2, true)|| collidesWith(zombie3, true)) {
            juego.setBanderaBala(false);
        }
    }
}
