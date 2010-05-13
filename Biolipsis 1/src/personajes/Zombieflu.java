package personajes;

import java.io.IOException;
import mapas.MapaNivel2y3;
import motor.AdministradorJuego;
import motor.Juego;

public class Zombieflu extends Enemigos implements ZombieMoviles {

    private static final String archivito = "/mujerZombie.png";
    private static int vida=1;
    private static final int vidatotal=1;
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
            moverX(-velocidad);
        }        
    }

    public void calcularDanio(){
        vida=vida-1;
        if(vida <= 0) {
            banderaMorir = true;
        }
    }

    public void estado(Juego juego, AdministradorJuego admin) {
        if(getX()<(admin.getDesplazamiento()-ancho)) {
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
        return vida;
    }

    public void restaurarVida(){
        vida=vidatotal;
    }

    public void setBanderaMorir(boolean banderaMorir) {
        this.banderaMorir = banderaMorir;
    }
}
