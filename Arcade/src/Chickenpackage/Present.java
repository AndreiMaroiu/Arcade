package Chickenpackage;

import java.awt.image.BufferedImage;

public class Present extends GameObject

{
    private int speed;
    private int points;

    public Present(int x, int y, int s, BufferedImage im) {
        super(x,y,40,im);
        this.speed = s;
        points = 50;

    }

    public int getPoints() {
        return points;
    }

    public void tick() {
        bounds.y += speed;
    }
}
