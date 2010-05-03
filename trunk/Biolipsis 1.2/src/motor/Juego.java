package motor;

//import elementos.AnimalCinco;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import menu.Menu;
import personajes.Enemigos;
import personajes.Heroes;
//import personajes.ZombieMoviles;

public class Juego extends GameCanvas implements Animable {

    private AdministradorJuego admin;
    private AnimadorJuego animador;
    private boolean bandera = false;
    private Graphics g;
    public int ANCHO;
    private int ALTO;
    private Biolipsis midlet;
    private boolean banderaDisparo = false;
    private boolean banderaMovimientoZombie;
    private boolean banderaColisionDano;
    private int banderaNumeroZombie;
    private boolean banderaDireccion;
    private boolean banderaBala;
    private boolean banderaMorir;
    private boolean banderaVirus;
    private boolean banderaVirus1;
    private boolean banderaVirus2;
    private boolean banderaParpadeo;
    private Menu menu;
    private Random random;

    public Juego(Biolipsis midlet, boolean seleccionPersonaje) throws Exception {

        super(true);
        setFullScreenMode(true);
        g = getGraphics();
        ANCHO = getWidth();
        ALTO = getHeight();
        this.midlet = midlet;
        if(!midlet.estaReproduciendo()){
            midlet.reproducir("Menu.mid");
        }
        banderaMovimientoZombie = false;
        banderaColisionDano = false;
        banderaDireccion=false;
        banderaNumeroZombie = 0;
        banderaBala=false;
        banderaMorir=false;
        banderaVirus = false;
        banderaVirus1 = false;
        banderaVirus2 = false;
        banderaParpadeo = false;
        random = new Random();
        admin = new AdministradorJuego(ANCHO, ALTO, seleccionPersonaje);

        animador = new AnimadorJuego(this);
        animador.iniciar();
    }

    public void dibujar() {
        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, getWidth(), getHeight());
        admin.paint(g);


        g.setColor(0x00FF00);
        g.drawString("D-Salir", ANCHO / 2, ALTO, Graphics.BOTTOM | Graphics.HCENTER);

        flushGraphics();
    }

    public void actualizar() {
        int tecla = getKeyStates();
        if (tecla == 0 && !banderaDisparo) {
            if (!banderaDisparo) {
                admin.personaje.setFrameSequence(admin.personaje.getSecuencia());
                bandera = false;
            }
            bandera = false;
        }
        int x = admin.personaje.getX();
        int y = admin.personaje.getY();
        if ((tecla & RIGHT_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.moverDerecha();
            if(!banderaBala){
            banderaDireccion=false;
            }
        } else if ((tecla & LEFT_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.moverIzquierda();
            if(!banderaBala){
                banderaDireccion=true;
            }
        }
        if ((tecla & FIRE_PRESSED) != 0 && !bandera) {
            bandera = true;
            banderaDisparo = true;
            banderaBala=true;
            if(!banderaDireccion){
                admin.bala.setPosition(x+Heroes.ancho,y+(Heroes.alto/2));
            }else{
                admin.bala.setPosition(x,y+(Heroes.alto/2));
            }
            admin.personaje.setFrameSequence(Heroes.getSecuenciaDisparo());
        }
        if ((tecla & UP_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.moverArriba();
        }
        if ((tecla & DOWN_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.moverAbajo();
        }
        if ((tecla & GAME_D_PRESSED) != 0) {
            midlet.notifyPaused();
            System.out.println("Me han pausado");
        } else if ((tecla & GAME_A_PRESSED) != 0) {
            midlet.resumeRequest();
        }
        if (admin.personaje.collidesWith(admin.mapa1, false)) {
            admin.personaje.setPosition(x, y);
        }
        if (banderaDisparo) {
            admin.personaje.disparo(this);
        }
        if(banderaBala){
                admin.bala.movimiento(admin.mapa1, (Enemigos) admin.zombie1, (Enemigos)admin.zombie2, (Enemigos)admin.zombie3, this, admin,  banderaDireccion);
        }
        if(admin.zombie1.collidesWith(admin.bala, true)){
            admin.zombie1.calcularDanio();
            admin.bala.setPosition(admin.getDesplazamiento()-10, -10);
        }
        if(admin.zombie1.getVida()<=0){
            banderaMorir=true;
            admin.zombie1.morir(this, admin);
            if(!banderaMorir){
                admin.zombie1.restaurarVida();
            }
        }
        if(admin.zombie1.getVida()>0){
            admin.zombie1.mover(this, banderaMovimientoZombie, admin.mapa1, admin.personaje);
            if (admin.personaje.collidesWith((Enemigos)admin.zombie1, true) && (banderaNumeroZombie == 0)) {
                banderaNumeroZombie = 1;
            }
            if (admin.personaje.collidesWith((Enemigos)admin.zombie1, true) && !banderaColisionDano && banderaNumeroZombie == 1) {
                banderaColisionDano = true;
                admin.personaje.calcularDano();
                System.out.println("tienes " + Heroes.vida + " vidas");
            }
            if (!admin.personaje.collidesWith((Enemigos)admin.zombie1, true) && banderaNumeroZombie == 1) {
                banderaColisionDano = false;
                banderaNumeroZombie = 0;
            }
        }
        if(admin.zombie2.collidesWith(admin.bala, true)){
            admin.zombie2.calcularDanio();
            admin.bala.setPosition(admin.getDesplazamiento(), 0);
        }
        if(admin.zombie2.getVida()<=0){
            banderaMorir=true;
            admin.zombie2.morir(this, admin);
            if(!banderaMorir){
                admin.zombie2.restaurarVida();
            }
        }
        if(admin.zombie2.getVida()>0){
            admin.zombie2.mover(this, banderaMovimientoZombie, admin.mapa1,admin.personaje);
            if (admin.personaje.collidesWith((Enemigos)admin.zombie2, true) && (banderaNumeroZombie == 0)) {
                banderaNumeroZombie = 2;
            }
            if (admin.personaje.collidesWith((Enemigos)admin.zombie2, true) && !banderaColisionDano && banderaNumeroZombie == 2) {
                banderaColisionDano = true;
                admin.personaje.calcularDano();
                System.out.println("tienes " + Heroes.vida + " vidas");
            }
            if (!admin.personaje.collidesWith((Enemigos)admin.zombie2, true) && banderaNumeroZombie == 2) {
                banderaColisionDano = false;
                banderaNumeroZombie = 0;
            }
        }
        if(admin.zombie3.collidesWith(admin.bala, true)){
            admin.zombie3.calcularDanio();
            admin.bala.setPosition(admin.getDesplazamiento(), 0);
        }
        if(admin.zombie3.getVida()<=0){
            banderaMorir=true;
            admin.zombie3.morir(this, admin);
            if(!banderaMorir) {
                admin.zombie3.restaurarVida();
            }
        }
        if(admin.zombie3.getVida()>0){
            admin.zombie3.mover(this, banderaMovimientoZombie, admin.mapa1,admin.personaje);
            if (admin.personaje.collidesWith((Enemigos)admin.zombie3, true) && (banderaNumeroZombie == 0)) {
                banderaNumeroZombie = 3;
            }
            if (admin.personaje.collidesWith((Enemigos)admin.zombie3, true) && !banderaColisionDano && banderaNumeroZombie == 3) {
                banderaColisionDano = true;
                admin.personaje.calcularDano();
                System.out.println("tienes " + Heroes.vida + " vidas");
            }
            if (!admin.personaje.collidesWith((Enemigos)admin.zombie3, true) && banderaNumeroZombie == 3) {
                banderaColisionDano = false;
                banderaNumeroZombie = 0;
            }
        }

        // Actualizacion y colisiones de primer nivel
        if( admin.lancha.getX()>=1900) {
            admin.lancha.setPosition(-30, 75);
        }
        if(admin.personaje.getX()>=650 && admin.personaje.getX()<=700) {
            admin.agua.setPosition(760, 90);
        }
        if(admin.personaje.getX()>=1410 && admin.personaje.getX()<=1460) {
            admin.agua.setPosition(1520, 90);
        }
        if(admin.personaje.getX()>=440 && admin.personaje.getX()<=490) {
            admin.agua.setPosition(0, 90);
        }
        if(admin.personaje.getX()>=1200 && admin.personaje.getX()<=1250) {
            admin.agua.setPosition(760, 90);
        }
        if(admin.personaje.getX()>=1030 && admin.personaje.getX()<=1080) {
            admin.agua1.setPosition(1140, 90);
        }
        if(admin.personaje.getX()>=700 && admin.personaje.getX()<=790) {
            admin.agua1.setPosition(380, 90);
        }

        admin.nativoUno.mover(-2);
        admin.lancha.mover(2);
        admin.animalUno.mover(-1);
        admin.animalDos.mover(-1);
        admin.animalCinco.mover(-1);
        admin.animalSeis.mover(-1);

        admin.animalUno.actualizar();
        admin.animalDos.actualizar();
        admin.animalTres.actualizar();
        admin.animalCuatro.actualizar();
        admin.animalCinco.actualizar();
        admin.animalSeis.actualizar();
        admin.nativoUno.actualizar();
        admin.nativoDos.actualizar();
        admin.nativoTres.actualizar();
        admin.lancha.actualizar();
        admin.agua.actualizar();
        admin.agua1.actualizar();

        if(admin.personaje.collidesWith(admin.vida, true)) {
            admin.vida.setPosition(admin.personaje.getX()+700, 80);
            admin.personaje.calcularRecuperacion();
            System.out.println("Subi a " + Heroes.vida + " de vida");
        } else if (admin.personaje.getX()>admin.vida.getX()+180) {
            admin.vida.setPosition(admin.personaje.getX()+700, 80);
        }

        admin.vida.actualizar();


        // Actualizacion y colisiones de segundo nivel
        /*if(admin.personaje.collidesWith(admin.virus, true) && !banderaVirus && !banderaParpadeo) {
            banderaVirus = true;
            if(banderaVirus) {
                banderaParpadeo = true;
                admin.personaje.setFrameSequence(admin.personaje.getSecuenciaHerido());
                admin.personaje.calcularDano();
            }
            System.out.println("El virus me ha hecho danio y baje mi vida a " + Heroes.vida);
        } if (!admin.personaje.collidesWith(admin.virus, true)) {
            admin.personaje.herido(this);
        }

        if(admin.personaje.collidesWith(admin.virus1, true) && !banderaVirus1) {
            banderaVirus1 = true;
            admin.personaje.calcularDano();
            System.out.println("El virus me ha hecho danio y baje mi vida a " + Heroes.vida);
        } if (!admin.personaje.collidesWith(admin.virus1, true)) {
            banderaVirus1 = false;
        }

        if(admin.personaje.collidesWith(admin.virus2, true) && !banderaVirus2) {
            banderaVirus2 = true;
            admin.personaje.calcularDano();
            System.out.println("El virus me ha hecho danio y baje mi vida a " + Heroes.vida);
        } if (!admin.personaje.collidesWith(admin.virus2, true)) {
            banderaVirus2 = false;
        }

        if(admin.personaje.getX()>admin.virus.getX()+180) {
            admin.virus.setPosition(random.nextInt(1900), admin.virus.getY());
        } else if (admin.personaje.getX()<admin.virus.getX()-180) {
            admin.virus.setPosition(random.nextInt(1900), admin.virus.getY());
            System.out.println("Soy virus y cambie mi posicion a " + admin.virus.getX());
        }

        if(admin.personaje.getX()>admin.virus1.getX()+180) {
            admin.virus1.setPosition(random.nextInt(1900), admin.virus1.getY());
        } else if (admin.personaje.getX()<admin.virus1.getX()-180) {
            admin.virus1.setPosition(random.nextInt(1900), admin.virus1.getY());
            System.out.println("Soy virus1 y cambie mi posicion a " + admin.virus1.getX());
        }

        if(admin.personaje.getX()>admin.virus2.getX()+180) {
            admin.virus2.setPosition(random.nextInt(1900), admin.virus2.getY());
        } else if (admin.personaje.getX()<admin.virus2.getX()-180) {
            admin.virus2.setPosition(random.nextInt(1900), admin.virus2.getY());
            System.out.println("Soy virus2 y cambie mi posicion a " + admin.virus2.getX());
        }

        admin.virus.actualizar();
        admin.virus1.actualizar();
        admin.virus2.actualizar();

        if( admin.tren.getX()>=1550) {
            admin.tren.setPosition(-200, 55);
        }
        admin.tren.mover(6);
        admin.tren.actualizar();

        if(admin.personaje.getX()>admin.flecha.getX()+190) {
            admin.flecha.setPosition(admin.personaje.getX()+190, 20);
        }
        if(admin.personaje.getX()<admin.flecha.getX()-190) {
            admin.flecha.setPosition(admin.personaje.getX()-190, 20);
        }
        admin.flecha.actualizar();

        // Actualizacion y colisiones de tercer nivel

        admin.rayo.actualizar();
        if(admin.personaje.collidesWith(admin.auto, true)) {
            admin.personaje.setPosition(x, y);
        }
        if(admin.personaje.collidesWith(admin.autoDos, true)) {
            admin.personaje.setPosition(x, y);
        }
        admin.autoDos.actualizar();

        admin.personajeExtra.mover(-1);

        if(admin.personaje.collidesWith(admin.autoUno, true)) {
            admin.personaje.setPosition(x, y);
        }
        if(admin.personaje.collidesWith(admin.destruido, true)) {
            admin.personaje.setPosition(x, y);
        }
        if(admin.personaje.collidesWith(admin.taxi, true)) {
            admin.personaje.setPosition(x, y);
        }
         */

    }

    public void setBanderaMoviemientoZombie(boolean banderaMoviemientoZombie) {
        this.banderaMovimientoZombie = banderaMoviemientoZombie;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public void setBanderaColisionDano(boolean banderaColisionDano) {
        this.banderaColisionDano = banderaColisionDano;
    }

    public void setBanderaDisparo(boolean banderaDisparo) {
        this.banderaDisparo = banderaDisparo;
    }

    public void setBanderaBala(boolean banderaBala) {
        this.banderaBala = banderaBala;
    }

    public void setBanderaMorir(boolean banderaMorir) {
        this.banderaMorir = banderaMorir;
    }

    public void setBanderaParpadeo(boolean banderaParpadeo) {
        this.banderaParpadeo = banderaParpadeo;
    }

    public void setBanderaVirus(boolean banderaVirus) {
        this.banderaVirus = banderaVirus;
    }

    public void terminar() {

        animador.terminar();
        midlet.terminar();
    }
}
