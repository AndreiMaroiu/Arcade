package ArcadePackage;

import Chickenpackage.Game;
import PongPackage.GameFrame;
import PongPackage.StartPanel;
import Chickenpackage.*;
import SnakePackage.MenuPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;

public class ArcadePanel extends JPanel
{
    static final Color BACKGROUNDCOLOR = new Color(238, 138, 2);
    static final Dimension dimension = new Dimension(1000, 500);
    static final Dimension BUTTONSIZE = new Dimension(150, 100);

    JButton pongButton;
    JButton chickenButton;
    JButton snakeButton;

    JFrame frame;

    StartPanel pongPanel;
    Chickenpackage.Game chickenPanel;
    MenuPanel snakePanel;

    public Game game;
    public Display showGame;

    Image title;
    Image background;

    public ArcadePanel(JFrame frame) throws IOException
    {
        this.setFocusable(true);
        this.setPreferredSize(dimension);
        this.setBackground(BACKGROUNDCOLOR);
        this.setLayout(null);
        this.addComponentListener(new CL());

        this.frame = frame;

        title = ImageIO.read(new File("Arcade.png"));
        background = ImageIO.read(new File("ArcadeBackground.png"));

        initPanel();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, this.getSize().width, this.getSize().height, null);
        g.drawImage(title, getSize().width / 2 - title.getWidth(null) / 2, 50, null);

    }

    public void initPanel()
    {
        pongButton = new JButton("Pong");
        chickenButton = new JButton("Chicken Invaders");
        snakeButton = new JButton("Snake");

        pongButton.setBounds(250 - BUTTONSIZE.width / 2, dimension.height / 2 - BUTTONSIZE.height / 2, BUTTONSIZE.width, BUTTONSIZE.height);
        chickenButton.setBounds(500 - BUTTONSIZE.width / 2, dimension.height / 2 - BUTTONSIZE.height / 2, BUTTONSIZE.width, BUTTONSIZE.height);
        snakeButton.setBounds(750 - BUTTONSIZE.width / 2, dimension.height / 2 - BUTTONSIZE.height / 2, BUTTONSIZE.width, BUTTONSIZE.height);

        pongButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        GameFrame frame = new GameFrame();
                        frame.start();
                    }
                });

        chickenButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        Game game = new Game();
                        game.setPreferredSize(new Dimension(game.getGameWidth() * game.getGameScale(), game.getGameHeight() * game.getGameScale()));
                        game.setMinimumSize(new Dimension(game.getGameWidth() * game.getGameScale(), game.getGameHeight() * game.getGameScale()));
                        game.setMaximumSize(new Dimension(game.getGameWidth() * game.getGameScale(), game.getGameHeight() * game.getGameScale()));

                        try
                        {
                            showGame = new Display(game);
                        }
                        catch(IOException exception)
                        {

                        }
                    }
                });

        snakeButton.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        try
                        {
                            new MenuPanel();
                        }
                        catch(IOException exception)
                        {
                            exception.printStackTrace();
                            System.out.println("Could not start snake.");
                        }
                    }
                });

        this.add(pongButton);
        this.add(chickenButton);
        this.add(snakeButton);
    }

    void resizeUI()
    {
        Dimension size = this.getSize();
        pongButton.setBounds(size.width / 4 - BUTTONSIZE.width / 2, size.height / 2 - BUTTONSIZE.height / 2, BUTTONSIZE.width, BUTTONSIZE.height);
        chickenButton.setBounds(size.width / 2 - BUTTONSIZE.width / 2, size.height / 2 - BUTTONSIZE.height / 2, BUTTONSIZE.width, BUTTONSIZE.height);
        snakeButton.setBounds(size.width - size.width / 4 - BUTTONSIZE.width / 2, size.height / 2 - BUTTONSIZE.height / 2, BUTTONSIZE.width, BUTTONSIZE.height);
    }

    class CL implements ComponentListener
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
            ArcadePanel.this.resizeUI();
        }

        @Override
        public void componentMoved(ComponentEvent e) {}

        @Override
        public void componentShown(ComponentEvent e) {}

        @Override
        public void componentHidden(ComponentEvent e) {}
    }

}
