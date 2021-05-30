package PongPackage;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends GameObject
{
	private static float upperLimit = 4.1f;
	private static float lowerLimit = -upperLimit;

	float velocity;
	float speed = 5;
	Color color;

	int up;
	int down;

	boolean wasUpPressed;
	boolean wasDownPressed;
	
	public Paddle(float x, float y, Vector2 size, Color color)
	{
		super(x, y, size.x, size.y);
		this.color = color;
		wasDownPressed = false;
		wasUpPressed = false;
	}

	public Paddle(float x, float y, Vector2 size, Color color, int up, int down)
	{
		super(x, y, size.x, size.y);
		this.color = color;

		this.up = up;
		this.down = down;
	}
	
	public void keyPressed(KeyEvent e)
	{
		if((e.getKeyCode() == up) && !wasUpPressed)
		{
			wasUpPressed = true;
			velocity += (-1 * speed);
		}
		if((e.getKeyCode() == down) && !wasDownPressed)
		{
			wasDownPressed = true;
			velocity += (speed);
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == up)
		{
			wasUpPressed = false;
			velocity -= (-1 * speed);
		}
		if(e.getKeyCode() == down)
		{
			wasDownPressed = false;
			velocity -= speed;
		}
	}

	public void changeYVelocity(float velocity)
	{
		this.velocity = velocity;
	}

	@Override
	public void checkCollision()
	{
		if(position.y <= lowerLimit)
		{
			position.y = lowerLimit;
		}
		else if(position.y >= upperLimit)
		{
			position.y = upperLimit;
		}
	}

	@Override
	public void move()
	{
		position.y = position.y + (velocity * (float) Time.getDeltaTime());
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		
		Vector2 middle = Screen.getMiddle();
		
		int x = (int)(position.x * Screen.getWidthUnit() + middle.x);
		int y = (int)(position.y * Screen.getHeightUnit() + middle.y);
		int width = (int)(size.x * Screen.getWidthUnit());
		int height = (int)(size.y * Screen.getHeightUnit());
		
		g.fillRect(x - width / 2 , y - height / 2, width, height);
	}
}