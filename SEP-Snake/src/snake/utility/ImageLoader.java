package snake.utility;

import javafx.scene.image.Image;
import snake.model.mapelement.MapElementType;

public class ImageLoader {

	private Image apple;
	private Image speedbuff;
	private Image speeddebuff;
	private Image decimatescore;
	private Image snake;
	private Image superapple;
	private Image wall;
	private Image targetportal;
	private Image snakehead;
	
	
	public ImageLoader(Double size){
		apple = new Image("file:resources/images/apple.png", size, size, false, false);
		speedbuff = new Image("file:resources/images/speedup_coke.png", size, size, false, false);
		speeddebuff = new Image("file:resources/images/slow_snail.png", size, size, false, false);
		decimatescore = new Image("file:resources/images/decimatepoints_skull.png", size, size, false, false);
		snake = new Image("file:resources/images/snake_element.png", size, size, false, false);
		superapple = new Image("file:resources/images/super_apple_burger.png", size, size, false, false);
		wall = new Image("file:resources/images/wall.png", size, size, false, false);
		targetportal= new Image("file:resources/images/portal.png", size, size, false, false);
		snakehead = new Image("file:resources/images/snake_head.png", size, size, false, false);
	}
	public Image getImage(MapElementType elementType){
		Image returnValue = snakehead;
		switch(elementType){
		case APPLE: 
			returnValue = apple;	
			break; 
		case SPEEDBUFF: 
			returnValue = speedbuff;
			break;
		case SPEEDDEBUFF: 
			returnValue = speeddebuff;
			break;
		case DECIMATESCORE: 
			returnValue = decimatescore;
			break;
		case SNAKE: 
			returnValue =snake;
			break;
		case SUPERAPPLE: 
			returnValue = superapple;
			break;
		case WALL: 
			returnValue = wall;
			break;
		case TARGETPORTAL: 
			returnValue = targetportal;
			break;
		}
		return returnValue;
		
	}
	
	
	
}
