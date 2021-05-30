package PongPackage;

public class Time 
{
	private static double timeScale = 1.0;
	private static double deltaTime = 0.0;
	private static double gameDeltaTime = 0.0;
	private static double realTime = 0.0;
	private static double gameTime = 0.0;
	private static long lastTime = System.nanoTime();
	private static long now;
	private static double delta = 0.0;
	
	public static void updateTime()
	{
		now = System.nanoTime();
		
		double deltaTime = (double) (now - lastTime) / 1_000_000_000;
		delta = deltaTime;
		realTime += deltaTime;
		deltaTime *= timeScale;
		gameTime += deltaTime;
		
		gameDeltaTime += deltaTime;
		
		lastTime = now;
	}
	
	public static void updateWasDone()
	{
		gameDeltaTime = 0.0;
	}
	
	public static double getRealTime()
	{
		return realTime;
	}
	
	public static double getDeltaTime()
	{
		return gameDeltaTime;
	}
	
	public static double getRealDelta()
	{
		return delta;
	}
	
	public static double getTimeScale()
	{
		return timeScale;
	}
	
	public static double getGameTime()
	{
		return gameTime;
	}
	
	public static void setTimeScale(double value)
	{
		if(value >= 0)
		{
			timeScale = value;
		}
	}
}