package PongPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameOverPanel extends JPanel
{
    private static final long serialVersionUID = 172L;

    private static final Color BACKGROUNDCOLOR = new Color(15, 15, 15);

    private Dimension buttonSize = new Dimension(4, 1);
    private int middle;

    private JButton mainMenu;
    private JLabel gameOverText;
    private JLabel winnerText;

    private GamePanel panel;
    private JFrame frame;

    GameOverPanel(Dimension dimension, GamePanel panel, JFrame frame)
    {
        this.panel = panel;
        this.frame = frame;

        this.setFocusable(true);
        this.setPreferredSize(dimension);
        this.setLayout(null);
        this.setBackground(BACKGROUNDCOLOR);

        middle = dimension.width / 2 - buttonSize.width / 2;

        initPanel();
    }

    public void resizeUI()
    {
        middle = Screen.getWidth() / 2 - (buttonSize.width * Screen.getWidthUnit()) / 2;
        int height = 2;
        int center = (int)Screen.getMiddle().y;
        int hUnit = Screen.getHeightUnit();
        int wUnit = Screen.getWidthUnit();

        mainMenu.setBounds(middle,
                (int)(height * hUnit + center),
                buttonSize.width * wUnit,
                buttonSize.height * hUnit);

        gameOverText.setBounds((int)(1 * wUnit + Screen.getMiddle().x / 2), (int) (-4 * hUnit + center),
                7 * wUnit, 2 * hUnit);

        winnerText.setBounds((int)(0 * wUnit + Screen.getMiddle().x / 2), (int) (-2 * hUnit + center),
                12 * wUnit, 2 * hUnit);
    }

    public void setWinner(String winner, int score1, int score2)
    {
        String result = winner + " won with score " + score1 + ":" + score2;
        winnerText.setText(result);
    }


    private void initPanel()
    {
        mainMenu = new JButton("Main menu");

        gameOverText = new JLabel("Game Over!", JLabel.CENTER);
        gameOverText.setForeground(Color.white);
        gameOverText.setFont(new Font("Arial", Font.BOLD, 50));

        winnerText = new JLabel("Player 1 won with score 5:3");
        winnerText.setForeground(Color.white);
        winnerText.setFont(new Font("Arial", Font.BOLD, 40));

        this.add(gameOverText);
        this.add(mainMenu);
        this.add(winnerText);

        mainMenu.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent arg0)
                    {
                        GameOverPanel.this.panel.openStartMenu();
                    }
                });

        resizeUI();
    }
}
