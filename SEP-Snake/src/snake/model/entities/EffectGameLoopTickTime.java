package snake.model.entities;

import snake.GameInstance;

public class EffectGameLoopTickTime implements Effect {
	
	public String name;
	public double factor;
	
	
	public EffectGameLoopTickTime(String name, double factor){
		this.name = name;
		this.factor = factor;
	}
	
	@Override
	public void stopEffect(GameInstance gameInstance) {
		gameInstance.setGameTickTime((int)(gameInstance.getGameTickTime()/factor));
		
	}

	@Override
	public void startEffect(GameInstance gameInstance) {
		gameInstance.setGameTickTime((int)(gameInstance.getGameTickTime()*factor));
	}

	@Override
	public String getEffectName() {
		return name;
	}

}
