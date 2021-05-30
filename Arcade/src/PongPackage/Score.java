package PongPackage;

import java.awt.*;

public class Score
{
	private int gameWidth;
	private int gameHeight;
	private int player1;
	private int player2;
	private Font font;
	
	Score(int width, int height)
	{
		gameWidth = width;
		gameHeight = height;
		font = new Font("Consolas", Font.PLAIN, 60);
	}
	
	public void setSize(Dimension size)
	{
		gameWidth = size.width;
		gameHeight = size.height;
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.setFont(font);
		
		g.drawLine(gameWidth/2, 0, gameWidth/2, gameHeight);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (gameWidth/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (gameWidth/2)+20, 50);
	}
	
	public void addPoint(int who)
	{
		if(who == 1)
		{
			player1 += 1;
		}
		else
		{
			player2 += 1;
		}
	}

	public int getPoint(int who)
	{
		if(who == 1)
		{
			return player1;
		}

		return player2;
	}

}