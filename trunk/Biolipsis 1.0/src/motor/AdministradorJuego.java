package motor;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.LayerManager;
import mapas.Mapa;
import personajes.Deflu;
import personajes.Heroes;
import personajes.Maryflu;

public class AdministradorJuego extends LayerManager
{
    Heroes personaje;
    Mapa mapa;
    private int ANCHO, ALTO;

    public AdministradorJuego(int ancho, int alto) {
        try {
            mapa = new Mapa();
            personaje = new Maryflu();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            this.append(mapa);
            this.append(personaje);
            personaje.setPosition(30,50);
            this.ANCHO = ancho;
            this.ALTO = alto;
    }

    public void paint(Graphics g) {

        int desplazamiento=0;

        if ( personaje.getX() > 88 ) { // 88 la mitad de la pantalla
            desplazamiento = personaje.getX()-ANCHO/2;
        }
        if ( personaje.getX()>mapa.getWidth()-ANCHO/2 ) { // ultima mitad
            desplazamiento = mapa.getWidth()-ANCHO;
        }

        setViewWindow(desplazamiento, 0, 176, 220);
        paint(g, 0, 0);
    }

    public void moverY(int dy) {

        personaje.move(0, dy);
    }

    public void moverX(int dx) {

        personaje.move(dx, 0);
    }
}
