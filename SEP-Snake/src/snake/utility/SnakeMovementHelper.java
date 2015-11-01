package snake.utility;

import java.util.ArrayList;
import java.util.List;

public class SnakeMovementHelper {

	private List<SnakeHeadDirection> inputlog;
	
	
	public SnakeMovementHelper(){
		inputlog = new ArrayList<SnakeHeadDirection>();
	}
	public void addDirection(SnakeHeadDirection keyInput){
		if(!inputlog.contains(keyInput)){
			inputlog.add(keyInput);
		}	
	}
	/**
	 * inputlog: only 1 of each enum can be contained
	 * removes the current head Direction and the opposite of it from the log list
	 * if the list size = 1 set the output to the only one object left
	 * if the list size = 2 sets the output to the input direction because both "left and right" had been pressed
	 * 
	 * @param snakeHeadDirection
	 * @return
	 */
	public SnakeHeadDirection getNextSnakeHeadDirection(SnakeHeadDirection snakeHeadDirection){
		
		if(inputlog.contains(snakeHeadDirection)){
			inputlog.remove(snakeHeadDirection);
		}if(inputlog.contains(snakeHeadDirection.turnClockwise().turnClockwise())){
			inputlog.remove(snakeHeadDirection.turnClockwise().turnClockwise());
		}
		if(inputlog.size()==1){
			return inputlog.get(0);
		}else{
			return snakeHeadDirection;
		}	
	}
	/**
	 * resets the list
	 */
	public void clearHelper(){
		inputlog.clear();
	}
	
	
	
	
}
