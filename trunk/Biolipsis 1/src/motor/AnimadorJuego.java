package motor;

/**
 *
 * @author Diego
 */
public class AnimadorJuego implements Runnable {
    private Animable juego;
    private boolean corriendo;
    private Thread thread;
    private final int FPS = 35;
    private final int RETARDO = 1000/FPS;

    /**
     *
     * @param juego
     */
    public AnimadorJuego(Animable juego) {
        this.juego = juego;
    }

    /**
     *
     */
    public void run() {
        corriendo = true;
        while(corriendo) {
                    juego.actualizar();                                                 
                    juego.dibujar();                                                    
                try {
                    Thread.sleep(RETARDO);
            } catch (InterruptedException ex) {}                                        
        }
    }

    /**
     *
     */
    public void iniciar() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     *
     */
    public void terminar() {
        corriendo = false;
        thread.interrupt(); 
    }
}
