package Chickenpackage;

import java.awt.image.BufferedImage;

public class Bullet extends GameObject
{
    private int speed;

    public Bullet(int x, int y, int s, BufferedImage im)
    {
        super(x,y,50,im);
        this.speed = s;
    }

    public void tick() {
        bounds.y -= speed;
    }
}