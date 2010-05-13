package imagenes;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * @author Ma. Fernanda Martínez
 * @author Enrique García
 * @author Diego Zamora
 */
public class Imagenes {

    private Image imagen;
    private int x,y,prioridad;

    /**
     *
     * @param archivo Recibe como parametro la imagen que se desea cargar
     * @param prioridad Recibe como parámeteo el número de imagen que se desea manejar
     * y de esta forma poder darle la animación
     * @param x Recibe la posición en x en la que se ubicará la imagen
     * @param y Recibe la posición en y en la que se ubicará la imagen
     * @throws IOException Esta excepción se da en caso de que no se llegara a encontrar
     * el archivo
     */
    public Imagenes(String archivo, int prioridad,int x, int y) throws IOException {
        imagen = Image.createImage(archivo);
        this.x = x;
        this.y = y;
        this.prioridad=prioridad;
    }

    /**
     *
     * @param pasos Indica el número de pixeles que se va a mover la imagen
     */
    public void mover(int pasos) {
        if(this.prioridad==0 && y<=15) {
            y+=pasos;
        }
        if(this.prioridad==1 && (x>=20 && y>=35)) {
           x-=pasos;
           y-=pasos;
        }
        if(this.prioridad==2 && (x<=130 && y>=35)) {
           x+=pasos;
           y-=pasos;
        }
        if(this.prioridad==4 && y<=180) {
           x-=pasos;
           y+=pasos;
        }
        if(this.prioridad==3 && (x<=130 && y<=180)) {
           y+=pasos;
           x+=pasos;
        }
        if(this.prioridad==15 && y>=-1) {
            y-=pasos;
        }
        if(this.prioridad==16 && y<=-1) {
            y+=pasos;
        }
        if(this.prioridad==17 && x<=-1) {
            x+=pasos;
        }
        if(this.prioridad==18 && x>=-1) {
            x-=pasos;
        }
        if(this.prioridad==19 && x<=20) {
            x+=pasos;
        }
        if(this.prioridad==20 && x>=95) {
            x-=pasos;
        }
        if(this.prioridad==23 && x>=8) {
            x-=pasos;
        }
        if(this.prioridad==24 && y>=4) {
            y-=pasos;
        }
        if(this.prioridad==25 && y<=106) {
            y+=pasos;
        }
        if(this.prioridad==26 && x<=79) {
            x+=pasos;
        }
        if(this.prioridad==28 && y<=140) {
            x-=pasos;
            y+=pasos;
        }
        if(this.prioridad==29 && y>=20) {
            x-=pasos;
            y-=pasos;
        }
        if(this.prioridad==39 && x<=-10) {
            x+=pasos;
            y+=pasos;
        }
        if(this.prioridad==40 && x>=5) {
            x-=pasos;
            y-=pasos;
        }
        if(this.prioridad==44 && y<=140) {
            x-=pasos;
            y+=pasos;
        }
        if(this.prioridad==45 && y>=20) {
            x-=pasos;
            y-=pasos;
        }
        if(this.prioridad==50 && y>=4) {
            y-=pasos;
        }
        if(this.prioridad==51 && x<=87) {
            x+=pasos;
        }
        if(this.prioridad==52 && x>=4) {
            x-=pasos;
        }
        if(this.prioridad==53 && y<=108) {
            y+=pasos;
        }
    }

    /**
     *
     * @param g Recibe como parámetro el objeto de tipo Graphics
     */
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, Graphics.TOP|Graphics.LEFT);
    }

    /**
     *
     * @return Permite poder recuerpar la posición x de una imagen dada
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @return Permite poder recuperar la posición y de una imagen dada
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @return Regresa el número de imagen que se está animando
     */
    public int getPrioridad() {
        return this.prioridad;
    }

    /**
     *
     * @param x Permite cambiar la posición en x de una imagen
     * @param y
     */
    public void setPosicion(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     *
     * @param prioridad Permite cambiar el número de imagen que se está utilizando
     */
    public void setPrioridad(int prioridad) {
        this.prioridad=prioridad;
    }
}
