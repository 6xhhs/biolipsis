package elementos;

import java.io.IOException;
import mapas.MapaNivel1;
import mapas.MapaNivel2y3;
import motor.AdministradorJuego;
import motor.Juego;
import personajes.Enemigos;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class PistolaJeringa extends Armas {

    private static final String archivito = "/bala.png";
    /**
     * Recibe como parámetro el archivo que se va a cargar
     */
    public static final int velocidad = 4;
    /**
     *Recibe la velocidad con la que se va a mover la bala
     */
    public static final int alto=10;
    /**
     *Recibe el alto de la bala
     */
    public static final int ancho=15;
    /**
     * Recibe el ancho de la bala
     */
    private static final int[] secuencia={0,0,0,0};

    /**
     * 
     * @throws IOException
     * Esta excepción es arrojada cuando no se encuentra la imagen
     */
    public PistolaJeringa() throws IOException {
        super(archivito,ancho,alto,secuencia);
    }

    /**
     *
     * @param mapa1
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
            juego.setBanderaBala(false);
            setPosition(admin.getDesplazamiento(), -alto);
        }
        if (collidesWith(zombie1, true) || collidesWith(zombie2, true)|| collidesWith(zombie3, true) || collidesWith(mapa2y3, false)) {
            juego.setBanderaBala(false);
        }
    }
    /**
     * 
     * @param mapa1
     * @param zombie1 Permite checar si el sprite colisiona con el zombie
     * @param zombie2 Permite checar si el sprite colisiona con el zombie
     * @param zombie3 Permite checar si el sprite colisiona con el zombie
     * @param juego Permite poder hacer cambios de posición en el juego
     * @param admin Permite crear los objetos de tipo jeringaLaser
     * @param direccion Permite checar hacia que dirección está orientado el laser
     */
    public void movimiento(MapaNivel1 mapa1, Enemigos zombie1, Enemigos zombie2, Enemigos zombie3, Juego juego, AdministradorJuego admin, boolean direccion) {
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
            juego.setBanderaBala(false);
            setPosition(admin.getDesplazamiento(), -alto);
        }
        if (collidesWith(zombie1, true) || collidesWith(zombie2, true)|| collidesWith(zombie3, true) || collidesWith(mapa1, false)) {
            juego.setBanderaBala(false);
        }
    }
}
