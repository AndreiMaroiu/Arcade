package PongPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	StartPanel startPanel;

	static final int WIDTH = 1000;
	static final int HEIGHT = (int)(WIDTH * (0.5555555));
	static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
	
	public GameFrame() {}
	
	public void start()
	{
		startPanel = new StartPanel(this);

		this.add(startPanel);
		this.setTitle("Pong Game");
		this.setResizable(true);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				super.windowClosing(e);

				if(startPanel.isRunning())
				{
					startPanel.stopGame();
				}
			}
		});
	}
}