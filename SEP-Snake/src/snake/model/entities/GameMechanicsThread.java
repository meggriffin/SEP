package snake.model.entities;

import snake.GameInstance;
import snake.model.Map;
import snake.model.mapelement.Apple;
import snake.model.mapelement.BuffSpeedElement;
import snake.model.mapelement.DeBuffSpeedElement;
import snake.model.mapelement.DecimateHighscore;
import snake.model.mapelement.MapElements;
import snake.model.mapelement.SuperApple;
import snake.model.mapelement.WallElement;
import snake.utility.MapPosition;

/**
 * Thread-class, which is responsible for initializing and spawning Map-Elemets
 * on the assigned map.
 */
public class GameMechanicsThread implements Entity{

	private Map map;
	private GameInstance gameInstance;
	private int doActionTimer;

	/**
	 * Is called by the constructor and spawns MapElements at a spawn ratio. Is
	 * paused and ended with the paused and active snake.
	 */
	@Override
	public void update(int gameTickTime) {
		doActionTimer = doActionTimer - gameTickTime;
		if(doActionTimer <= 0){
			MapElements toPlaceObject = randomGeneratorObject();
			placeObject(toPlaceObject);			
			doActionTimer = doActionTimer +40000 / map.getSizeY();
			//TODO Maybe theres a logic flaw regarding the downcounting
			
		}
		
		
		
	}

	/**
	 * Constructor, that initializes some MapElements and starts the thread.
	 * 
	 * @param map
	 * @param gameInstance
	 * @param snakeThread
	 * @param mapNumber
	 *            To generate different map types.
	 */
	public GameMechanicsThread(GameInstance gameInstance, int mapNumber)
	{
		this.map = gameInstance.getMap();
		this.gameInstance = gameInstance;
		doActionTimer = 40000 / map.getSizeY();
		gameInstance.addEntityToEntityList(this);
		
		switch (mapNumber)
		{
		case 0:
			generateBounds(0, 0, 0, 0);
			break;
		case 1:
			generateBounds(1, 1, 0, 0);
			break;
		case 2:
			generateBounds(4, 6, 3, 4);
			break;
		}
	}

	/**
	 * Add the element to the ObservableList.
	 * 
	 * @param element
	 */

	public void placeObject(MapElements element)
	{
		gameInstance.addElementToMap(element);
		
	}

	/**
	 * Generates a MapElement to be added to the ObservableList. Uses the spawn
	 * probability of generateRandomSpawn(). Some elements create a lifetime
	 * thread, that despawns them after a certain amount of time.
	 * 
	 * TODO replace the element lifetimethreads with ItemWith Lifetime enities in the game instance
	 * @return
	 */
	public MapElements randomGeneratorObject()
	{
		MapElements returnValue = null;
		switch (generateRandomSpawn())
		{
		case 0:
			returnValue = new Apple(checkAvailableRandomPosition());
			break;
		case 1:
			returnValue = new BuffSpeedElement(checkAvailableRandomPosition());
			gameInstance.addEntityToEntityList(new ItemWithLifetime(returnValue, 5000, gameInstance));
			break;
		case 2:
			returnValue = new DeBuffSpeedElement(checkAvailableRandomPosition());
			gameInstance.addEntityToEntityList(new ItemWithLifetime(returnValue, 5000, gameInstance));
			break;
		case 3:
			returnValue = new DecimateHighscore(checkAvailableRandomPosition());
			gameInstance.addEntityToEntityList(new ItemWithLifetime(returnValue, 10000, gameInstance));
			break;
		case 4:
			returnValue = new SuperApple(checkAvailableRandomPosition());
			gameInstance.addEntityToEntityList(new ItemWithLifetime(returnValue, 2500, gameInstance));			break;
		}
		return returnValue;
	}

	/**
	 * Method to generate different probabilities for different map elements
	 * based on number intervals; e.g. the apple is very likely to spawn so it
	 * has the widest interval. Intervals can be modified.
	 * 
	 * @return
	 */
	public int generateRandomSpawn()
	{
		double random = Math.random() * 17;
		int returnValue = -1;
		if (random < 10)
		{
			returnValue = 0; // Apple spawn probability
		}
		if (random >= 10 && random < 12)
		{
			returnValue = 1; // SpeedBuff spawn probability
		}
		if (random >= 12 && random < 14)
		{
			returnValue = 2; // SpeedDebuff spawn probability
		}
		if (random >= 14 && random < 16)
		{
			returnValue = 3; // HighscoreDecimate spawn probability
		}
		if (random >= 16 && random < 17)
		{
			returnValue = 4; // Superapple spawn probability
		}
		return returnValue;
	}

	/**
	 * Algorithm which creates (and adds) wall elements for the map boundaries.
	 * It alternatingly spawns wall and gap blocks based on the min and max
	 * inputs of their length. Example: If minWall=2 and maxWall=4 their is a
	 * 0.33% chance to spawn 2 or 3 or 4 wall elements per block. The first loop
	 * generates the horizontal walls, the second loop generates the vertical
	 * walls. If
	 * 
	 * @param minWall
	 *            minimum lenght of wall blocks
	 * @param maxWall
	 *            maximum length of wall blocks MUST be greater than minWall!
	 * @param minGap
	 *            minimum length of gaps - 1
	 * @param maxGap
	 *            maximum length of gaps - 1 MUST be
	 */
	public void generateBounds(int minWall, int maxWall, int minGap, int maxGap)
	{
		int wallNumber = ((int) (Math.random() * (maxWall - minWall + 1))) + minWall;
		int gapNumber = ((int) (Math.random() * (maxGap - minGap + 1))) + minGap;
		if (wallNumber > 0)
		{
			placeObject(new WallElement(new MapPosition(0, 0)));
			placeObject(new WallElement(new MapPosition(map.getSizeX() - 1, 0)));
			placeObject(new WallElement(new MapPosition(0, map.getSizeY() - 1)));
			placeObject(new WallElement(new MapPosition(map.getSizeX() - 1, map.getSizeY() - 1)));
		}
		for (int i = 0; i < map.getSizeX() - 1; i++)
		{
			if (wallNumber > 0)
			{
				placeObject(new WallElement(new MapPosition(i, 0)));
				placeObject(new WallElement(new MapPosition(i, map.getSizeY() - 1)));
				wallNumber -= 1;
			}
			if (wallNumber == 0 && gapNumber == 0)
			{
				gapNumber = ((int) (Math.random() * (maxGap - minGap + 1))) + minGap;
			}
			if (gapNumber > 0)
			{
				gapNumber -= 1;
			}
			if (gapNumber == 0 && wallNumber == 0)
			{
				wallNumber = ((int) (Math.random() * (maxWall - minWall + 1))) + minWall;
			}
		}
		wallNumber = ((int) (Math.random() * (maxWall - minWall + 1))) + minWall;
		gapNumber = ((int) (Math.random() * (maxGap - minGap + 1))) + minGap;
		for (int i = 1; i < map.getSizeY() - 1; i++)
		{
			if (wallNumber > 0)
			{
				placeObject(new WallElement(new MapPosition(0, i)));
				placeObject(new WallElement(new MapPosition(map.getSizeX() - 1, i)));
				wallNumber -= 1;
			}
			if (wallNumber == 0 && gapNumber == 0)
			{
				gapNumber = ((int) (Math.random() * (maxGap - minGap + 1))) + minGap;
			}
			if (gapNumber > 0)
			{
				gapNumber -= 1;
			}
			if (gapNumber == 0 && wallNumber == 0)
			{
				wallNumber = ((int) (Math.random() * (maxWall - minWall + 1))) + minWall;
			}
		}
	}

	/**
	 * Check if the destined location is free and optional if there is no other
	 * object in immediate proximity (1) to avoid collisions maybe even only
	 * check for snake head
	 * 
	 * @return
	 */
	public MapPosition checkAvailableRandomPosition()
	{
		MapPosition returnValue = new MapPosition((int) (Math.random() * map.getSizeX()),
				(int) (Math.random() * map.getSizeY()));
		while (map.findNode(returnValue) != null)
		{
			returnValue = new MapPosition((int) (Math.random() * map.getSizeX()),
					(int) (Math.random() * map.getSizeY()));
		}
		return returnValue;
	}




}
