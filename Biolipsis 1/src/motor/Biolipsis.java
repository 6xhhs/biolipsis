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
     * @throws Exception
     */
    public Biolipsis() throws Exception {
        musica = new Musica();
        menu = new Menu(this);


    }

    /**
     *
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
     * @param nuevo
     */
    public void ponerPantalla(Displayable nuevo) {
        Display.getDisplay(this).setCurrent(nuevo);
    }

    /**
     *
     * @param unconditional
     */
    public void destroyApp(boolean unconditional) {
    }

    /**
     *
     */
    public void terminar() {
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     *
     * @param archivo
     * @throws Exception
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
     * @param archivo
     * @throws Exception
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
     *
     */
    public void pausarMusica() {
        try {
            musica.pausar();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public void detenerMusica(){
        musica.detenerMusica();
    }
    /**
     *
     */
    public void detenerMusica2() {
        musica.detenerMusica2();
    }

    /**
     *
     * @return
     */
    public boolean estaReproduciendo() {
        return this.musica.getReproduciendo();
    }
    /**
     *
     */
    public void cambioDeNivel() {
        musica = new Musica();
    }

    /**
     *
     * @throws IOException
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
     * @throws IOException
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
     * @param data
     * @throws IOException
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
     * @param datos
     */
    public void pausa(String datos) {
        pausa = new Pausa(juego, this, datos);
        Display.getDisplay(this).setCurrent(pausa);
    }

    /**
     *
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
