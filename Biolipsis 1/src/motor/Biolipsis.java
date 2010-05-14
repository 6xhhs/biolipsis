package motor;

import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.media.MediaException;
import javax.microedition.midlet.*;
import menu.Menu;
import menu.Pausa;

/**
 *
 * @author Diego
 */
public class Biolipsis extends MIDlet {
    private Menu menu;
    private Juego juego;
    private Musica musica;
    private Pausa pausa;
    private FileConnection archivos;

    /**
     *
     * @throws Exception En caso de que la musica que se esta tocando no se encuentre
     */
    public Biolipsis() throws Exception {
        musica = new Musica();
        menu = new Menu(this);


    }

    /**
     * Inicia la aplicacion en el celular
     */
    public void startApp() {
        while(menu.getCambio()==false&&!menu.terminar) {
            Display.getDisplay(this).setCurrent(menu);
        }
        if(menu.getCambio()) {
            try {
                juego = new Juego(this, menu.isSeleccionPersonaje(), menu.getNivel(), menu.getHighscores());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            menu = null;
            ponerPantalla(juego);
        }
    }

    /**
     *
     */
    public void pauseApp() {

    }

    /**
     *
     * @param nuevo Pone la pantalla actual en el celular
     */
    public void ponerPantalla(Displayable nuevo) {
        Display.getDisplay(this).setCurrent(nuevo);
    }

    /**
     *
     * @param unconditional Permite indicar si la aplicacion se ha finalizado
     */
    public void destroyApp(boolean unconditional) {
    }

    /**
     * Metodo que termina de correr el juego en el celular
     */
    public void terminar() {
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     *
     * @param archivo Recibe este parametro para poder guardar los datos como puntajes y nivel de un usuario
     * @throws Exception En caso de que no se pudiera reproducir la musica, se lanza esta excepcion
     */
    public void reproducir(String archivo) throws Exception {
        if (musica != null) {
            musica.reproducir(archivo);
        } else {
            musica = new Musica();
            return;
        }
    }
    /**
     *
     * @param archivo Recibe este parametro para poder guardar los datos como puntajes y nivel de un usuario
     * @throws Exception En caso de que no se pudiera reproducir la musica, se lanza esta excepcion
     */
    public void reproducir2(String archivo) throws Exception {
        if (musica != null) {
            musica.reproducir2(archivo);
        } else {
            musica = new Musica();
            return;
        }
    }

    /**
     * Pausa la musica cuando el menu de pausa se manda a llamar
     */
    public void pausarMusica() {
        try {
            musica.pausar();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Detiene la musica por completo
     */
    public void detenerMusica(){
        musica.detenerMusica();
    }
    /**
     * Pausa la musica por complemto
     */
    public void detenerMusica2() {
        musica.detenerMusica2();
    }

    /**
     *
     * @return Cuando la musica se esta reproduciendo en el celular
     */
    public boolean estaReproduciendo() {
        return this.musica.getReproduciendo();
    }
    /**
     * Permite hacer el cambio de musica cuando se ha cambiado de nivel
     */
    public void cambioDeNivel() {
        musica = new Musica();
    }

    /**
     *
     * @throws IOException Se lanza esta excepcion en caso de que no se haya
     * reproducido la musica deseada
     */
    public void reiniciar() throws IOException {
        int n = juego.getHighscores();
        String score = n+"";
        try{
            escrituraScores(score.getBytes());
        }catch (SecurityException e){
            
        }
        juego = null;
        try {
            menu = new Menu(this);
            musica=new Musica();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        startApp();
    }

    /**
     *
     * @throws IOException Se lanza esta excepcion en caso de que no se pudiera reproducir la musica
     */
    public void reiniciarDesdePausa()throws IOException{
        juego = null;
        try {
            menu = new Menu(this);
            musica=new Musica();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        startApp();
    }

    /**
     *
     * @param data Se recibe este parametro para poder guardar la informacion de los puntajes
     * @throws IOException En caso de que no se pudiera hacer la escritura de los datos se lanza
     * esta excepcion
     */
    public void escrituraScores(byte[] data) throws IOException {
        this.archivos = (FileConnection) Connector.open(Menu.archivoScores, Connector.READ_WRITE);
        if(!archivos.exists()){
            archivos.create();
        }
        OutputStream printer = archivos.openOutputStream();
        printer.write(data);
        printer.close();
        archivos.close();

    }

    /**
     *
     * @param datos Para poder guardar la informacion de los datos
     */
    public void pausa(String datos) {
        pausa = new Pausa(juego, this, datos);
        Display.getDisplay(this).setCurrent(pausa);
    }

    /**
     * Permite regresar la musicar al momento preciso en el que se pauso con anterioridad
     */
    public void despausa() {
        Display.getDisplay(this).setCurrent(juego);
        pausa = null;
        try {
            musica.reanudar(musica.pausar());
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
}
