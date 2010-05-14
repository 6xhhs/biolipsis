package personajes;

import java.io.IOException;
import mapas.MapaNivel2y3;
import motor.AdministradorJuego;
import motor.Juego;

public class Zombieflu extends Enemigos implements ZombieMoviles {

    private static final String archivito = "/mujerZombie.png";
    private static int VIDA = 1;
    private static final int VIDA_TOTAL = 1;
    public boolean banderaMorir;

    public Zombieflu() throws IOException {
        super(archivito);
        setFrameSequence(getSecuenciaCaminar());
        banderaMorir = false;
    }

    public void mover(Juego juego, MapaNivel2y3 mapa2y3, Heroes personaje) {
        int x=getX();
        int y=getY();
        if (!banderaMorir) {
            setPosition(x, y);
            nextFrame();
            moverX(-VELOCIDAD/2);
        }        
    }

    public void calcularDanio(){
        VIDA=VIDA-1;
        if(VIDA <= 0) {
            banderaMorir = true;
        }
    }

    public void estado(Juego juego, AdministradorJuego admin) {
        if(getX()<(admin.getDesplazamiento()-ANCHO)) {
            setPosition(admin.getDesplazamiento()+(juego.ANCHO*2),getY());
        }
        if(banderaMorir) {
                morir(juego,admin, this);
            if(!banderaMorir) {
                juego.sumaHighscores(5);
                restaurarVida();
            }
        }
    }

    public int getVida() {
        return VIDA;
    }

    public void restaurarVida(){
        VIDA=VIDA_TOTAL;
    }

    public void setBanderaMorir(boolean banderaMorir) {
        this.banderaMorir = banderaMorir;
    }
}
