package SnakePackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener
{

	static  int SCREEN_WIDTH = 750;
    static  int SCREEN_HEIGHT = 550;
    static  int UNIT_SIZE = 50;
    static  int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    int DELAY = 175;
    int x[] = new int[GAME_UNITS];
    int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    JButton button_restart;
    JButton button_easy;
    JButton button_medium;
    JButton button_hard;
    Image apple,back,game_over,head_up,head_down,head_left,head_right,body_up,body_down;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    int highscore=0;
    int start=1;

    GamePanel(int input, int width, int height) throws IOException 
    {
        DELAY=input;
        random = new Random();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.requestFocus();
        this.setLocation(new Point(0,0));
        startGame();
    }
    
    public void startGame() throws IOException
    {
        Image image = ImageIO.read(new File("apple.png"));
        apple = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("black.jpg"));
        game_over = image.getScaledInstance(SCREEN_WIDTH,SCREEN_HEIGHT,Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("head_down.png"));
        head_down = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("head_up.png"));
        head_up = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("head_right.png"));
        head_right = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("head_left.png"));
        head_left = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("body_down.png"));
        body_down = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("body_up.png"));
        body_up = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        image = ImageIO.read(new File("background.png"));
        back = image.getScaledInstance(SCREEN_WIDTH,SCREEN_HEIGHT,Image.SCALE_SMOOTH);
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {

        g.drawImage(back,0,0,null);
        if(running)
        {
            g.drawImage(apple,appleX,appleY,null);
            
            for(int i = 0; i< bodyParts;i++) 
            {
                if(i == 0) 
                {
                    switch(direction)
                    {
                        case 'U':
                            g.drawImage(head_up, x[i], y[i], null);
                            break;
                        case 'D':
                            g.drawImage(head_down, x[i], y[i], null);
                            break;
                        case 'L':
                            g.drawImage(head_left, x[i], y[i], null);
                            break;
                        case 'R':
                            g.drawImage(head_right, x[i], y[i], null);
                            break;
                    }

                }
                else
                {
                	g.drawImage(body_up, x[i], y[i], null);
                }
            }
            g.setColor(Color.WHITE);
            g.setFont( new Font("Comic Sans MS",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+ applesEaten)) - 20, g.getFont().getSize());
        }
        else
        {
            g.drawImage(game_over,0,0,null);
            gameOver(g);
        }

    }
    public void newApple()
    {
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    
    public void move()
    {
        for(int i = bodyParts; i > 0; --i)
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction) 
        {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }
    public void checkApple() 
    {
        if((x[0] == appleX) && (y[0] == appleY)) 
        {
            bodyParts++;
            applesEaten++;
            if(highscore < applesEaten)
                highscore=applesEaten;
            newApple();
        }
    }
    
    public void checkCollisions() 
    {
        // body
        for(int i = bodyParts; i > 0; --i)
        {
            if((x[0] == x[i]) && (y[0] == y[i]))
            {
                running = false;
            }
        }
        
        //border
        if(x[0] < 0 || x[0] >= SCREEN_WIDTH - 18 || y[0] < 0 || y[0] >= SCREEN_HEIGHT - 15)
        {
            running = false;
        }

        if(running==false)
        {
            timer.stop();
        }
    }
    public void gameOver(Graphics g)
    {

        g.setColor(Color.WHITE);
        g.setFont( new Font("Comic Sans MS",Font.BOLD, 80));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2-70, g.getFont().getSize()+150);
        g.setFont( new Font("Comic Sans MS",Font.BOLD, 40));
        metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()+430);
        g.drawString("Highscore: "+highscore, (SCREEN_WIDTH - metrics1.stringWidth("Highscore: "+highscore))/2, g.getFont().getSize()+370);
        button_restart=new JButton("Restart");
        button_restart.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
        button_restart.setBackground(Color.WHITE);
        button_restart.setBounds((SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2+10, g.getFont().getSize()+450,150,30);
        add(button_restart);
         x = new int[GAME_UNITS];
         y = new int[GAME_UNITS];
         bodyParts = 6;
         applesEaten=0;
         direction = 'R';
         running = false;
        button_restart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ex)
            {
            remove(button_restart);
                try {
                    startGame();
                    draw(g);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {

        if(running) 
        {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e) 
        {
            switch(e.getKeyCode()) 
            {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') 
                    {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') 
                    {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') 
                    {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') 
                    {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

}