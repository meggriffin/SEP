package snake.model.mapelement;

import snake.model.Map;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public class WallElement extends MapElements{

	
	
	public WallElement(MapPosition mapPosition){
		this.setMapPosition(mapPosition);
		this.setMapElementType(MapElementType.WALL);
	}
	
	/**
	 * kills the snake
	 * returns same position
	 */
	@Override
	public MapPosition collide(SnakeThread snake, MapPosition mapPosition) {
		snake.getGameInstance().gameOver(); 
		//TODO Blinking animation
		return mapPosition;
	}

}
