package Chickenpackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame
{
    private static BufferedImage back;
    private static JFrame frame;
    private static ImagePanel mainPanel;
    private static Component text;
    private static Component subText;
    private static Component endText;
    private static Component endSubText1;
    private static Component endSubText2;
    private static Component endSubText3;
    private static Dimension d;
    private Game game;
    private boolean clicked;

    public  Display(Game game1) throws IOException {
        game = game1;
        frame = new JFrame(game.title);
        d = new Dimension(game.getGameWidth() * game.getGameScale(), game.getGameHeight() * game.getGameScale());
        initFrame();
        startFrame();

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!clicked) {
                    mainPanel.remove(text);
                    mainPanel.remove(subText);
                    frame.add(game);
                    mainPanel.setVisible(false);
                    //frame.revalidate();
                    frame.repaint();
                    game.setVisible(true);
                    game.start(Display.this);
                    clicked = true;
                }else{
                    try {
                        restartFrame();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }

            }
        });
    }

    private void initFrame() throws IOException {
        back = ImageIO.read(new File("space.jpg"));
        text = new JLabel("Chicken Invaders", JLabel.CENTER);
        subText = new JLabel("Click to start playing", JLabel.CENTER);
        endText = new JLabel("GAME OVER", JLabel.CENTER);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                if(game != null)
                {
                    game.stop();
                }
            }
        });
        frame.setResizable(false);
        frame.setBackground(Color.black);

        mainPanel = new ImagePanel(back, d);
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);

        frame.setSize(d);

        text.setFont(new Font("Courier", Font.PLAIN, 50));
        subText.setFont(new Font("Courier", Font.PLAIN, 25));

        text.setBackground(new Color(0, 0, 0, 0));
        text.setForeground(Color.white);

        subText.setBackground(new Color(0, 0, 0, 0));
        subText.setForeground(Color.white);

        text.setBounds(0, 200, 600, 50);
        subText.setBounds(90, 250, 400, 50);

        mainPanel.setSize(d);
        mainPanel.setLocation(0, 0);
        mainPanel.setBackground(new Color(0, 0, 0, 0));
    }

    public void startFrame() throws IOException {
        mainPanel.add(text);
        mainPanel.add(subText);

        frame.add(mainPanel);
        //frame.revalidate();
        frame.repaint();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void endFrame() {
        game.setVisible(false);

        //frame.revalidate();
        frame.repaint();

        endSubText1 = new JLabel("Your Score: " + game.score.getScore(), JLabel.CENTER);
        endSubText2 = new JLabel("At Level : " + game.score.getLevel(), JLabel.CENTER);
        endSubText3 = new JLabel("Click to restart the game", JLabel.CENTER);

        endText.setFont(new Font("Courier", Font.PLAIN, 50));
        endSubText1.setFont(new Font("Courier", Font.PLAIN, 25));
        endSubText2.setFont(new Font("Courier", Font.PLAIN, 25));
        endSubText3.setFont(new Font("Courier", Font.PLAIN, 25));

        endText.setBackground(new Color(0, 0, 0, 0));
        endText.setForeground(Color.white);

        endSubText1.setBackground(new Color(0, 0, 0, 0));
        endSubText1.setForeground(Color.white);

        endSubText2.setBackground(new Color(0, 0, 0, 0));
        endSubText2.setForeground(Color.white);

        endSubText3.setBackground(new Color(0, 0, 0, 0));
        endSubText3.setForeground(Color.white);

        endText.setBounds(90, 200, 400, 50);
        endSubText1.setBounds(90, 250, 400, 50);
        endSubText2.setBounds(90, 280, 400, 50);
        endSubText3.setBounds(90, 310, 400, 50);

        mainPanel.add(endText);
        mainPanel.add(endSubText1);
        mainPanel.add(endSubText2);
        mainPanel.add(endSubText3);

        mainPanel.setVisible(true);

        //frame.revalidate();
        frame.repaint();
    }

    private void restartFrame() throws IOException {
        mainPanel.remove(endText);
        mainPanel.remove(endSubText1);
        mainPanel.remove(endSubText2);
        mainPanel.remove(endSubText3);
        frame.revalidate();
        clicked = false;
        startFrame();
    }

}
