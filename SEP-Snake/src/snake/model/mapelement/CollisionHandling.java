package snake.model.mapelement;

import snake.GameInstance;
import snake.model.entities.SnakeThread;
import snake.utility.MapPosition;

public interface CollisionHandling {

	public MapPosition collide(SnakeThread snake, MapPosition mapPosition);
}
