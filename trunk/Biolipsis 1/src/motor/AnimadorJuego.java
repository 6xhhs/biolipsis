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
     * @param juego Recibe una interfaz animable que esta la que permite estar dandole vida al juego
     */
    public AnimadorJuego(Animable juego) {
        this.juego = juego;
    }

    /**
     * Mientras la bandera corriendo sea verdadera el juego se va estar pintando y actualizando en pantalla
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
     * Permite iniciar el juego
     */
    public void iniciar() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Termina de dibujar y actualizar al juego
     */
    public void terminar() {
        corriendo = false;
        thread.interrupt(); 
    }
}
