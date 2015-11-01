package snake.model.entities;

import snake.GameInstance;

public interface Effect {
	public void stopEffect(GameInstance gameInstance);
	public void startEffect(GameInstance gameInstance);
	public String getEffectName();
}
