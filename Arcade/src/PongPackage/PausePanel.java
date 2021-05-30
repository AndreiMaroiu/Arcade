package PongPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PausePanel extends JPanel
{
	private static final long serialVersionUID = 102L;
	
	private static final Color BACKGROUNDCOLOR = new Color(39, 174, 96);
	
	private Thread thread;
	private Dimension buttonSize = new Dimension(4, 2);
	private int middle;
	
	private JButton resume;
	private JButton exit;
	private JButton mainMenu;
	
	private GamePanel panel;
	private JFrame frame;
	
	private ArrayList<JButton> UIList;
	
	PausePanel(Dimension dimension, GamePanel panel, JFrame frame)
	{
		this.panel = panel;
		this.frame = frame;
		
		this.setFocusable(true);
		this.setPreferredSize(dimension);
		this.setLayout(null);
		this.setBackground(BACKGROUNDCOLOR);
		
		middle = dimension.width / 2 - buttonSize.width / 2;
		UIList = new ArrayList<JButton>();
		
		initPanel();
	}
	
	public void resizeUI()
	{
		middle = Screen.getWidth() / 2 - (buttonSize.width * Screen.getWidthUnit()) / 2;
		int height = -4;
		int center = (int)Screen.getMiddle().y;
		int hUnit = Screen.getHeightUnit();
		int wUnit = Screen.getWidthUnit();
		
		for(JButton button : UIList)
		{
			button.setBounds(middle, 
					(int)(height * hUnit + center),
					buttonSize.width * wUnit, 
					buttonSize.height * hUnit);
			
			height += 3;
		}
	}
	
	private void initPanel()
	{
		resume = new JButton("Resume");
		exit = new JButton("Exit Game");
		mainMenu = new JButton("Main menu");
		
		this.add(resume);
		this.add(exit);
		this.add(mainMenu);
		
		resume.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						PausePanel.this.setVisible(false);
						PausePanel.this.panel.resumeGame();
					}
				});
		
		exit.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						//System.exit(0);
						frame.dispose();
						panel.stop();
					}
				});
		
		mainMenu.addActionListener(
				new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						PausePanel.this.panel.openStartMenu();
					}
				});
		
		UIList.add(resume);
		UIList.add(mainMenu);
		UIList.add(exit);

		resizeUI();
	}
}
