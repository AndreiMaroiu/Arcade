package Chickenpackage;

import java.awt.image.BufferedImage;

public class Egg extends GameObject


{

    private int speed;

    public Egg(int x, int y, int s, BufferedImage im) {
       super(x,y,25,im);
        this.speed = s;
    }

    public void tick() {
        bounds.y += speed;
    }
}