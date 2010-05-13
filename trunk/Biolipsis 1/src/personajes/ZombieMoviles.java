package personajes;

import javax.microedition.lcdui.game.Sprite;
import mapas.MapaNivel2y3;
import motor.AdministradorJuego;
import motor.Juego;

/**
 *
 * @author vicius thel
 */
public interface ZombieMoviles {

    /**
     *
     * @param juego
     * @param bandera
     * @param mapa
     * @param personaje
     */
    public void mover(Juego juego, MapaNivel2y3 mapa2y3, Heroes personaje);

    /**
     *
     * @param i
     * @param i0
     */
    public void setPosition(int i, int i0);

    /**
     *
     * @param trans
     */
    public void setTransform(int trans);

    /**
     *
     * @return
     */
    public int getVida();

    /**
     *
     */
    public void calcularDanio();

    /**
     *
     * @return
     */
    public int[] getSecuenciaCaminar();

    /**
     *
     * @return
     */
    public int[] getSecuenciaMorir();

    /**
     *
     */
    public void restaurarVida();

    /**
     *
     * @param sequence
     */
    public void setFrameSequence(int [] sequence);


    /**
     *
     * @param s
     * @param pixelLevel
     * @return
     */
    public boolean collidesWith(Sprite s, boolean pixelLevel);

    public void setBanderaMorir(boolean banderaMorir);

    public void estado(Juego juego, AdministradorJuego admin);

}
