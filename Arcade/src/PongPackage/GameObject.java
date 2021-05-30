package PongPackage;

import java.awt.*;

public abstract class GameObject
{
    public Vector2 position;
    public Vector2 size;

    public GameObject()
    {
        this.position = new Vector2();
        this.size = new Vector2();
    }

    public GameObject(Vector2 position, Vector2 size)
    {
        this.position = new Vector2(position);
        this.size = new Vector2(size);
    }

    public GameObject(float x, float y, float width, float height)
    {
        position = new Vector2(x, y);
        size = new Vector2(width, height);
    }

    public abstract void draw(Graphics g);
    public abstract void move();
    public abstract void checkCollision();
}
