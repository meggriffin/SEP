package snake.model.entities;

import snake.GameInstance;

public class GameEffect implements Entity {

	private int lifetime;
	private GameInstance gameInstance;
	private String name;
	
	@Override
	public void update(int gameTickTime) {
		lifetime = lifetime - gameTickTime;
		if(lifetime <= 0){
			gameInstance.removeEntityFromEntityList(this);
			gameInstance.removeEffect(name);
		}
		
	}
	public GameEffect(int lifetime, GameInstance gameInstance, Effect effect){
		this.lifetime = lifetime;
		this.gameInstance = gameInstance;
		effect.startEffect(gameInstance);
		gameInstance.addEffect(effect.getEffectName());
		name = effect.getEffectName();
		
	}

}
