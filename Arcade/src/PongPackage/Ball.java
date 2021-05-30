package PongPackage;

import java.awt.*;
import java.util.Random;

public class Ball extends GameObject
{
	public Vector2 velocity;
	
	private float speed = 5f;
	private Color color;
	
	public Ball(float x, float y, float size)
	{
		super(x, y, size, size);
		
		Random random = new Random();
		
		velocity = new Vector2((int)Math.pow(-1, random.nextInt(2)),
				(int)Math.pow(-1, random.nextInt(2)));
		
		color = Color.white;
	}
	
	public Ball(float x, float y, float size, Color color)
	{
		super(x, y, size, size);
		
		Random random = new Random();
		
		velocity = new Vector2((int)Math.pow(-1, random.nextInt(2)),
				(int)Math.pow(-1, random.nextInt(2)));
		
		this.color = color;
	}

	@Override
	public void move()
	{
		position = position.plus(velocity.multiply((float)(speed * Time.getDeltaTime())));
	}

	@Override
	public void checkCollision()
	{
		if(position.y <= -5 || position.y >= 5)
		{
			velocity.y *= -1;
		}
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
		
		g.fillOval(x - width / 2, y - height / 2, width, height);
	}
	
	public float getSize()
	{
		return size.x;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
}
