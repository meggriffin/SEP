package snake.model;

import java.util.ArrayList;

import snake.GameInstance;
import snake.model.entities.SnakeThread;
import snake.model.mapelement.SnakeElement;
import snake.utility.MapPosition;
import snake.utility.SnakeHeadDirection;

public class SnakeInstance {

	private ArrayList<SnakeElement> snakeBody = new ArrayList<SnakeElement>();
	private SnakeHeadDirection snakeHeadDirection = SnakeHeadDirection.UP;

	/**
	 * testconstructor for a test snake
	 * 
	 * @param testSnake
	 */
	public SnakeInstance(String testSnake, Map map)
	{

		snakeBody.add(0, new SnakeElement(new MapPosition((map.getSizeX()) / 2, (map.getSizeY()) / 2)));
	}

	/**
	 * calcs the next stepPosition using facing enum and gethead !! Care u need
	 * to implement the MapMaxBounds too.
	 * 
	 * @return
	 */
	public MapPosition getNextStepPosition(Map map, SnakeThread snakeThread)
	{
		// Changes Direction of Head depending of UserInput
		snakeHeadDirection = snakeThread.getMovementHelper().getNextSnakeHeadDirection(snakeHeadDirection);
		// CLONE! SIDEEFFECT THORUGH MapPosition object edit -- fixed
		MapPosition returnValue = new MapPosition(snakeGetHead().getMapPosition());
		switch (snakeHeadDirection)
		{
		case UP:
			returnValue.addYPosition(-1);
			break;
		case LEFT:
			returnValue.addXPosition(-1);
			break;
		case DOWN:
			returnValue.addYPosition(1);
			break;
		case RIGHT:
			returnValue.addXPosition(1);
			break;
		}
		returnValue = checkBoundaries(map, returnValue);
		snakeThread.getMovementHelper().clearHelper();
		return returnValue;
	}

	/**
	 * Check for Bounds of the Map.
	 * 
	 * @param map
	 * @param position
	 * @return
	 */
	public MapPosition checkBoundaries(Map map, MapPosition position)
	{
		if (position.getXPosition() >= map.getSizeX() - 1)
		{
			position.addXPosition(-map.getSizeX());
		}
		if (position.getXPosition() < 0)
		{
			position.addXPosition(map.getSizeX());
		}
		if (position.getYPosition() >= map.getSizeY() - 1)
		{
			position.addYPosition(-map.getSizeY());
		}
		if (position.getYPosition() < 0)
		{
			position.addYPosition(map.getSizeY());
		}
		return position;
	}

	/**
	 * the snake performs a step given the parameters if the the grow value is >
	 * 0, the delete component is skipped
	 * 
	 * @param position
	 * @param map
	 * @param snakeThread
	 */
	public void doStep(MapPosition position, Map map, SnakeThread snakeThread)
	{

		SnakeElement snakeHead = new SnakeElement(position);
		snakeThread.getGameInstance().addElementToMap(snakeHead);
		snakeBody.add(0, snakeHead);

		// if snake does not grow delete tail for movement
		if (snakeThread.getGrow() == 0)
		{

			deleteTail(snakeThread.getGameInstance());

		} else
		{
			snakeThread.setGrow(snakeThread.getGrow() - 1);
		}
	}

	/**
	 * returns the snake body map elements
	 * 
	 * @return
	 */
	public ArrayList<SnakeElement> getSnakeBody()
	{
		return snakeBody;
	}

	/**
	 * sets the snake Body
	 * 
	 * @param snakeBody
	 */
	public void setSnakeBody(ArrayList<SnakeElement> snakeBody)
	{
		this.snakeBody = snakeBody;
	}

	/**
	 * gets the snake Head direction
	 * 
	 * @return enum HeadDirection
	 */
	public SnakeHeadDirection getSnakeHeadDirection()
	{
		return snakeHeadDirection;
	}

	/**
	 * sets the enum head direction
	 * 
	 * @param snakeHeadDirection
	 */
	public void setSnakeHeadDirection(SnakeHeadDirection snakeHeadDirection)
	{
		this.snakeHeadDirection = snakeHeadDirection;
	}

	/**
	 * returns the Snake Head
	 * 
	 * @return
	 */
	public SnakeElement snakeGetHead()
	{
		return snakeBody.get(0);
	}

	/**
	 * takes last element of snakeBody deletes it from the map and from the
	 * snakeBodylist itsself
	 */
	public void deleteTail(GameInstance gameInstance)
	{
		SnakeElement todelete = snakeBody.get((snakeBody.size() - 1));
		gameInstance.removeElementFromMap(todelete);
		snakeBody.remove(todelete);

	}

}
