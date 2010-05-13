package motor;

import elementos.Bomba;
import elementos.PistolaJeringa;
import imagenes.Imagenes;
import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import personajes.Enemigos;
import personajes.Heroes;

/**
 *
 * @author Diego
 */
public class Juego extends GameCanvas implements Animable {

    private AdministradorJuego admin;
    private AnimadorJuego animador;
    private boolean bandera = false;
    private Graphics g;
    /**
     *
     */
    public int ANCHO;
    private int ALTO;
    private Biolipsis midlet;
    private boolean banderaDisparo = false;
    private boolean banderaDireccion;
    private boolean banderaBala;
    private boolean banderaDanio;
    private boolean banderaDanio1;
    private boolean banderaDanio2;
    private boolean banderaHerido;
    private boolean banderaHerido1;
    private boolean banderaHerido2;
    private boolean banderaHerido3;
    private boolean banderaDanio3;
    private Random random;
    private int nivel;
    private int armaSeleccionada;
    private boolean seleccionPersonaje;
    private int highscores;
    private Imagenes juegoTerminado, nivelGanado, nivelGanado2, nivelGanado3;

    /**
     *
     * @param midlet
     * @param seleccionPersonaje
     * @param nivel
     * @param highscores
     */
    public Juego(Biolipsis midlet, boolean seleccionPersonaje, int nivel, int highscores) {
        super(true);
        this.nivel = nivel;
        setFullScreenMode(true);
        g = getGraphics();
        ANCHO = getWidth();
        ALTO = getHeight();
        this.seleccionPersonaje=seleccionPersonaje;
        this.midlet = midlet;
        this.highscores=highscores;
        this.armaSeleccionada=1;
        banderaDireccion = false;
        banderaBala = false;
        banderaDanio = false;
        banderaDanio1 = false;
        banderaDanio2 = false;
        banderaDanio3 = false;
        banderaHerido = false;
        banderaHerido1 = false;
        banderaHerido2 = false;
        banderaHerido3 = false;
        random = new Random();
        admin = new AdministradorJuego(ANCHO, ALTO, seleccionPersonaje, this.nivel);
        try {
            if (midlet.estaReproduciendo()) {
                switch (this.nivel) {
                    case 1:
                        midlet.reproducir("/MusicNivel1.mid");
                        break;
                    case 2:
                        midlet.reproducir("/MusicNivel2.mid");
                        break;
                    case 3:
                        midlet.reproducir("/MusicNivel3.mid");
                        break;
                }
                juegoTerminado = new Imagenes("/gameOver.jpg",100,0,0);
                nivelGanado = new Imagenes("/youWinNivel1.jpg",200,0,0);
                nivelGanado2 = new Imagenes("/youWinNivel2.jpg",300,0,0);
                nivelGanado3 = new Imagenes ("/youWinNivel3.jpg",400,0,0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        animador = new AnimadorJuego(this);
        animador.iniciar();
    }

    /**
     *
     */
    public void dibujarJuegoTerminado() {
        midlet.detenerMusica();
        juegoTerminado.dibujar(g);
        if(!midlet.estaReproduciendo()) {
            try {
                midlet.reproducir2("/zombieMuerto.wav");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        flushGraphics();
    }

    /**
     *
     */
    public void dibujarNivelGanado() {
        nivelGanado.dibujar(g);
        if(!midlet.estaReproduciendo()) {
            try {
                midlet.reproducir2("/zombieMuerto.wav");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        flushGraphics();
    }

     /**
      *
      */
     public void dibujarNivelGanado2() {
        nivelGanado2.dibujar(g);
        if(!midlet.estaReproduciendo()) {
            try {
                midlet.reproducir2("/zombieMuerto.wav");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        flushGraphics();
    }

     /**
      *
      */
     public void dibujarNivelGanado3() {
        nivelGanado3.dibujar(g);
        if(!midlet.estaReproduciendo()) {
            try {
                midlet.reproducir2("/zombieMuerto.wav");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        flushGraphics();
    }

     /**
      *
      */
     public void dibujar() {
        admin.dibujar(g);
        g.setColor(0xB40404);
        g.drawString("Vida: ", ANCHO / 12, 0, Graphics.TOP | Graphics.LEFT);
        g.drawString("Puntaje "+this.highscores, ANCHO / 2, 0, Graphics.TOP | Graphics.LEFT);
        flushGraphics();
    }

    /**
     *Se encarga de actualizar colisiones de enemigos, colisiones con elementos
     * de nivel y con obstaculos.
     */
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
            if (!banderaBala) {
                banderaDireccion = false;
            }
        } else if ((tecla & LEFT_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.moverIzquierda();
            if (!banderaBala) {
                banderaDireccion = true;
            }
        }
        if ((tecla & FIRE_PRESSED) != 0 && !bandera) {
            switch (armaSeleccionada) {
                case 1:
                    bandera = true;
                    banderaDisparo = true;
                    banderaBala = true;
                    if (!banderaDireccion) {
                        admin.bala.setPosition(x + Heroes.ancho, y + (Heroes.alto / 2));
                    } else {
                        admin.bala.setPosition(x, y + (Heroes.alto / 2));
                    }
                    admin.personaje.setFrameSequence(Heroes.getSecuenciaDisparo());
                    break;
                case 2:
                    if (this.nivel != 1) {
                        bandera = true;
                        banderaDisparo = true;
                        banderaBala = true;
                        if (!banderaDireccion) {
                            admin.bombaDeflu.setPosition(x + (Heroes.ancho / 2), y + (Heroes.alto / 3));
                        } else {
                            admin.bombaDeflu.setPosition(x, y + (Heroes.alto / 3));
                        }
                        admin.personaje.setFrameSequence(admin.personaje.getSecuenciaBomba());
                    }
                    break;
                case 3:
                    if(this.nivel==3){
                        bandera = true;
                        banderaDisparo = true;
                        banderaBala = true;
                        if (!banderaDireccion) {
                            admin.laser.setPosition(x + Heroes.ancho, y + (Heroes.alto / 10));
                        } else {
                            admin.laser.setPosition(x - Heroes.ancho, y + (Heroes.alto / 10));
                        }
                        admin.personaje.setFrameSequence(Heroes.getSecuenciaDisparo());
                    }
                    break;
                default:
                    bandera = true;
                    banderaDisparo = true;
                    banderaBala = true;
                    if (!banderaDireccion) {
                        admin.bala.setPosition(x + Heroes.ancho, y + (Heroes.alto / 2));
                    } else {
                        admin.bala.setPosition(x, y + (Heroes.alto / 2));
                    }
                    admin.personaje.setFrameSequence(Heroes.getSecuenciaDisparo());
                    break;
            }
        }
        if ((tecla & UP_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.moverArriba();
        }
        if ((tecla & DOWN_PRESSED) != 0 && !banderaDisparo) {
            admin.personaje.moverAbajo();
        }
        if ((tecla & GAME_A_PRESSED) != 0) {
            armaSeleccionada = 1;
        }
        if ((tecla & GAME_B_PRESSED) != 0) {
            armaSeleccionada = 2;
        }
        if ((tecla & GAME_C_PRESSED) != 0) {
            armaSeleccionada = 3;
        }
        if ((tecla & GAME_D_PRESSED) != 0) {
            String datos = this.highscores+"";
            midlet.pausa(datos);
            midlet.pausarMusica();
            animador.terminar();
        }

        if (this.nivel != 1) {
            if (admin.personaje.collidesWith(admin.mapa2y3, false)) {
                admin.personaje.setPosition(x, y);
            }
        } else {
            if (admin.personaje.collidesWith(admin.mapa1, false)) {
                admin.personaje.setPosition(x, y);
            }
        }
        if (banderaDisparo) {
            admin.personaje.disparo(this);
        }
        if (banderaBala) {
            switch (armaSeleccionada) {
                case 1:
                    if(this.nivel!=1){
                        admin.bala.movimiento(admin.mapa2y3, (Enemigos) admin.zombie1, (Enemigos) admin.zombie2, (Enemigos) admin.zombie3, this, admin, banderaDireccion);
                    }else{
                        admin.bala.movimiento(admin.mapa1, (Enemigos) admin.zombie1, (Enemigos) admin.zombie2, (Enemigos) admin.zombie3, this, admin, banderaDireccion);
                    }
                    break;
                case 2:
                    if (this.nivel != 1) {
                        admin.bombaDeflu.movimiento(admin.mapa2y3, (Enemigos) admin.zombie1, (Enemigos) admin.zombie2, (Enemigos) admin.zombie3, this, admin, banderaDireccion);
                    }
                    break;
                case 3:
                    if (this.nivel == 3) {
                        admin.laser.movimiento(admin.mapa2y3, (Enemigos) admin.zombie1, (Enemigos) admin.zombie2, (Enemigos) admin.zombie3, this, admin, banderaDireccion);
                    }
                    break;
                default:
                    admin.bala.movimiento(admin.mapa2y3, (Enemigos) admin.zombie1, (Enemigos) admin.zombie2, (Enemigos) admin.zombie3, this, admin, banderaDireccion);
                    break;

            }
        }
        try {
            admin.barraVida.dibujar(Heroes.vida - 1, admin.getDesplazamiento() + (ANCHO / 3));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if(admin.barraVida.isGameover()) {
            dibujarJuegoTerminado();
            try {
                Thread.sleep(7000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            animador.terminar();
            admin = null;
            try {
                midlet.reiniciar();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        admin.personaje.colisionesDanio((Enemigos) admin.zombie1, (Enemigos) admin.zombie2, (Enemigos) admin.zombie3);

        if (admin.zombie1.collidesWith(admin.bala, true)) {
            admin.zombie1.calcularDanio();
            admin.bala.setPosition(0, -PistolaJeringa.alto);
        }
        if (this.nivel != 1) {
            if (admin.zombie1.collidesWith(admin.bombaDeflu, true)) {
                admin.zombie1.calcularDanio();
                admin.zombie1.calcularDanio();
                admin.bombaDeflu.setFrame(0);
                admin.bombaDeflu.setPosition(0, -Bomba.alto);
            }
        }
        if(this.nivel ==3 ){
            if (admin.zombie1.collidesWith(admin.laser, true)) {
                admin.zombie1.calcularDanio();
                admin.zombie1.calcularDanio();
                admin.zombie1.calcularDanio();
            }
        }
        admin.zombie1.estado(this, admin);
        admin.zombie1.mover(this, admin.mapa2y3, admin.personaje);
        if (admin.zombie2.collidesWith(admin.bala, true)) {
            admin.zombie2.calcularDanio();
            admin.bala.setPosition(0, -PistolaJeringa.alto);
        }
        if (this.nivel != 1) {
            if (admin.zombie2.collidesWith(admin.bombaDeflu, true)) {
                admin.zombie2.calcularDanio();
                admin.zombie2.calcularDanio();
                admin.bombaDeflu.setFrame(0);
                admin.bombaDeflu.setPosition(0, -Bomba.alto);
            }
        }
        if (this.nivel ==3) {
            if (admin.zombie2.collidesWith(admin.laser, true)) {
                admin.zombie2.calcularDanio();
                admin.zombie2.calcularDanio();
                admin.zombie2.calcularDanio();
            }
        }
        admin.zombie2.estado(this, admin);
        admin.zombie2.mover(this, admin.mapa2y3, admin.personaje);
        if (admin.zombie3.collidesWith(admin.bala, true)) {
            admin.zombie3.calcularDanio();
            admin.bala.setPosition(0, -PistolaJeringa.alto);
        }
        if (this.nivel != 1) {
            if (admin.zombie3.collidesWith(admin.bombaDeflu, true)) {
                admin.zombie3.calcularDanio();
                admin.zombie3.calcularDanio();
                admin.bombaDeflu.setFrame(0);
                admin.bombaDeflu.setPosition(0, -Bomba.alto);
            }
        }
        if (this.nivel ==3) {
            if (admin.zombie3.collidesWith(admin.laser, true)) {
                admin.zombie3.calcularDanio();
                admin.zombie3.calcularDanio();
                admin.zombie3.calcularDanio();
            }
        }
        admin.zombie3.estado(this, admin);
        admin.zombie3.mover(this, admin.mapa2y3, admin.personaje);
        switch (this.nivel) {
            case 1:
                if (admin.lancha.getX() >= 1900) {
                    admin.lancha.setPosition(-30, 75);
                }
                if (admin.animalCinco.getX() <= -30) {
                    admin.animalCinco.setPosition(1600, 40);
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
                if (admin.personaje.collidesWith(admin.animalCuatro, true) && !banderaDanio3) {
                    banderaDanio3 = true;
                    admin.personaje.calcularDano();
                } else if (!admin.personaje.collidesWith(admin.animalCuatro, true)) {
                    banderaDanio3 = false;
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
                if (admin.personaje.collidesWith(admin.vida, true)) {
                    admin.vida.setPosition(admin.personaje.getX() + 700, 80);
                    admin.personaje.calcularRecuperacion();
                } else if (admin.personaje.getX() > admin.vida.getX() + 180) {
                    admin.vida.setPosition(admin.personaje.getX() + 700, 80);
                }

                admin.vida.actualizar();
                break;
            case 2:
                if (admin.personaje.collidesWith(admin.virus, true) && !banderaDanio && !banderaHerido) {
                    banderaDanio = true;
                    if (banderaDanio) {
                        banderaHerido = true;
                        admin.personaje.setFrameSequence(admin.personaje.getSecuenciaMuerto());
                        admin.personaje.calcularDano();
                    }
                }
                if (!admin.personaje.collidesWith(admin.virus, true)) {
                    admin.personaje.herido(this);
                }

                if (admin.personaje.collidesWith(admin.virus1, true) && !banderaDanio1 && !banderaHerido1) {
                    banderaDanio1 = true;
                    if (banderaDanio1) {
                        banderaHerido1 = true;
                        admin.personaje.setFrameSequence(admin.personaje.getSecuenciaMuerto());
                        admin.personaje.calcularDano();
                    }
                }
                if (!admin.personaje.collidesWith(admin.virus1, true)) {
                    admin.personaje.herido(this);
                }

                if (admin.personaje.collidesWith(admin.virus2, true) && !banderaDanio2 && !banderaHerido2) {
                    banderaDanio2 = true;
                    if (banderaDanio2) {
                        banderaHerido2 = true;
                        admin.personaje.setFrameSequence(admin.personaje.getSecuenciaMuerto());
                        admin.personaje.calcularDano();
                    }
                }
                if (!admin.personaje.collidesWith(admin.virus2, true)) {
                    admin.personaje.herido(this);
                }

                if (admin.personaje.getX() > admin.virus.getX() + 180) {
                    admin.virus.setPosition(random.nextInt(1900), admin.virus.getY());
                } else if (admin.personaje.getX() < admin.virus.getX() - 180) {
                    admin.virus.setPosition(random.nextInt(1900), admin.virus.getY());
                }

                if (admin.personaje.getX() > admin.virus1.getX() + 180) {
                    admin.virus1.setPosition(random.nextInt(1900), admin.virus1.getY());
                } else if (admin.personaje.getX() < admin.virus1.getX() - 180) {
                    admin.virus1.setPosition(random.nextInt(1900), admin.virus1.getY());
                }

                if (admin.personaje.getX() > admin.virus2.getX() + 180) {
                    admin.virus2.setPosition(random.nextInt(1900), admin.virus2.getY());
                } else if (admin.personaje.getX() < admin.virus2.getX() - 180) {
                    admin.virus2.setPosition(random.nextInt(1900), admin.virus2.getY());
                }

                admin.virus.actualizar();
                admin.virus1.actualizar();
                admin.virus2.actualizar();

                if (admin.tren.getX() >= 1550) {
                    admin.tren.setPosition(-200, 55);
                }
                if (admin.personaje.collidesWith(admin.tambo, true)) {
                    admin.personaje.setPosition(x, y);
                }
                if (admin.personaje.getX() > admin.flecha2.getX() + 190) {
                    admin.flecha2.setPosition(admin.personaje.getX() + 190, 40);
                }
                if (admin.personaje.getX() < admin.flecha2.getX() - 190) {
                    admin.flecha2.setPosition(admin.personaje.getX() - 190, 40);
                }
                admin.tren.mover(6);
                admin.asustado.mover(-3);
                admin.tren.actualizar();
                admin.tambo.actualizar();
                admin.persona1.actualizar();
                admin.compu.actualizar();
                admin.asustado.actualizar();
                admin.engrane.actualizar();
                admin.flecha2.actualizar();
                admin.persona3.actualizar();
                admin.hombres.actualizar();

                if (admin.personaje.collidesWith(admin.vida, true)) {
                    admin.vida.setPosition(admin.personaje.getX() + 700, 80);
                    admin.personaje.calcularRecuperacion();
                } else if (admin.personaje.getX() > admin.vida.getX() + 180) {
                    admin.vida.setPosition(admin.personaje.getX() + 700, 80);
                }

                admin.vida.actualizar();
                break;
            case 3:
                if (admin.personaje.collidesWith(admin.virus, true) && !banderaDanio && !banderaHerido) {
                    banderaDanio = true;
                    if (banderaDanio) {
                        banderaHerido = true;
                        admin.personaje.setFrameSequence(admin.personaje.getSecuenciaMuerto());
                        admin.personaje.calcularDano();
                    }
                }
                if (!admin.personaje.collidesWith(admin.virus, true)) {
                    admin.personaje.herido(this);
                }

                if (admin.personaje.collidesWith(admin.virus1, true) && !banderaDanio1 && !banderaHerido1) {
                    banderaDanio1 = true;
                    if (banderaDanio1) {
                        banderaHerido1 = true;
                        admin.personaje.setFrameSequence(admin.personaje.getSecuenciaMuerto());
                        admin.personaje.calcularDano();
                    }
                }
                if (!admin.personaje.collidesWith(admin.virus1, true)) {
                    admin.personaje.herido(this);
                }

                if (admin.personaje.getX() > admin.virus.getX() + 180) {
                    admin.virus.setPosition(random.nextInt(1900), admin.virus.getY());
                } else if (admin.personaje.getX() < admin.virus.getX() - 180) {
                    admin.virus.setPosition(random.nextInt(1900), admin.virus.getY());
                }

                if (admin.personaje.getX() > admin.virus1.getX() + 180) {
                    admin.virus1.setPosition(random.nextInt(1900), admin.virus1.getY());
                } else if (admin.personaje.getX() < admin.virus1.getX() - 180) {
                    admin.virus1.setPosition(random.nextInt(1900), admin.virus1.getY());
                }

                if (admin.personaje.collidesWith(admin.rayo, true) && !banderaDanio2 && !banderaHerido2) {
                    banderaDanio2 = true;
                    if (banderaDanio2) {
                        banderaHerido2 = true;
                        admin.personaje.setFrameSequence(admin.personaje.getSecuenciaMuerto());
                        admin.personaje.calcularDano();
                        admin.personaje.calcularDano();
                        admin.personaje.calcularDano();
                    }
                }
                if (!admin.personaje.collidesWith(admin.rayo, true)) {
                    admin.personaje.herido(this);
                }

                if (admin.personaje.collidesWith(admin.rayo1, true) && !banderaDanio3 && !banderaHerido3) {
                    banderaDanio3 = true;
                    if (banderaDanio3) {
                        banderaHerido3 = true;
                        admin.personaje.setFrameSequence(admin.personaje.getSecuenciaMuerto());
                        admin.personaje.calcularDano();
                        admin.personaje.calcularDano();
                        admin.personaje.calcularDano();
                    }
                }
                if (!admin.personaje.collidesWith(admin.rayo1, true)) {
                    admin.personaje.herido(this);
                }

                if (admin.personaje.getX() > admin.rayo.getX() + 180) {
                    admin.rayo.setPosition(random.nextInt(1900), admin.rayo.getY());
                } else if (admin.personaje.getX() < admin.rayo.getX() - 180) {
                    admin.rayo.setPosition(random.nextInt(1900), admin.rayo.getY());
                }

                if (admin.personaje.getX() > admin.rayo1.getX() + 180) {
                    admin.rayo1.setPosition(random.nextInt(1900), admin.rayo1.getY());
                } else if (admin.personaje.getX() < admin.rayo1.getX() - 180) {
                    admin.rayo1.setPosition(random.nextInt(1900), admin.rayo1.getY());
                }

                admin.virus.actualizar();
                admin.virus1.actualizar();

                if (admin.personaje.collidesWith(admin.autoDos, true)) {
                    admin.personaje.setPosition(x, y);
                }
                if (admin.personaje.collidesWith(admin.autoTres, true)) {
                    admin.personaje.setPosition(x, y);
                }
                if (admin.personaje.getX() > admin.flecha.getX() + 190) {
                    admin.flecha.setPosition(admin.personaje.getX() + 190, 20);
                }
                if (admin.personaje.getX() < admin.flecha.getX() - 190) {
                    admin.flecha.setPosition(admin.personaje.getX() - 190, 20);
                }
                if (admin.autoTres.collidesWith(admin.personaje, true)) {
                    admin.personaje.calcularDano();
                }

                admin.persona.mover(-1);
                admin.autoTres.mover(-2);
                admin.autoDos.actualizar();
                admin.persona.actualizar();
                admin.flecha.actualizar();
                admin.autoTres.actualizar();
                admin.rayo.actualizar();
                admin.rayo1.actualizar();
                admin.colgado.actualizar();
                admin.temblorin.actualizar();
                admin.persona2.actualizar();
                if (admin.personaje.collidesWith(admin.vida, true)) {
                    admin.vida.setPosition(admin.personaje.getX() + 700, 80);
                    admin.personaje.calcularRecuperacion();
                } else if (admin.personaje.getX() > admin.vida.getX() + 180) {
                    admin.vida.setPosition(admin.personaje.getX() + 700, 80);
                }
                admin.vida.actualizar();
                break;
        }
        if(admin.personaje.getX()>(admin.getDesplazamiento()+ANCHO)){
            try {
                cambioNivel();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Se encarga de cambiar la musica y el nivel en el que se encuentra el personaje
     * @throws InterruptedException
     */
    public void cambioNivel() throws InterruptedException{
        this.nivel++;
        if(this.nivel>3){
            this.dibujarNivelGanado3();
            midlet.detenerMusica();
            Thread.sleep(7000);
            try {
                midlet.reiniciar();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(this.nivel==2) {
            midlet.detenerMusica();
            admin=null;
            this.dibujarNivelGanado();
            Thread.sleep(7000);
            midlet.cambioDeNivel();
        }
        if(this.nivel==3) {
            midlet.detenerMusica();
            admin=null;
            this.dibujarNivelGanado2();
            Thread.sleep(7000);
            midlet.cambioDeNivel();
        }
        try {
        if(!midlet.estaReproduciendo()) {
            switch(this.nivel) {
                case 2:
                    midlet.reproducir("/MusicNivel2.mid");
                    break;
                case 3:
                    midlet.reproducir("/MusicNivel3.mid");
                    break;
                }
            }
        } catch (Exception ex) {
                ex.printStackTrace();
            }
        admin=new AdministradorJuego(ANCHO, ALTO, seleccionPersonaje, this.nivel);
    }

    /**
     * Se encarga de reiniciar el nivel
     */
    public void reiniciarNivel(){
        admin=null;
        this.highscores=0;
        admin=new AdministradorJuego(ANCHO, ALTO, seleccionPersonaje, this.nivel);
    }

    /**
     *
     * @param x
     */
    public void sumaHighscores(int x){
        this.highscores=this.highscores+x;
    }

    /**
     * Se encarga de lectura de teclado
     * @param bandera
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    /**
     * Se encarga de cambiar la posicion del disparo
     * @param banderaDisparo
     */
    public void setBanderaDisparo(boolean banderaDisparo) {
        this.banderaDisparo = banderaDisparo;
    }

    /**
     * Se encarga de la direccion del disparo
     * @param banderaBala
     */
    public void setBanderaBala(boolean banderaBala) {
        this.banderaBala = banderaBala;
    }

    /**
     * Se encarga de detectar cuando el personaje ha sido herido
     * @param banderaParpadeo
     */
    public void setBanderaParpadeo(boolean banderaParpadeo) {
        this.banderaHerido = banderaParpadeo;
    }

    /**
     * Se encarga de calcular el danio ocasionado por virus
     * @param banderaVirus
     */
    public void setBanderaVirus(boolean banderaVirus) {
        this.banderaDanio = banderaVirus;
    }

    /**
     *
     * @return
     */
    public int getHighscores() {
        return highscores;
    }

    /**
     *
     */
    public void reanudarJuego() {
        animador.iniciar();
    }

    /**
     *
     */
    public void terminar() {

        animador.terminar();
        midlet.terminar();
    }
}