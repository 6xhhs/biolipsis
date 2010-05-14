package personajes;

import java.io.IOException;
import mapas.MapaNivel2y3;
import motor.AdministradorJuego;
import motor.Juego;

public class ZombieMayorflu extends Enemigos implements ZombieMoviles {

    private static final String ARCHIVO = "/hombreZombie.png";
    private static int VIDA = 2;
    private static final int VIDA_TOTAL = 2;
    private boolean banderaMorir;

    public ZombieMayorflu() throws IOException {
        super(ARCHIVO);
        setFrameSequence(getSecuenciaCaminar());
        banderaMorir = false;
    }

    public void mover(Juego juego, MapaNivel2y3 mapa2y3, Heroes personaje) {
        int x = getX();
        int y = getY();

        if (!banderaMorir) {
            nextFrame();
            if (collidesWith(mapa2y3, false)) {
                setPosition(x, y);
            }
            if (personaje.getX() < getX()) {
                setTransform(TRANS_MIRROR);
                setPosition(x, y);
                moverX(-VELOCIDAD/2);
            }
            if (personaje.getX() > getX()) {
                setTransform(TRANS_NONE);
                setPosition(x, y);
                moverX(VELOCIDAD/2);
            }
            if (personaje.getY() < getY()) {
                setPosition(x, y);
                moverY(-VELOCIDAD/2);
            }
            if (personaje.getY() > getY()) {
                setPosition(x, y);
                moverY(VELOCIDAD/2);
            }
        }
    }

    public void calcularDanio() {
        VIDA = VIDA - 1;
        if (VIDA <= 0) {
            banderaMorir = true;
        }
    }

    public void estado(Juego juego, AdministradorJuego admin) {
        if(getX()<(admin.getDesplazamiento()-ANCHO)) {
            setPosition(admin.getDesplazamiento()+(juego.ANCHO*2),getY());
        }
        if (banderaMorir) {
            morir(juego, admin, this);
            if (!banderaMorir) {
            juego.sumaHighscores(10);
                restaurarVida();
            }
        }
    }

    public int getVida() {
        return VIDA;
    }

    public void restaurarVida() {
        VIDA = VIDA_TOTAL;
    }

    public void setBanderaMorir(boolean banderaMorir) {
        this.banderaMorir = banderaMorir;
    }

}

