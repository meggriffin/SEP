package snake.model.mapelement;

import snake.model.Map;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public class Apple extends MapElements {

	
	/**
	 * at collision increases the grow value returns same map position
	 */
	@Override
	public MapPosition collide(SnakeThread snake, MapPosition mapPosition)
	{
		snake.setGrow(snake.getGrow() + 1);
		snake.getGameInstance().addPointsToHighscore(100);
		snake.getGameInstance().removeElementFromMap(this);
		return mapPosition;
	}

	public Apple(MapPosition mapPosition)
	{
		this.setMapPosition(mapPosition);
		setMapElementType(MapElementType.APPLE);
	}

}
