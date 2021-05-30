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
import java.rmi.dgc.Lease;

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

public class MenuPanel extends JFrame  {

    static final int SCREEN_WIDTH = 750;
    static final int SCREEN_HEIGHT = 580;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    int DELAY=1;
    JButton button_easy;
    JButton button_medium;
    JButton button_hard;
    Image image,back;


    public MenuPanel() throws IOException {
        image = ImageIO.read(new File("background.png"));
        back = image.getScaledInstance(SCREEN_WIDTH,SCREEN_HEIGHT,Image.SCALE_SMOOTH);
        JLabel background=new JLabel(new ImageIcon(back));
        add(background);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        //this.setFocusable(true);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        button_easy=new JButton("Easy");
        button_easy.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
        button_easy.setBackground(Color.WHITE);
        button_easy.setBounds(300, 180,150,30);
        background.add(button_easy);
        button_medium=new JButton("Medium");
        button_medium.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
        button_medium.setBackground(Color.WHITE);
        button_medium.setBounds(300, 280,150,30);
        background.add(button_medium);
        button_hard=new JButton("Hard");
        button_hard.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
        button_hard.setBackground(Color.WHITE);
        button_hard.setBounds(300, 380,150,30);
        background.add(button_hard);
        JLabel text=new JLabel("Choose the difficulty");
        text.setBounds(270, 90,400,30);
        text.setFont(new Font("Comic Sans MS",Font.BOLD, 20));
        background.add(text);
        button_easy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ex)
            {
                DELAY=250;
                try {
                    startGame();
                    //dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button_medium.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ex)
            {
                DELAY=150;
                try {
                    startGame();
                    //dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button_hard.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ex)
            {
                DELAY=50;
                try {
                    startGame();
                    //dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    
    public void startGame() throws IOException
    {
    	//GameFrame game = new GameFrame(DELAY);
    	GamePanel game = new GamePanel(DELAY, this.getSize().width, this.getSize().height);
    	
    	game.requestFocus();
    	game.setFocusable(true);
    	add(game);
        button_easy.setVisible(false);
        button_medium.setVisible(false);
        button_hard.setVisible(false);
    }

}




