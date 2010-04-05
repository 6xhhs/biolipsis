package menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Imagenes {

    private Image imagen;
    private int x,y,prioridad;

    public Imagenes(String archivo, int prioridad,int x, int y) throws IOException {
        imagen = Image.createImage(archivo);
        this.x = x;
        this.y = y;
        this.prioridad=prioridad;
    }

    public void mover(int pasos) {
        if(this.prioridad==0 && y<=15) {
            y+=pasos;
        }
        if(this.prioridad==1 && (x>20 && y>35)) {
           x-=pasos;
           y-=pasos;
        }
        if(this.prioridad==2 && (x<130 && y>35)) {
           x+=pasos;
           y-=pasos;
        }
        if(this.prioridad==3 && y<180) {
           x-=pasos;
           y+=pasos;
        }
        if(this.prioridad==4 && (x<130 && y<180)) {
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
        if(this.prioridad==21 && y>=4) {
            y-=pasos;
        }
        if(this.prioridad==22 && x<=87) {
            x+=pasos;
        }
        if(this.prioridad==23 && x>=4) {
            x-=pasos;
        }
        if(this.prioridad==24 && y<=108) {
            y+=pasos;
        }
        if(this.prioridad==19 && (x>=20 && y>=20)) {
            x-=pasos;
            y-=pasos;
        }
        if(this.prioridad==20 && (x<=100 && y<=100)) {
            x+=pasos;
            y+=pasos;
        }

        if(this.prioridad==26 && y<140) {
            x-=pasos;
            y+=pasos;

        }
        if(this.prioridad==27 && y>20) {
            x-=pasos;
            y-=pasos;
        }
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, Graphics.TOP|Graphics.LEFT);
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
