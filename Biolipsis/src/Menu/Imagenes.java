package Menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Imagenes {

    private Image a, b, c, d, e, f,titulo;
    private int x,y,v,w, prioridad;

    public Imagenes(String archivo, int prioridad) throws IOException {
        a = Image.createImage(archivo);
        b = Image.createImage(archivo);
        c = Image.createImage(archivo);
        d = Image.createImage(archivo);
        e = Image.createImage(archivo);
        f = Image.createImage(archivo);
        titulo = Image.createImage(archivo);
        x = 75;
        y = 100;
        v = 43;
        w = 0;
        this.prioridad=prioridad;
    }

    public void mover(int pasos) {
        if(this.prioridad==1 && (x>35 && y>50)) {
           x-=pasos;
           y-=pasos;
        }
        if(this.prioridad==2 && y<140) {
           x-=pasos;
           y+=pasos;
        }
        if(this.prioridad==3 && (x<115 && y>50)) {
           x+=pasos;
           y-=pasos;
        }
        if(this.prioridad==4 && (x<140 && y<140)) {
           y+=pasos;
           x+=pasos;
        }
    }

    public void mover2(int pasos) {
        if(w<=15) {
            w+=pasos;
        }
    }

    public void dibujar(Graphics g) {
        g.drawImage(a, x, y, Graphics.TOP|Graphics.LEFT);
        g.drawImage(b, x, y, Graphics.TOP|Graphics.LEFT);
        g.drawImage(c, x, y, Graphics.TOP|Graphics.LEFT);
        g.drawImage(d, x, y, Graphics.TOP|Graphics.LEFT);
        g.drawImage(e, x, y, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar2(Graphics g) {
        g.drawImage(f, x, y, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar3(Graphics g) {
        g.drawImage(titulo, v, w, Graphics.TOP|Graphics.LEFT);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getPrioridad() {
        return this.prioridad;
    }

    public void setPosicion(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad=prioridad;
    }
}
