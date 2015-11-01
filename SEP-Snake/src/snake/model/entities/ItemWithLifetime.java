package snake.model.entities;

import snake.GameInstance;
import snake.model.MapChangeHelper;
import snake.model.mapelement.MapElements;

public class ItemWithLifetime implements Entity {

	private int lifetime;
	private MapElements mapElement;
	private GameInstance gameInstance;
	
	@Override
	public void update(int gameTickTime) {
		lifetime = lifetime - gameTickTime;
		if(lifetime <= 0){
			gameInstance.removeElementFromMap(mapElement);
			gameInstance.removeEntityFromEntityList(this);
		}
	}
	
	
	public ItemWithLifetime(MapElements mapElement, int lifetime, GameInstance gameInstance){
		this.lifetime = lifetime;
		this.mapElement = mapElement;
		
		
		
		
		
	}

}
