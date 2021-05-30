package Chickenpackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
    private int health;

    private int velX = 0;
    private int velY = 0;

    private int frameW;
    private int frameH;

    private int fHeight;


    public Player(int x, int y, BufferedImage im, int W, int H) {
        super(x,y,70,im);
        this.frameW = W;
        this.frameH = H;
        bounds.height = fWidth - 30;
        bounds.x += fWidth / 2;
        health = 5;
    }

    public void decrHealth() {
        health--;
    }

    public void tick() {
        bounds.x += velX;
        bounds.y += velY;
        if (bounds.x <= 0 + fWidth / 2)
            bounds.x = 0 + fWidth / 2;
        if (bounds.x >= frameW - fWidth / 2)
            bounds.x = frameW - fWidth / 2;
        if (bounds.y <= 150)
            bounds.y = 150;
        if (bounds.y >= frameH - 100)
            bounds.y = frameH - 100;
    }

    public int getHealth(){
        return health;
    }

    public void setVelX(int s) {
        this.velX = s;
    }

    public void setVelY(int s) {
        this.velY = s;
    }

    public void setWidth(int w) {
        this.fWidth = w;
    }

    public void setHeight(int h) {
        this.fHeight = h;
    }
}