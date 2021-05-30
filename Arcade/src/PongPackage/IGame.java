package PongPackage;

import java.awt.*;

public interface IGame
{
    void update();
    void checkCollision();
    void move();
    void draw(Graphics g);
    void gameLoop();
}
