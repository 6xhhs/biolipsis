package elementos;
import java.io.IOException;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class BarraVida extends ElementosDeNivel {

    private static final String archivo = "/arco.jpg";
    private static int alto = 10;
    private static int ancho = 10;
    private static int[] secuencia = {6,5,4,3,2,1,0};
    private boolean gameover;

    /**
 * Es el constructor de la clase, crea un taxi a partir de un archivo, lo
 * posiciona y le da secuencia.
 * @throws IOException
 */
    public BarraVida() throws IOException {
        super(archivo,ancho,alto,secuencia);
        setFrameSequence(secuencia);
        setFrame(0);
        gameover=false;
    }

    /**
     *
     * @param a
     * @param b
     * @throws InterruptedException Esta excepción ocurre cuando
     * no se realiza la función del Thread.sleep corectamente.
     * @exception IndexOutOfBoundsException Se da cuando está fuera
     * del rango del arreglo
     *
     */
    public void dibujar(int a, int b) throws InterruptedException{
        try{
            setFrame(a);
        }catch (IndexOutOfBoundsException e){
            Thread.sleep(3000);
            gameover=true;
        }
        setPosition(b, 5);
    }

    /**
     *
     * @return Regresa la bandera que indica si el juego se ha perdido
     */
    public boolean isGameover() {
        return gameover;
    }

}