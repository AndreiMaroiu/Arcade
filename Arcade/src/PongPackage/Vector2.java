package PongPackage;

public class Vector2 
{
	public static final Vector2 Zero = new Vector2(0, 0);
	public static final Vector2 Up = new Vector2(0, -1);
	public static final Vector2 Down = new Vector2(0, 1);
	public static final Vector2 Left = new Vector2(-1, 0);
	public static final Vector2 Right = new Vector2(1, 0);
	
	public float x;
	public float y;
	
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2()
	{
		x = 0;
		y = 0;
	}
	
	public Vector2(Vector2 other)
	{
		x = other.x;
		y = other.y;
	}

	
	public void Set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void Set(Vector2 other)
	{
		this.x = other.x;
		this.y = other.y;
	}
	
	public Vector2 plus(Vector2 other)
	{
		return new Vector2(x + other.x, y + other.y);
	}
	
	public Vector2 minus(Vector2 other)
	{
		return new Vector2(x - other.x, y - other.y);
	}
	
	public Vector2 multiply(float scalar)
	{
		return new Vector2(x * scalar, y * scalar);
	}
	
	public void normalize()
	{
		float m = getMagnitude();
		x = x / m;
		y = y / m;
	}
	
	public Vector2 getNormalized()
	{
		float m = getMagnitude();
		return new Vector2(x/m, y/m);
	}
	
	public float getMagnitude()
	{
		return (float)Math.sqrt((x * x) + (y * y));
	}
	
	public float getSquareMagnitude()
	{
		return (x * x) + (y * y);
	}
	
	public static float angle(Vector2 first, Vector2 second)
	{
		float angle = (float) Math.toDegrees(Math.atan2(second.y - first.y, second.x - first.x));
		
	    return (angle + 180) % 360;
	}
	
	public static Vector2 getDirection(float angle)
	{
		double a = Math.toRadians(angle);
		
		Vector2 direction = new Vector2((float)Math.cos(a), (float)Math.sin(a));
		return direction;
	}
	
	@Override
	public String toString()
	{
		return x + " " + y;
	}
}
