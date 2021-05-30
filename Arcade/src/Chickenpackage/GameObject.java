package Chickenpackage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject
{
    protected int fWidth;

    protected Rectangle bounds;
    protected BufferedImage image;


    public int getX() {
        return bounds.x;
    }

    public int getY() {
        return bounds.y;
    }

    public void setX(int x) {
        this.bounds.x = x;
    }

    public void setY(int y) {
        this.bounds.y = y;
    }

    public int getWidth() {
        return fWidth;
    }

    public void setWidth(int w) {
        this.fWidth = w;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage i) {
        this.image = i;
    }

    GameObject(int x, int y, int fWidth, BufferedImage image) {
        this.image = image;
        this.fWidth = fWidth;
        bounds = new Rectangle(x, y, this.fWidth, this.fWidth);
    }

    public void render(Graphics g)
    {
        g.drawImage(image, bounds.x, bounds.y, fWidth, fWidth, null);
    }
}
