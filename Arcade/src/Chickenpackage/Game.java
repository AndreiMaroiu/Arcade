package Chickenpackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends Canvas implements Runnable
{

    private Display showGame;

    public static final int width = 600;
    public static final int height = 600;
    public static final int scale = 1;
    public final String title = "Chicken Invaders";
    private boolean running = false;

    private boolean isShooting = false;

    private Thread thread;

    BufferedImage back;
    BufferedImage ship;
    BufferedImage bullet;
    BufferedImage chick;
    BufferedImage egg;
    BufferedImage present;
    BufferedImage heart;

    private int pSpeed = 10;
    private int objSpeed = 2;
    private int bSpeed = 20;
    private int chickNr = 20;
    private static int genEggs = 0;
    private static int genPresents = 0;
    private static int maxHealth = 5;

    private Vector<Chicken> chicks;
    private Vector<Bullet> bullets;
    private Vector<Egg> eggs;
    private Vector<Present> presents;
    private Vector<Heart> hearts;

    private Player player;

    Controller c;
    Score score;

    public int getGameHeight()
    {
        return height;
    }

    public int getGameWidth()
    {
        return width;
    }

    public int getGameScale()
    {
        return scale;
    }

    public void init() throws IOException
    {
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyInput(this));
        loadImages();

        player = new Player(this.getWidth() / 2 - 70, this.getHeight() - 100, ship, this.getWidth(), this.getHeight());
        c = new Controller(this);
        chicks = new Vector<Chicken>(chickNr);
        bullets = new Vector<Bullet>();
        eggs = new Vector<Egg>();
        presents = new Vector<Present>();
        score = new Score();
        hearts = new Vector<Heart>(maxHealth);
        initHearts();
        initChicks();
    }

    private void loadImages() throws IOException
    {
        ship = ImageIO.read(new File("ship.gif"));
        bullet = ImageIO.read(new File("bullet.png"));
        chick = ImageIO.read(new File("chick.png"));
        back = ImageIO.read(new File("space.jpg"));
        egg = ImageIO.read(new File("egg.png"));
        present = ImageIO.read(new File("present.png"));
        heart = ImageIO.read(new File("health.png"));
    }

    private void initChicks()
    {
        int cX = 30;
        int cY = 50;
        for (int i = 0; i < chickNr; i++)
        {
            chicks.add(new Chicken(cX, cY, objSpeed, chick));
            cX += 50;
            if (i == 9)
            {
                cX = 30;
                cY += 45;
            }
        }
    }

    private void initHearts()
    {
        int hx = 30;
        int hy = getGameHeight() - 70;

        for (int i = 0; i < maxHealth; i++)
        {
            hearts.add(new Heart(hx, hy, heart));
            hx += 30;
        }
    }

    synchronized void start(Display showGame)
    {
        this.showGame = showGame;
        if (running)
        {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    synchronized void stop()
    {
        if (!running)
        {
            return;
        }
        running = false;
        /*try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.exit(1);*/
    }

    void printFPS(int updates, int frames)
    {
        System.out.println(updates + " Ticks, FPS: " + frames);
    }

    void executeRun()
    {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        long now = 0;

        while (running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1)
            {
                try
                {
                    tick();
                    updates++;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                delta--;
            }

            try
            {
                render();
                frames++;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() - timer > 1000)
            {
                printFPS(updates, frames);
                timer += 1000;
                frames = 0;

                if (running)
                {
                    generateEggs();
                    generatePresents();
                }
            }
        }
    }

    public void run()
    {
        createBufferStrategy(3);

        try
        {
            init();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        executeRun();

        stop();
    }

    private void tick() throws IOException
    {
        player.tick();
        c.tickBullets(bullets);
        c.tickEggs(eggs, getGameHeight());
        c.tickPresents(presents, getGameHeight());
        if (chicks.size() == 0)
        {
            score.alterLevel();
            initChicks();
        } else
        {
            moveChicks();
        }
        checkCollision();
        checkDamage();
        checkUpgrade();
        chekHealth();
        score.checkScore();
    }

    public void moveChicks()
    {
        for (int i = 0; i < chicks.size(); i++)
        {
            if (chicks.elementAt(i).getX() <= 0)
            {
                chicks.elementAt(i).setLeft(false);
                chicks.elementAt(i).setRight(true);
            }
            if (chicks.elementAt(i).getX() >= this.getWidth() - 60)
            {
                chicks.elementAt(i).setLeft(true);
                chicks.elementAt(i).setRight(false);
            }
            chicks.elementAt(i).tick();
        }
    }

    public void render() throws IOException
    {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }

        try
        {
            Graphics g = bs.getDrawGraphics();

            /////////////////////////////////
            g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
            player.render(g);
            c.renderBullets(g, bullets);
            c.renderEggs(g, eggs);
            c.renderPresents(g, presents);
            for (int i = 0; i < chicks.size(); i++)
            {
                chicks.elementAt(i).render(g);
            }
            c.renderHearts(g, hearts);
            score.render(g, this);
            /////////////////////////////////

            g.dispose();
            bs.show();
        } catch (Exception e)
        {

        }
    }

    public void keyPressed(KeyEvent e) throws IOException
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT)
        {
            player.setVelX(pSpeed);
        } else if (key == KeyEvent.VK_LEFT)
        {
            player.setVelX(-pSpeed);
        } else if (key == KeyEvent.VK_DOWN)
        {
            player.setVelY(pSpeed);
        } else if (key == KeyEvent.VK_UP)
        {
            player.setVelY(-pSpeed);
        } else if (key == KeyEvent.VK_SPACE && !isShooting)
        {
            tick();
            bullets.add(new Bullet(player.getX(), player.getY(), bSpeed, bullet));
            isShooting = true;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        switch (e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                player.setVelX(0);
                break;

            case KeyEvent.VK_LEFT:
                player.setVelX(0);
                break;

            case KeyEvent.VK_DOWN:
                player.setVelY(0);
                break;

            case KeyEvent.VK_UP:
                player.setVelY(0);
                break;

            case KeyEvent.VK_SPACE:
                isShooting = false;
                break;

            default:
                break;
        }
    }

    public void generateEggs()
    {
        if (genEggs % 4 == 0 && genEggs != 0 && chicks.size() > 0)
        {
            Random rand = new Random();
            int randNum = rand.nextInt(chicks.size());
            eggs.add(new Egg(chicks.elementAt(randNum).getX(), chicks.elementAt(randNum).getY(), objSpeed, egg));
        }
        genEggs++;
    }

    private void generatePresents()
    {
        if (genPresents % 4 == 0 && genPresents != 0)
        {
            Random rand = new Random();
            int randNum = rand.nextInt(chicks.size());
            presents.add(new Present(chicks.elementAt(randNum).getX(), chicks.elementAt(randNum).getY(), objSpeed, present));
        }
        genPresents++;
    }

    public void checkCollision()
    {
        if (bullets.isEmpty())
        {
            return;
        }

        for (int i = 0; i < bullets.size(); i++)
        {
            if (chicks.isEmpty())
            {
                continue;
            }

            for (int j = 0; j < chicks.size(); j++)
            {
                if (!bullets.elementAt(i).getBounds().intersects(chicks.elementAt(j).getBounds()))
                {
                    continue;
                }

                executeCollision(i, j);
                return;
            }
        }
    }

    void executeCollision(int i, int j)
    {
        System.out.println("Collision");
        this.score.alterScore(chicks.elementAt(j).getPoints());
        this.chicks.remove(j);
        this.bullets.remove(i);
    }

    public void checkDamage()
    {
        if (!eggs.isEmpty())
        {
            for (int i = 0; i < eggs.size(); i++)
            {
                if (player != null)
                {
                    if (eggs.elementAt(i).getBounds().intersects(player.getBounds()))
                    {
                        System.out.println("Collision Egg");
                        player.decrHealth();
                        eggs.remove(i);
                        hearts.remove(hearts.lastElement());
                        return;
                    }
                }
            }
        }
    }

    public void checkUpgrade()
    {
        if (!presents.isEmpty())
        {
            for (int i = 0; i < presents.size(); i++)
            {
                if (player != null)
                {
                    if (presents.elementAt(i).getBounds().intersects(player.getBounds()))
                    {
                        System.out.println("Collision Present");
                        score.alterScore(presents.elementAt(i).getPoints());
                        presents.remove(i);
                        return;
                    }
                }
            }
        }
    }

    public void chekHealth()
    {
        if (player.getHealth() == 0)
        {
            stop();
            showGame.endFrame();
        }
    }

    public boolean isRunning()
    {
        return running;
    }
}