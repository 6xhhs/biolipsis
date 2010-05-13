package motor;

import elementos.Agua;
import elementos.AnimalCinco;
import elementos.AnimalCuatro;
import elementos.AnimalDos;
import elementos.AnimalSeis;
import elementos.AnimalTres;
import elementos.AnimalUno;
import elementos.Asustado;
import elementos.AutoDos;
import elementos.AutoTres;
import elementos.BarraVida;
import elementos.Bomba;
import elementos.Colgado;
import elementos.Compu;
import elementos.Engrane;
import elementos.Flecha;
import elementos.Flecha2;
import elementos.Hombres;
import elementos.JeringaLaser;
import elementos.PistolaJeringa;
import elementos.Lancha;
import elementos.NativoDos;
import elementos.NativoTres;
import elementos.NativoUno;
import elementos.Persona;
import elementos.Persona1;
import elementos.Persona2;
import elementos.Persona3;
import elementos.Rayo;
import elementos.Tambo;
import elementos.Temblorin;
import elementos.Tren;
import elementos.Vida;
import elementos.Virus;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Layer;
import javax.microedition.lcdui.game.LayerManager;
import mapas.MapaNivel2y3;
import imagenes.Imagenes;
import mapas.MapaNivel1;
import personajes.Deflu;
import personajes.Enemigos;
import personajes.Heroes;
import personajes.Maryflu;
import personajes.ZombieMayorflu;
import personajes.ZombieMoviles;
import personajes.Zombieflu;

/**
 *
 * @author Diego
 */
public class AdministradorJuego extends LayerManager {

    // Elementos comunes en todos los niveles
    Heroes personaje;
    Vida vida;
    MapaNivel2y3 mapa2y3;
    MapaNivel1 mapa1;
    Virus virus, virus1, virus2;
    ZombieMoviles zombie1, zombie2, zombie3, zombie4;
    PistolaJeringa bala;
    Bomba bombaDeflu;
    BarraVida barraVida;

    // Elementos de nivel 1
    Agua agua, agua1;
    Lancha lancha;
    NativoUno nativoUno;
    NativoDos nativoDos;
    NativoTres nativoTres;
    AnimalUno animalUno;
    AnimalDos animalDos;
    AnimalTres animalTres;
    AnimalCuatro animalCuatro;
    AnimalCinco animalCinco;
    AnimalSeis animalSeis;

    // Elementos de nivel 2
    Tren tren;
    Tambo tambo;
    Persona1 persona1;
    Compu compu;
    Asustado asustado;
    Engrane engrane;
    Flecha2 flecha2;
    Persona3 persona3;
    Hombres hombres;

    //Elementos de nivel 3
    Flecha flecha;
    Colgado colgado;
    Temblorin temblorin;
    Persona persona;
    Persona2 persona2;
    AutoDos autoDos;
    AutoTres autoTres;
    Rayo rayo, rayo1;
    JeringaLaser laser;

    private Imagenes fondo;
    private int ANCHO, ALTO, desplazamiento, nivel;

    /**
     *
     * @param ancho
     * @param alto
     * @param seleccionPersonaje
     * @param nivel
     */
    public AdministradorJuego(int ancho, int alto, boolean seleccionPersonaje, int nivel) {
        this.nivel = nivel;
        this.ANCHO = ancho;
        this.ALTO = alto;
        try {
            if (seleccionPersonaje) {
                personaje = new Maryflu();
            } else {
                personaje = new Deflu();
            }
            switch(this.nivel) {
                case 1:
                    zombie1 = new Zombieflu();
                    zombie2 = new Zombieflu();
                    zombie3 = new Zombieflu();
                    vida = new Vida();
                    bala = new PistolaJeringa();
                    agua = new Agua();
                    agua1 = new Agua();
                    lancha = new Lancha();
                    nativoUno = new NativoUno();
                    nativoDos = new NativoDos();
                    nativoTres = new NativoTres();
                    animalUno = new AnimalUno();
                    animalDos = new AnimalDos();
                    animalTres = new AnimalTres();
                    animalCuatro = new AnimalCuatro();
                    animalCinco = new AnimalCinco();
                    animalSeis = new AnimalSeis();
                    mapa1 = new MapaNivel1();
                    barraVida = new BarraVida();
                    fondo = new Imagenes("/fondoLago.jpg", 0, 0, 0);
                    this.append(mapa1);
                    this.append(personaje);
                    this.append(bala);
                    this.append(barraVida);
                    this.append((Layer) zombie1);
                    this.append((Layer) zombie2);
                    this.append((Layer) zombie3);
                    this.append(vida);
                    this.append(lancha);
                    this.append(agua);
                    this.append(agua1);
                    this.append(nativoUno);
                    this.append(nativoDos);
                    this.append(nativoTres);
                    this.append(animalUno);
                    this.append(animalDos);
                    this.append(animalTres);
                    this.append(animalCuatro);
                    this.append(animalCinco);
                    this.append(animalSeis);
                    personaje.setPosition(30,100);
                    bala.setPosition(0, -PistolaJeringa.alto);
                    vida.setPosition(633, 160);
                    zombie1.setPosition(180, 100);
                    zombie1.setTransform(Enemigos.TRANS_MIRROR);
                    zombie2.setPosition(270, 120);
                    zombie2.setTransform(Enemigos.TRANS_MIRROR);
                    zombie3.setPosition(320, 140);
                    zombie3.setTransform(Enemigos.TRANS_MIRROR);
                    lancha.setPosition(-30, 75);
                    agua.setPosition(0, 90);
                    agua1.setPosition(380, 90);
                    nativoUno.setPosition(1600, 140);
                    nativoDos.setPosition(900, 130);
                    nativoTres.setPosition(1600, 120);
                    animalUno.setPosition(1300, 160);
                    animalDos.setPosition(1800, 140);
                    animalTres.setPosition(800, 50);
                    animalCuatro.setPosition(400, 180);
                    animalCinco.setPosition(1600, 40);
                    animalSeis.setPosition(200, 150);
                    barraVida.setPosition(desplazamiento+(ANCHO/2), 5);
                    break;
                case 2:
                    zombie1 = new Zombieflu();
                    zombie2 = new Zombieflu();
                    zombie3 = new ZombieMayorflu();
                    vida = new Vida();
                    bala = new PistolaJeringa();
                    bombaDeflu = new Bomba();
                    virus = new Virus();
                    virus1 = new Virus();
                    virus2 = new Virus();
                    tren = new Tren();
                    tambo = new Tambo();
                    persona1 = new Persona1();
                    compu = new Compu();
                    asustado = new Asustado();
                    engrane = new Engrane();
                    flecha2 = new Flecha2();
                    persona3 = new Persona3();
                    hombres = new Hombres();
                    mapa2y3 = new MapaNivel2y3();
                    barraVida = new BarraVida();
                    fondo = new Imagenes("/fondoTren.jpg", 0, 0, 0);
                    this.append(mapa2y3);
                    this.append(personaje);
                    this.append(bala);
                    this.append(bombaDeflu);
                    this.append(barraVida);
                    this.append((Layer) zombie1);
                    this.append((Layer) zombie2);
                    this.append((Layer) zombie3);
                    this.append(vida);
                    this.append(virus);
                    this.append(virus1);
                    this.append(virus2);
                    this.append(tren);
                    this.append(tambo);
                    this.append(persona1);
                    this.append(compu);
                    this.append(asustado);
                    this.append(engrane);
                    this.append(flecha2);
                    this.append(persona3);
                    this.append(hombres);
                    personaje.setPosition(30,100);
                    bala.setPosition(0, -PistolaJeringa.alto);
                    bombaDeflu.setPosition(0, -Bomba.alto);
                    vida.setPosition(633, 160);
                    virus.setPosition(-30, 105);
                    virus1.setPosition(-30, 155);
                    virus2.setPosition(-30, 205);
                    zombie1.setPosition(180, 100);
                    zombie1.setTransform(Enemigos.TRANS_MIRROR);
                    zombie2.setPosition(270, 120);
                    zombie2.setTransform(Enemigos.TRANS_MIRROR);
                    zombie3.setPosition(320, 140);
                    zombie3.setTransform(Enemigos.TRANS_MIRROR);
                    tren.setPosition(-160, 55);
                    tambo.setPosition(800, 100);
                    persona1.setPosition(1550, 140);
                    compu.setPosition(950, 110);
                    asustado.setPosition(1700, 90);
                    engrane.setPosition(700, 180);
                    flecha2.setPosition(280, 40);
                    persona3.setPosition(1700, 155);
                    hombres.setPosition(400, 120);
                    barraVida.setPosition(desplazamiento+(ANCHO/2), 5);
                    break;
                case 3:
                    zombie1 = new ZombieMayorflu();
                    zombie2 = new Zombieflu();
                    zombie3 = new ZombieMayorflu();
                    vida = new Vida();
                    bala = new PistolaJeringa();
                    bombaDeflu = new Bomba();
                    laser = new JeringaLaser();
                    virus = new Virus();
                    virus1 = new Virus();
                    rayo = new Rayo();
                    rayo1 = new Rayo();
                    persona = new Persona();
                    persona2 = new Persona2();
                    autoDos = new AutoDos();
                    autoTres = new AutoTres();
                    flecha = new Flecha();
                    colgado = new Colgado();
                    temblorin = new Temblorin();
                    mapa2y3 = new MapaNivel2y3();
                    barraVida = new BarraVida();
                    fondo = new Imagenes("/fondoPeriferico.jpg", 0, 0, 0);
                    this.append((Layer) zombie1);
                    this.append((Layer) zombie2);
                    this.append((Layer) zombie3);
                    this.append(barraVida);
                    this.append(virus);
                    this.append(virus1);
                    this.append(bala);
                    this.append(bombaDeflu);
                    this.append(laser);
                    this.append(vida);
                    this.append(rayo);
                    this.append(rayo1);
                    this.append(mapa2y3);
                    this.append(persona);
                    this.append(persona2);
                    this.append(personaje);
                    this.append(flecha);
                    this.append(autoDos);
                    this.append(autoTres);
                    this.append(colgado);
                    this.append(temblorin);
                    personaje.setPosition(30,100);
                    bala.setPosition(0, -PistolaJeringa.alto);
                    bombaDeflu.setPosition(0, -Bomba.alto);
                    laser.setPosition(0, -JeringaLaser.alto);
                    vida.setPosition(633, 160);
                    virus.setPosition(-30, 105);
                    virus1.setPosition(-30, 155);
                    zombie1.setPosition(180, 100);
                    zombie1.setTransform(Enemigos.TRANS_MIRROR);
                    zombie2.setPosition(270, 120);
                    zombie2.setTransform(Enemigos.TRANS_MIRROR);
                    zombie3.setPosition(320, 140);
                    zombie3.setTransform(Enemigos.TRANS_MIRROR);
                    rayo.setPosition(-50, -52);
                    rayo1.setPosition(-50, -2);
                    persona.setPosition(200, 140);
                    persona2.setPosition(1350, 70);
                    autoDos.setPosition(1500, 120);
                    autoTres.setPosition(1800, 160);
                    autoTres.setTransform(AutoTres.TRANS_MIRROR);
                    flecha.setPosition(290,20);
                    colgado.setPosition(523, 16);
                    temblorin.setPosition(1400, 140);
                    barraVida.setPosition(desplazamiento+(ANCHO/2), 5);
                    break;
            }
            // Elemetos presentes en todos los niveles

            // Elementos del nivel 1

            // Elementos del nivel 2

            // Elementos del nivel 3
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Elementos para tods los niveles
        
        // Nivel 1

        // Nivel 2

        // Nivel 3
        
        // Elementos para todos los niveles

        // Nivel 1
        
        // Nivel 2
        
        // Nivel 3
        
    }

    /**
     *
     * @param g
     */
    public void dibujar(Graphics g) {
        desplazamiento = 0;
        switch(this.nivel) {
            case 1:
                if (personaje.getX() > ANCHO / 4) { // la mitad de la pantalla
                    this.desplazamiento = personaje.getX() - ANCHO / 4;
                }
                if (personaje.getX() > mapa1.getWidth() - (ANCHO*3) / 4) { // ultima mitad
                    this.desplazamiento = mapa1.getWidth() - ANCHO;
                }
                break;
            case 2:
                if (personaje.getX() > ANCHO / 4) { // la mitad de la pantalla
                    this.desplazamiento = personaje.getX() - ANCHO / 4;
                }
                if (personaje.getX() > mapa2y3.getWidth() - (ANCHO*3) / 4) { // ultima mitad
                    this.desplazamiento = mapa2y3.getWidth() - ANCHO;
                }
                break;
            case 3:
                if (personaje.getX() > ANCHO / 4) { // la mitad de la pantalla
                    this.desplazamiento = personaje.getX() - ANCHO / 4;
                }
                if (personaje.getX() > mapa2y3.getWidth() - (ANCHO*3) / 4) { // ultima mitad
                    this.desplazamiento = mapa2y3.getWidth() - ANCHO;
                }
                break;
        }
        fondo.setPosicion(-desplazamiento, 0);
        fondo.dibujar(g);
        setViewWindow(desplazamiento, 0, ANCHO, ALTO);
        paint(g, 0, 0);
    }

    /**
     *
     * @return
     */
    public int getDesplazamiento() {
        return desplazamiento;
    }
}
