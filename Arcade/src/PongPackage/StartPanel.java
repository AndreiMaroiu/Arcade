package PongPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel
{
	private static final long serialVersionUID = 101L;
	
	private static final Color BACKGROUNDCOLOR = new Color(6, 28, 54);
	static final int WIDTH = 1000;
	static final int HEIGHT = (int)(WIDTH * (0.5555555));
	static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
	private static final Dimension buttonSize = new Dimension(200, 100);
	
	private JButton start;
	private GamePanel gamePanel;
	private JFrame frame;
	private JPanel startMenu;

	public StartPanel(JFrame frame)
	{
		this.frame = frame;

		this.setFocusable(true);
		this.setPreferredSize(DIMENSION);
		this.setSize(DIMENSION);
		this.setLayout(null);
		this.setBackground(BACKGROUNDCOLOR);

		initPanel();
	}

	public StartPanel(JFrame frame, JPanel startMenu)
	{
		this.frame = frame;
		this.startMenu = startMenu;

		this.setFocusable(true);
		this.setPreferredSize(DIMENSION);
		this.setSize(DIMENSION);
		this.setLayout(null);
		this.setBackground(BACKGROUNDCOLOR);
		
		initPanel();

		System.out.println(getSize());
	}

	public void stopGame()
	{
		gamePanel.stop();
	}

	public boolean isRunning()
	{
		return gamePanel != null;
	}

	
	private void initPanel()
	{
		start = new JButton("Start");
		start.setBounds(WIDTH / 2 - buttonSize.width / 2, HEIGHT / 2 - buttonSize.height / 2, buttonSize.width, buttonSize.height);
		start.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						if(StartPanel.this.gamePanel != null)
						{
							StartPanel.this.frame.remove(gamePanel);
						}
						if(startMenu != null)
						{
							StartPanel.this.gamePanel = new GamePanel(StartPanel.this.startMenu, frame);
						}
						else
						{
							StartPanel.this.gamePanel = new GamePanel(StartPanel.this, frame);
						}
						StartPanel.this.frame.add(gamePanel);
						Time.setTimeScale(2.0f);
						StartPanel.this.setVisible(false);
					}
				});
		
		this.add(start);
	}
}
