package PongPackage;

import java.awt.*;

public class Screen 
{
	private static final float SCALEWIDTH = GamePanel.dimension.width;
	private static final float SCALEHEIGHT = GamePanel.dimension.height;
	private static final int UNITSPERHEIGTH = 10;
	private static final int UNITSPERWIDTH = 18;
	
	private static Dimension size = new Dimension();
	private static int heightUnit = (int)SCALEHEIGHT / UNITSPERHEIGTH;
	private static int widthUnit = (int)SCALEWIDTH / UNITSPERWIDTH;
	
	public static void updateScreen(Dimension size)
	{
		Screen.size = size;
		heightUnit = (int)size.height / UNITSPERHEIGTH;
		widthUnit = (int)size.width / UNITSPERWIDTH;
	}
	
	public static int getUnitSize()
	{
		return heightUnit;
	}
	
	public static int getHeightUnit()
	{
		return heightUnit;
	}
	
	public static int getWidthUnit()
	{
		return widthUnit;
	}

	public static Vector2 getMiddle()
	{
		return new Vector2(size.width / 2, size.height / 2);
	}

	public static int getWidth()
	{
		return size.width;
	}

	public static int getHeight()
	{
		return size.height;
	}
	
	public static Vector2 getSizeVector2()
	{
		return new Vector2(size.width, size.height);
	}
	
	public static Dimension getSizeDim()
	{
		return new Dimension(size);
	}
	
	public static Vector2 getLeft()
	{
		return new Vector2(0, size.height / 2);
	}
	
	public static Vector2 getRight()
	{
		return new Vector2(size.width, size.height / 2);
	}
	
	public static Vector2 getTop()
	{
		return new Vector2(size.width / 2, 0);
	}
	
	public static Vector2 getBottom()
	{
		return new Vector2(size.width / 2 , size.height);
	}
	
	public static Vector2 getTopLeft()
	{
		return new Vector2(0, 0);
	}
	
	public static Vector2 getTopRight()
	{
		return new Vector2(size.width, 0);
	}
	
	public static Vector2 getBottomLeft()
	{
		return new Vector2(0, size.height);
	}
	
	public static Vector2 getBottomRight()
	{
		return new Vector2(size.width, size.height);
	}
}
