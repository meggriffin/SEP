package snake.model.mapelement;

import snake.model.Map;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public class SuperApple extends MapElements  {

	
	
	
	public SuperApple(MapPosition mapPosition){
		setMapPosition(mapPosition);
		setMapElementType(MapElementType.SUPERAPPLE);
	}
	@Override
	public MapPosition collide(SnakeThread snake, MapPosition mapPosition) {
		// TODO Auto-generated method stub
		
		snake.setGrow(snake.getGrow()+3);
		snake.getGameInstance().addPointsToHighscore(1000);
		snake.getGameInstance().removeElementFromMap(this);
		return mapPosition;
	}
	
}
