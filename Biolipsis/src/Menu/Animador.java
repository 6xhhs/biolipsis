package menu;

import menu.JuegoNuevo;
import menu.Menu;

public class Animador implements Runnable {
    private Menu menu;        //Juego debería ser una interface
    private JuegoNuevo submenu;
    private boolean corriendo;
    private Thread thread;
    private final int FPS = 60;
    private final int RETARDO = 1000/FPS;

    public Animador(Menu menu) {
        this.menu = menu;
    }
    public Animador(JuegoNuevo submenu) {
        this.submenu = submenu;
    }

    public void run() {
        corriendo = true;
        while(corriendo) {
                    menu.actualizar();                                                 // <-- Indicarle al juego que se actualice a todos los objetos(posicion)
                    menu.dibujar();                                                    // <-- Actualizar la pantalla(dibujar todos los objetos)
                try {
                Thread.sleep(RETARDO);
            } catch (InterruptedException ex) {}
                menu.actualizar();
                menu.dibujar();                                                // <-- Indicarle al juego que se actualice a todos los objetos(posicion
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
