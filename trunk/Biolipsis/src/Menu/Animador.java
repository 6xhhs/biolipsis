package Menu;

public class Animador implements Runnable {
    private PantallaMenu juego;        //Juego deberÃ­a ser una interface.
    private boolean corriendo;
    private Thread thread;
    private final int FPS = 60;
    private final int RETARDO = 1000/FPS;

    public Animador(PantallaMenu juego) {
        this.juego = juego;
    }

    public void run() {
        corriendo = true;
        while(corriendo) {
            juego.actualizar();                                                 // <-- Indicarle al juego que se actualice a todos los objetos(posicion)
            juego.dibujar();                                                    // <-- Actualizar la pantalla(dibujar todos los objetos)
            try {
                Thread.sleep(RETARDO);
            } catch (InterruptedException ex) {}
            juego.actualizar();                                                 // <-- Indicarle al juego que se actualice a todos los objetos(posicion)
        }
    }

    public void iniciar() {
        thread = new Thread(this);
        thread.start();                                                         // <-- Llama al metodo run().
    }

    public void terminar() {
        corriendo = false;
    }
}
