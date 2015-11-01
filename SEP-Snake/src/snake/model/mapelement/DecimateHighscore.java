package snake.model.mapelement;

import snake.model.Map;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public class DecimateHighscore extends MapElements {

	public DecimateHighscore(MapPosition mapPosition){
		setMapPosition(mapPosition);
		setMapElementType(MapElementType.DECIMATESCORE);
	}

	/**
	 * calls the decimate points method
	 * returns same position
	 */
	@Override
	public MapPosition collide(SnakeThread snake, MapPosition mapPosition) {
		snake.decimatePoints();
		snake.getGameInstance().removeElementFromMap(this);
		return mapPosition;
	
	}

}
