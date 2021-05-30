package ArcadePackage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ArcadeFrame extends JFrame
{
    private static final long serialVersionUID = 1L;
    ArcadePanel startPanel;

    static final int WIDTH = 1000;
    static final int HEIGHT = 500;
    static final Dimension dimension = new Dimension(WIDTH, HEIGHT);

    ArcadeFrame() {}

    void start()
    {
        try
        {
            startPanel = new ArcadePanel(this);
        }
        catch (IOException exception)
        {

        }
        this.add(startPanel);
        this.setTitle("Arcade");
        this.setResizable(true);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
