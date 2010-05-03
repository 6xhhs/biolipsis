package motor;
import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

public class Musica {

    Player player;
    private boolean reproduciendo;

    public Musica(){
        reproduciendo = false;
    }

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
            // player.setLoopCount(5);
             reproduciendo = true;
         }catch(Exception e){
             e.printStackTrace();
             System.out.println("No se puede reproducir la musica");
         }
    }

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

    public long pausar() throws MediaException{
        long tiempo = player.getMediaTime();
        player.stop();
        return tiempo;
    }

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

    public boolean getReproduciendo(){
        return reproduciendo;
    }

}

//Esta parte va en el constructor cuando se crea el nuevo objeto
// if(!midlet.estaReproduciendo())
//                midlet.reproducir("menu.mid");

//En el midlet instacías una variable de tipo musica
//En el constructor la creas
//Se ponen los siguientes metodos
//public void reproducir(String archivo) throws Exception {
//        if (musica != null) {
//            musica.reproducir(archivo);
//        } else {
//            System.out.println("La musca es null y se creara");
//            musica = new Musica();
//            return;
//        }
//    }
//
//    public void detenerMusica(){
//        musica.detenerMusica();
//    }
//
//    public void terminar() {
//        destroyApp(true);
//        notifyDestroyed();
//    }
//
//    public boolean estaReproduciendo() {
//        return this.musica.getReproduciendo();
//    }