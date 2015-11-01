package snake.model.mapelement;

import snake.model.Map;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public class TargetPorter extends MapElements {
	
	private MapPosition targetLocation;
	
	/**
	 * ports the snake to a target location.
	 * should be created in pairs 
	 */
	@Override
	public MapPosition collide(SnakeThread snake, MapPosition mapPosition) {
		// TODO Random mapPosition calculator ( maybe only spawn max distance to itself and walls = 4)
		
		
		return targetLocation;
	}
	/**
	 * should be created in pairs
	 * @param targetLocation
	 */
	public TargetPorter(MapPosition mapPosition,MapPosition targetLocation){
		this.setTargetLocation(targetLocation);
		setMapPosition(mapPosition);
		setMapElementType(MapElementType.TARGETPORTAL);
	}
	public MapPosition getTargetLocation() {
		return targetLocation;
	}
	public void setTargetLocation(MapPosition targetLocation) {
		this.targetLocation = targetLocation;
	}
	
}
