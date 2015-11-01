package snake.utility;

public class MapPosition {

	private int xPosition;
	private int yPosition;

	public MapPosition(int xPosition, int yPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public MapPosition(MapPosition mapPosition)
	{
		xPosition = mapPosition.getXPosition();
		yPosition = mapPosition.getYPosition();

	}

	public void setXYPosition(int xPosition, int yPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public int getYPosition()
	{
		return yPosition;
	}

	public void setYPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}

	public int getXPosition()
	{
		return xPosition;
	}

	public void setXPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public void addYPosition(int addy)
	{
		this.yPosition = yPosition + addy;
	}

	public void addXPosition(int addx)
	{
		this.xPosition = xPosition + addx;
	}

	public boolean compare(MapPosition mapPosition)
	{
		return ((xPosition == mapPosition.getXPosition()) && yPosition == mapPosition.getYPosition());
	}

	@Override
	public String toString()
	{
		return "(" + xPosition + "," + yPosition + ")";
	}
}
