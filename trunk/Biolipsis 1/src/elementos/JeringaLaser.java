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
public class JeringaLaser extends Armas {

    private static final String archivito = "/secuenciaLaser.png";
    /**
     * Es el archivo que recibe como parametro
     */
    public static final int velocidad = 4;
    /**
     * La velocidad con la que se mueve el laser
     */
    public static final int alto=37;
    /**
     * El alto del sprite
     */
    public static final int ancho=75;
    /**
     * El ancho del sprite
     */
    private static final int[] secuencia={0,1,2,3,0,1,2,3,0,1,2,3,0,1,2,3};

    /**
     * 
     * @throws IOException
     * Esta excepción es arrojada cuando no se encuentra la imagen
     */
    public JeringaLaser() throws IOException {
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
                nextFrame();
            } else {
                setTransform(TRANS_NONE);
                setPosition(x, y);
                nextFrame();
            }
        if(getFrame()==getFrameSequenceLength()-1){
            juego.setBanderaBala(false);
            setPosition(admin.getDesplazamiento(), -alto);
        }
        if((getX()<admin.getDesplazamiento()-ancho||getX()>admin.getDesplazamiento()+juego.ANCHO)){
            setPosition(admin.getDesplazamiento(), -alto);
            juego.setBanderaBala(false);
        }
    }
}
