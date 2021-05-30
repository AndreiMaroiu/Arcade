package Chickenpackage;

import java.awt.image.BufferedImage;

public class Chicken extends GameObject

{
    private int speed;
    private int points;

    private boolean moveLeft = false;
    private boolean moveRight = true;

    public Chicken(int x, int y,int s, BufferedImage im) {
        super(x,y,70, im);
        this.speed = s;
        this.points = 10;
        bounds.x += fWidth / 2;
    }

    public void tick() {
        if (moveLeft == true)
            bounds.x -= speed;
        if (moveRight == true)
            bounds.x += speed;
    }

    public int getPoints() {
        return points;
    }
    public void setLeft(boolean b) {
        this.moveLeft = b;
    }
    public void setRight(boolean b) {
        this.moveRight = b;
    }
}