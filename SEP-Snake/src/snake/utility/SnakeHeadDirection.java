package snake.utility;

/**
 * 
 * @author Julian Schwab
 *
 */
public enum SnakeHeadDirection {
	UP, RIGHT, DOWN, LEFT;

	// private SnakeHeadDirection[] vals = values();
	/*
	 * public SnakeHeadDirection turnCounterClockwise() { return
	 * vals[(this.ordinal() + 1) % vals.length]; }
	 */
	public SnakeHeadDirection turnClockwise()
	{
		SnakeHeadDirection returnValue = null;
		switch (this)
		{
		case UP:
			returnValue = RIGHT;
			break;
		case RIGHT:
			returnValue = DOWN;
			break;
		case DOWN:
			returnValue = LEFT;
			break;
		case LEFT:
			returnValue = UP;
			break;
		}
		return returnValue;
	}

	/*
	 * public SnakeHeadDirection turnClockwise() { return vals[(this.ordinal() +
	 * 3) % vals.length]; }
	 */
	public SnakeHeadDirection turnCounterClockwise()
	{
		SnakeHeadDirection returnValue = null;
		switch (this)
		{
		case UP:
			returnValue = LEFT;
			break;
		case RIGHT:
			returnValue = UP;
			break;
		case DOWN:
			returnValue = RIGHT;
			break;
		case LEFT:
			returnValue = DOWN;
			break;
		}
		return returnValue;
	}

}
