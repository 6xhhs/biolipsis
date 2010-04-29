package motor;

import elementos.Agua;
import elementos.AnimalCinco;
import elementos.AnimalCuatro;
import elementos.AnimalDos;
import elementos.AnimalSeis;
import elementos.AnimalTres;
import elementos.AnimalUno;
import elementos.AutoDestruido;
import elementos.AutoDos;
import elementos.AutoUno;
import elementos.Calabera;
import elementos.Destruido;
import elementos.Flecha;
import elementos.Hueso;
import elementos.Jeringa;
import elementos.Lancha;
import elementos.NativoDos;
import elementos.NativoTres;
import elementos.NativoUno;
import elementos.PersonajesExtra;
import elementos.Rayo;
import elementos.Taxi;
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
import personajes.ZombieFrustrado;
import personajes.ZombieMoviles;
import personajes.ZombieTriste;
import personajes.Zombieflu;

public class AdministradorJuego extends LayerManager {

    Heroes personaje;
    MapaNivel2y3 mapa2y3;
    MapaNivel1 mapa1;
    Tren tren;
    Virus virus, virus1, virus2;
    Rayo rayo;
    Vida vida;
    Flecha flecha;
    PersonajesExtra personajeExtra;
    AutoDestruido auto;
    Calabera calabera;
    AutoUno autoUno;
    AutoDos autoDos;
    Destruido destruido;
    Hueso hueso;
    Taxi taxi;
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
    ZombieMoviles zombie1, zombie2, zombie3;
    Jeringa bala;
    private Imagenes fondo;
    private int ANCHO, ALTO, desplazamiento;

    public AdministradorJuego(int ancho, int alto, boolean seleccionPersonaje) {
        try {
            //mapa2y3 = new MapaNivel2y3();
            mapa1 = new MapaNivel1();
            if (seleccionPersonaje) {
                System.out.println("se crea maryflu");
                personaje = new Maryflu();
            } else {
                System.out.println("se crea deflu");
                personaje = new Deflu();
            }
            // Elementos del nivel 1
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
            zombie1 = new Zombieflu();
            zombie2 = new ZombieFrustrado();
            zombie3 = new ZombieTriste();

            // Elementos del nivel 2
            //tren = new Tren();
            /*virus = new Virus();
            virus1 = new Virus();
            virus2 = new Virus();
            flecha = new Flecha();*/

            // Elementos del nivel 3
            //rayo = new Rayo();
            /*personajeExtra = new PersonajesExtra();
            auto = new AutoDestruido();
            calabera = new Calabera();
            autoUno = new AutoUno();
            autoDos = new AutoDos();
            destruido = new Destruido();
            hueso = new Hueso();
            taxi = new Taxi();
             */

            // Elementos presentes en todos los niveles
            vida = new Vida();
            bala = new Jeringa();
            fondo = new Imagenes("/fondoLago.jpg", 0, 0, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        desplazamiento = 0;

        //this.append(mapa2y3);
        this.append(mapa1);
        this.append(personaje);
        this.append(bala);

        // Nivel 1
        this.append((Layer) zombie1);
        this.append((Layer) zombie2);
        this.append((Layer) zombie3);
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
        this.append(vida);

        // Nivel 2
        /*this.append(tren);
        this.append(virus);
        this.append(virus1);
        this.append(virus2);*/

        // Nivel 3
        //this.append(rayo);
        /*this.append(flecha);
        this.append(personajeExtra);
        this.append(auto);
        this.append(calabera);
        this.append(autoUno);
        this.append(autoDos);
        this.append(destruido);
        this.append(hueso);
        this.append(taxi);
        */

        // Nivel 1
        personaje.setPosition(30,100);
        vida.setPosition(633, 160);
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

        /*
        personajeExtra.setPosition(200, 140);
        auto.setPosition(400, 178);
        calabera.setPosition(200, 80);
        autoUno.setPosition(700, 100);
        autoDos.setPosition(1500, 120);
        destruido.setPosition(900, 120);
        hueso.setPosition(1300, 140);
        taxi.setPosition(1757,105);
        */
        zombie1.setPosition(180, 100);
        zombie1.setTransform(Enemigos.TRANS_MIRROR);
        zombie2.setPosition(270, 120);
        zombie2.setTransform(Enemigos.TRANS_MIRROR);
        zombie3.setPosition(320, 140);
        zombie3.setTransform(Enemigos.TRANS_MIRROR);
        // Nivel 2
        /*flecha.setPosition(290,20);
        virus.setPosition(120, 125);
        virus1.setPosition(120, 155);
        virus2.setPosition(120, 185);
        tren.setPosition(-160, 55);*/

        // Nivel 3
        //rayo.setPosition(60, -40);

        this.ANCHO = ancho;
        this.ALTO = alto;
    }

    public void paint(Graphics g) {

        if (personaje.getX() > ANCHO / 2) { // la mitad de la pantalla
            this.desplazamiento = personaje.getX() - ANCHO / 2;
        }
        /*if (personaje.getX() > mapa2y3.getWidth() - ANCHO / 2) { // ultima mitad
            this.desplazamiento = mapa2y3.getWidth() - ANCHO;
        }*/
        if (personaje.getX() > mapa1.getWidth() - ANCHO / 2) { // ultima mitad
            this.desplazamiento = mapa1.getWidth() - ANCHO;
        }
        fondo.dibujar(g);
        fondo.setPosicion(-desplazamiento, 0);
        setViewWindow(desplazamiento, 0, 176, 220);
        paint(g, 0, 0);
    }

    public int getDesplazamiento() {
        return desplazamiento;
    }
}
