package snake.model.mapelement;

import snake.model.Map;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public class BuffSpeedElement extends MapElements{

	
	
	/**
	 * invokes a BuffSpeed Thread
	 * returns same map position
	 */
	@Override
	public MapPosition collide(SnakeThread snake, MapPosition mapPosition) {
		snake.buffSpeed();
		snake.getGameInstance().removeElementFromMap(this);
		return mapPosition;
	}
	public BuffSpeedElement (MapPosition mapPosition){
		this.setMapPosition(mapPosition);
		
		setMapElementType(MapElementType.SPEEDBUFF);
	}

}
