package snake.model.entities;

import snake.GameInstance;
import snake.model.Map;
import snake.model.SnakeInstance;
import snake.model.mapelement.MapElements;
import snake.model.mapelement.SnakeElement;
import snake.utility.MapPosition;
import snake.utility.SnakeMovementHelper;

public class SnakeThread implements Entity{

	
	private SnakeMovementHelper movementHelper;
	
	private boolean start = true;

	private int grow = 3;
	
	private GameInstance gameInstance;
	private Map map;


	/**
	 * reference to the snake instance
	 */
	private SnakeInstance snakeInstance;

	@Override
	public void update(int gameTickTime) {
		
		snakeMove();
		
	}

	/**
	 * class constructor for The Snake starts a new Thread of Snake and adds it
	 * to the map
	 * 
	 * @param snakeInstance
	 * @param gameInstance
	 */
	public SnakeThread(GameInstance gameInstance, SnakeInstance snakeInstance)
	{
		this.gameInstance = gameInstance;
		this.map = gameInstance.getMap();
		this.snakeInstance = snakeInstance;
		movementHelper = new SnakeMovementHelper();
		gameInstance.addEntityToEntityList(this);
		
		// Adds Snake Elements to the map
		
		for(SnakeElement element:snakeInstance.getSnakeBody()){
			gameInstance.addElementToMap(element);
			
		}
	}

	/**
	 * performs next snake move 1. computes the next step location. 2. checks
	 * for unit collision, updates next step location if necessary 3. executes
	 * the next step
	 */
	public void snakeMove()
	{
		//TODO add the map changer calls
		MapPosition nextStep = snakeInstance.getNextStepPosition(map, this);
		nextStep = checkCollision(nextStep);
		snakeInstance.doStep(nextStep, map, this);
		gameInstance.addPointsToHighscore((int)(0.25*snakeInstance.getSnakeBody().size()) );
	}

	/**
	 * increases the speed of the snake for a set time
	 */
	public void buffSpeed()
	{

		//TODO creates new Entity GameEffect Effect EffectLoopTickTime
	}

	/**
	 * decreases the speed of the snake for a set time
	 */
	public void debuffSpeed()
	{
		//TODO creates new Entity GameEffect Effect EffectLoopTickTime
	}

	/**
	 * shortens the snake of 3 units
	 */
	public void looseButtPieces()
	{
		gameInstance.addPointsToHighscore(-400);
		int i = 3;
		while (i != 0)
		{
			snakeInstance.deleteTail(gameInstance);
			i--;
		}
	}

	/**
	 * subtracts 10% from the highscore
	 */
	public void decimatePoints()
	{
		gameInstance.addPointsToHighscore(-(int) (gameInstance.getHighscore() / 10));
	}

	/**
	 * increases the multiplier TODO maybe 0.2
	 */
	public void increaseScoreMultiplier()
	{
		// TODO Optional to many features
	}

	/**
	 * decreases the multiplier TODO care musn't fall below a certain value eg
	 * 0.2
	 */
	public void decreaseScoreMultiplier()
	{
		// TODO Optional to many features
	}

	/**
	 * checks which type of Collision Occurs and performs the collision either
	 * returning new next step or changing properties of the snake
	 * 
	 * 
	 * @param nextStep
	 * @return
	 */
	public MapPosition checkCollision(MapPosition nextStep)
	{
		MapElements collisionobject = map.findNode(nextStep);
		MapPosition returnValue = nextStep;
		if (collisionobject != null)
		{
			returnValue = collisionobject.collide(this, nextStep);
		}

		return returnValue;
	}

	/**
	 * returns the grow value
	 * 
	 * @return
	 */
	public int getGrow()
	{
		return grow;
	}

	/**
	 * returns the grow value
	 * 
	 * @param grow
	 */
	public void setGrow(int grow)
	{
		this.grow = grow;
	}

	/**
	 * returns the game instance
	 * 
	 * @return
	 */
	public GameInstance getGameInstance()
	{
		return gameInstance;
	}

	public SnakeInstance getSnakeInstance()
	{
		return snakeInstance;
	}

	public boolean getStart()
	{
		return start;
	}

	public void setStart(boolean start)
	{
		this.start = start;
	}

	public SnakeMovementHelper getMovementHelper() {
		return movementHelper;
	}


}
