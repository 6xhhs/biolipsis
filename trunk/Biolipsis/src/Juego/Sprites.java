package Juego;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Sprites {
    private int posicionX, posicionY;//Posicion en pixeles de los sprites
    private boolean activo;//sirve para saber si el sprite esta activo
    private int frame/*almacena el frame actual*/, nframes/*total de frames*/;
    private Image[] sprites; //Objetos que contendrá cada frame del juego

    public Sprites(int nframes){
        activo=false;
        frame=1;
        this.nframes=nframes;
        sprites=new Image[nframes+1];

    }

    //Regresan la posicion del Sorite
    public int getX(){
        return posicionX;
    }

    public int getY(){
        return posicionY;
    }

    //Actualizan la posicion del Sprite
    public void setX(int x){
        posicionX=x;
    }

    public void setY(int y){
        posicionY=y;
    }

    //Tamaño del Frame
    public int getW(){
        return sprites[nframes].getWidth();
    }
    public int getH(){
        return sprites[nframes].getHeight();
    }

    //Metodos que activan el sprite
    public void prendido(){
        activo=true;
    }

    public void apagado(){
        activo=false;
    }

    //Da a conocer el estado del sprite
    public boolean isActive(){
        return activo;
    }

    //Fija el frame actual del sprite
    public void selFrame(int frameNum){
        frame=frameNum;
    }

    //Regresa el numero de frames
    public int frames() {
        return nframes;
    }

    //Añade frames al sprite
    public void addFrames(int frameNum, String path){
        try{
            sprites[frameNum]=Image.createImage(path);
        }catch(IOException e){
            System.out.println("No se puede cargar la imagen");
        }
    }

   public boolean collide(Sprites s){
        int w1,h1,w2,h2,x1,y1,x2,y2;
        w1=getW(); // ancho del sprite1
        h1=getH(); // altura del sprite1
        w2=s.getW(); // ancho del sprite2
        h2=s.getH(); // alto del sprite2
        x1=getX(); // posicion X del sprite1
        y1=getY(); // posicion Y del sprite1
        x2=s.getX(); // posicion X del sprite2
        y2=s.getY(); // posicion Y del sprite2
        if (((x1+w1)>x2)&&((y1+h1)>y2)&&((x2+w2)>x1)&&((y2+h2)>y1)) {
            return true;
        } else {
            return false;
        }
    }

    //Dibuja los sprites
    public void draw(Graphics g){
        g.drawImage(sprites[frame], posicionX, posicionY,
                    Graphics.HCENTER|Graphics.VCENTER);
    }


}
