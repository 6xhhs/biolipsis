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
     *
     */
    public Musica(){
        reproduciendo = false;
    }

    /**
     *
     * @param archivo
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
            //player.setLoopCount(5); Reproducir n cantidad de veces
             reproduciendo = true;
         }catch(Exception e){
             e.printStackTrace();
         }
    }

    /**
     *
     * @param archivo
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
            //player.setLoopCount(5); Reproducir n cantidad de veces
             reproduciendo = true;
         }catch(Exception e){
             e.printStackTrace();
         }
    }

    /**
     *
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
     *
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
     * @return
     * @throws MediaException
     */
    public long pausar() throws MediaException{
        long tiempo = player.getMediaTime();
        player.stop();
        return tiempo;
    }

    /**
     *
     * @param tiempo
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
     * @return
     */
    public boolean getReproduciendo(){
        return reproduciendo;
    }

}

