package motor;

public class AnimadorJuego implements Runnable {
    private Animable juego;
    private boolean corriendo;
    private Thread thread;
    private final int FPS = 35;
    private final int RETARDO = 1000/FPS;

    public AnimadorJuego(Animable juego) {
        this.juego = juego;
    }

    public void run() {
        corriendo = true;
        while(corriendo) {
                    juego.actualizar();                                                 // <-- Indicarle al juego que se actualice a todos los objetos(posicion)
                    juego.dibujar();                                                    // <-- Actualizar la pantalla(dibujar todos los objetos)
                try {
                    Thread.sleep(RETARDO);
            } catch (InterruptedException ex) {}                                              // <-- Indicarle al juego que se actualice a todos los objetos(posicion
        }
    }

    public void iniciar() {
        thread = new Thread(this);
        thread.start();                                                         
    }

    public void terminar() {
        corriendo = false;
        thread.interrupt(); // Termina inmediatamente el thread
    }
}
