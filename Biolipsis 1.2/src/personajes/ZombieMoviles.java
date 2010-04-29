package personajes;

import javax.microedition.lcdui.game.Sprite;
import mapas.MapaNivel1;
import mapas.MapaNivel2y3;
import motor.AdministradorJuego;
import motor.Juego;

public interface ZombieMoviles {

    public void mover(Juego juego, boolean bandera, /*MapaNivel2y3 mapa,*/MapaNivel1 mapa1, Heroes personaje);

    public void setPosition(int i, int i0);

    public void setTransform(int trans);

    public int getVida();

    public void calcularDanio();

    public int[] getSecuenciaCaminar();

    public int[] getSecuenciaMorir();

    public void restaurarVida();

    public void setFrameSequence(int [] sequence);

    public void morir(Juego aThis, AdministradorJuego admin);

    public boolean collidesWith(Sprite s, boolean pixelLevel);

}
