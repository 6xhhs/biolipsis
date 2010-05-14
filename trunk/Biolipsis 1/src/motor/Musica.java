package motor;
import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *
 * @author Diego
 */
public class Musica {

    Player player, player2;
    private boolean reproduciendo;

    /**
     * Bandera que indicia cuando la musica se esta o no reproduciendo
     */
    public Musica(){
        reproduciendo = false;
    }

    /**
     *
     * @param archivo Recibe este parametro para indicar cual es el archivo que se leera
     */
    public void reproducir(String archivo){
        try {
            if(player!=null){
                player.stop();
                player.deallocate();
                player=null;
                reproduciendo = false;
             }
             InputStream datos = getClass().getResourceAsStream(archivo);
             player=Manager.createPlayer(datos,"audio/midi");
             player.realize();
             player.prefetch();
             player.start();
             reproduciendo = true;
         }catch(Exception e){
             e.printStackTrace();
         }
    }

    /**
     *
     * @param archivo Recibe este parametro para indicar cual es el archivo que se leera
     */
    public void reproducir2(String archivo){
        try {
            if(player2!=null){
                player2.stop();
                player2.deallocate();
                player2=null;
                reproduciendo = false;
             }
             InputStream datos = getClass().getResourceAsStream(archivo);
             player2 = Manager.createPlayer(datos,"audio/x-wav");
             player2.realize();
             player2.prefetch();
             player2.start();
             reproduciendo = true;
         }catch(Exception e){
             e.printStackTrace();
         }
    }

    /**
     * Metodo que permite detener la musica que se esta reproduciendo
     */
    public void detenerMusica(){
        try {
            player.stop();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
        player.deallocate();
        player = null;
        reproduciendo = false;
    }

    /**
     * Metodo que permite detener la musica que se esta reproduciendo
     */
    public void detenerMusica2() {
        try {
            player2.stop();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
        player2.deallocate();
        player2 = null;
        reproduciendo = false;
    }
    /**
     *
     * @return Regresa el segundo exacto en el que se detuvo la musica
     * @throws MediaException Si la musica no se logra reproducir
     */
    public long pausar() throws MediaException{
        long tiempo = player.getMediaTime();
        player.stop();
        return tiempo;
    }

    /**
     *
     * @param tiempo Regresa el tiempo exacto en el que se dejo de reproducir la musica
     */
    public void reanudar(long tiempo){
        try {
            player.setMediaTime(tiempo);
            player.start();
            player.prefetch();
            player.realize();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return Regresa la bandera que indica si la musica se estaba reproduciendo
     */
    public boolean getReproduciendo(){
        return reproduciendo;
    }

}

