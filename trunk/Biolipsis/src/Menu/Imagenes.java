package menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Imagenes {

    private Image imagen;
    private int t,u,v,w,x,y, prioridad;

    public Imagenes(String archivo, int prioridad) throws IOException {
        imagen = Image.createImage(archivo);
        x = 75;
        y = 100;
        v = 33;
        w = 0;
        u = -180;
        t = 280;
        this.prioridad=prioridad;
    }

    public void mover(int pasos) {
        if(this.prioridad==0 && w<=15) {
            w+=pasos;
        }
        if(this.prioridad==1 && (x>35 && y>50)) {
           x-=pasos;
           y-=pasos;
        }
        if(this.prioridad==2 && (x<115 && y>50)) {
           x+=pasos;
           y-=pasos;
        }
        if(this.prioridad==3 && y<140) {
           x-=pasos;
           y+=pasos;
        }
        if(this.prioridad==4 && (x<140 && y<140)) {
           y+=pasos;
           x+=pasos;
        }
        if(this.prioridad==7 && t>=-1) {
            t-=pasos;
        }
        if(this.prioridad==8 && u<=-1) {
            u+=pasos;
        }
        if(this.prioridad==9 && u<=-1) {
            u+=pasos;
        }
        if(this.prioridad==10 && t>=-1) {
            t-=pasos;
        }
        if(this.prioridad==11 && t>=4) {
            t-=pasos;
        }
        if(this.prioridad==12 && u<=87) {
            u+=pasos;
        }
        if(this.prioridad==13 && t>=4) {
            t-=pasos;
        }
        if(this.prioridad==14 && u<=87) {
            u+=pasos;
        }
        if(this.prioridad==15 && u<=0) {
            u+=pasos;
        }
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar3(Graphics g) {
        g.drawImage(imagen, v, w, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar4(Graphics g) {
        g.drawImage(imagen, w, w, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar5(Graphics g) {
        g.drawImage(imagen, u, w, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar6(Graphics g) {
        g.drawImage(imagen, w, u, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar7(Graphics g) {
        g.drawImage(imagen, t, w, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar8(Graphics g) {
        g.drawImage(imagen, w, t, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar9(Graphics g) {
        g.drawImage(imagen, 90, u, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar10(Graphics g) {
        g.drawImage(imagen, t, 90, Graphics.TOP|Graphics.LEFT);
    }

    public void dibujar11(Graphics g) {
        g.drawImage(imagen, v, u, Graphics.TOP|Graphics.LEFT);
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
