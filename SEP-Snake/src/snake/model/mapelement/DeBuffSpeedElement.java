package snake.model.mapelement;

import snake.model.Map;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public class DeBuffSpeedElement extends MapElements{

	
	/**
	 * invokes a debuff speed thread
	 * returns same position
	 */
	@Override
	public MapPosition collide(SnakeThread snake, MapPosition mapPosition) {
		snake.debuffSpeed();
		snake.getGameInstance().removeElementFromMap(this);
		return mapPosition;
	}
	public DeBuffSpeedElement (MapPosition mapPosition){
		this.setMapPosition(mapPosition);
		setMapElementType(MapElementType.SPEEDDEBUFF);
	}
}
