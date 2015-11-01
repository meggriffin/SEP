package snake.model.mapelement;

import snake.model.Map;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public class SnakeElement extends MapElements{

	
	
	public SnakeElement(MapPosition mapPosition)
	{
		this.setMapPosition(mapPosition);
		setMapElementType(MapElementType.SNAKE);

	}
	/**
	 * handles the collision for the Snake element.
	 * disables the snake -> invoiking game over
	 */
	public MapPosition collide(SnakeThread snakeThread, MapPosition mapPosition)
	{
		snakeThread.getGameInstance().gameOver(); 
		return mapPosition;
	}
	public void text(){
		System.out.println("test");
	}
}
