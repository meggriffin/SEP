package snake.view;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import snake.GameInstance;
import snake.MainApp;
import snake.model.Map;
import snake.model.MapChangeHelper;
import snake.model.entities.SnakeThread;
import snake.utility.ImageLoader;
import snake.utility.SnakeHeadDirection;

public class GameGraphicsViewController {

	@FXML
	private Canvas canvas;
	@FXML
	private Label highscore;

	private GraphicsContext graphicsContext;
	private GameInstance gameInstance;
	
	private double pixelSize;
	private double xOffset;
	private double yOffset;
	// TODO added for testing purpose
	private MainApp mainApp;

	private ImageLoader imageLoader;
	
	
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}

	

	//TableColumn tableColumn = new TableColumn();
	// TODO Is Raw Type -> parametize
	Label highscoreLabel = new Label();

	public GameGraphicsViewController()
	{

	}

	/**
	 * sets reference to graphicscontext
	 */
	public void initialize()
	{
		graphicsContext = canvas.getGraphicsContext2D();
		
		
		
		// create label for hightscore and list for effects.
		// in set new observedGameInstance bind them to the gameValue

	}

	/**
	 * sets the map reference for the controller calls recalculateView resets
	 * the content of the canvas adds listeners to the map
	 * 
	 * @param map
	 */
	public void setNewObservedGameInstance(GameInstance gameInstance)
	{
		this.gameInstance = gameInstance;
		recalculateViewSettings(
				new Double(gameInstance.getMap().getSizeX()), 
				new Double(gameInstance.getMap().getSizeY())
				);
		imageLoader = new ImageLoader(pixelSize);
		//draws a white background
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		//draws the alligned map
		graphicsContext.setFill(Color.LAVENDERBLUSH);
		graphicsContext.fillRect(
				xOffset, 
				yOffset, 
				new Double(gameInstance.getMap().getSizeX() * pixelSize),
				new Double(gameInstance.getMap().getSizeY() * pixelSize));

				drawCanvas(gameInstance);
		
		// Bind the highscore label to the highscoreProperty
		//TODO Change it to a update sort of thing into the update method
		highscore.textProperty().bind(Bindings.convert(gameInstance.getHighscoreProperty()));

		// TODO Bind highscore label to hightscoreproperty
		// TODO Bind tablecolumn to effectslistproperty
	}
	public void updateCanvas(MapChangeHelper mapChanges){
		graphicsContext.setFill(Color.LAVENDERBLUSH);
		mapChanges.getDeletedElements().forEach(elements -> graphicsContext.fillRect(
				xOffset + pixelSize * elements.getMapPosition().getXPosition(), 
				yOffset + pixelSize * elements.getMapPosition().getYPosition(), 
				pixelSize, pixelSize)
				);
		mapChanges.getCreatedElements().forEach(elements -> graphicsContext.drawImage(
				imageLoader.getImage(elements.getMapElementType()),
				xOffset + pixelSize * elements.getMapPosition().getXPosition(),
				yOffset + pixelSize * elements.getMapPosition().getYPosition())
				);	
	}
	

	/**
	 * Draws the game content Called by listener looking for changes in the map
	 */
	public void drawCanvas(GameInstance gameInstance)
	{
		Map map = gameInstance.getMap();
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphicsContext.setFill(Color.LAVENDERBLUSH);
		graphicsContext.fillRect(xOffset, yOffset, new Double(map.getSizeX() * pixelSize),
				new Double(map.getSizeY() * pixelSize));
		graphicsContext.setFill(Color.BLACK);

		map.getMapElements().forEach(elements -> graphicsContext.drawImage(imageLoader.getImage(elements.getMapElementType()),
				xOffset + pixelSize * elements.getMapPosition().getXPosition(),
				yOffset + pixelSize * elements.getMapPosition().getYPosition()));
	}

	/**
	 * calculates the positioning of the map in the canvas and the size of each
	 * pixel.
	 * 
	 * @param sizeX
	 *            of the map
	 * @param sizeY
	 *            of the map
	 */
	public void recalculateViewSettings(Double sizeX, Double sizeY)
	{

		Double canvasRatio = canvas.getWidth() / canvas.getHeight();
		Double mapRatio = sizeX / sizeY;
		if (canvasRatio > mapRatio)
		{
			pixelSize = canvas.getHeight() / sizeY;
			xOffset = (canvas.getWidth() % (sizeX * pixelSize)) / 2;
			yOffset = 0;

		} else if (canvasRatio < mapRatio)
		{
			pixelSize = canvas.getWidth() / sizeX;
			xOffset = 0;
			yOffset = (canvas.getHeight() % (sizeY * pixelSize)) / 2;

		} else
		{
			xOffset = 0;
			yOffset = 0;
			pixelSize = canvas.getWidth() / sizeX;

		}
	}

	/**
	 * Adds a key listener to the scene of gameGraphicsView.
	 * 
	 * @param snake
	 * @param game
	 */
	public void addKeyListener(SnakeThread snake, GameInstance game)
	{
		Scene scene = canvas.getParent().getScene();
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e)
			{
				switch(e.getCode()){
					case UP:		snake.getMovementHelper().addDirection(SnakeHeadDirection.UP);
									snake.getGameInstance().unPauseGame();
									break;
									
					case DOWN:		snake.getMovementHelper().addDirection(SnakeHeadDirection.DOWN);
									snake.getGameInstance().unPauseGame();
									break;	
								
					case LEFT:      snake.getMovementHelper().addDirection(SnakeHeadDirection.LEFT);
									snake.getGameInstance().unPauseGame();
									break;
									
					case RIGHT:    	snake.getMovementHelper().addDirection(SnakeHeadDirection.RIGHT);
									snake.getGameInstance().unPauseGame();					
									break;
									
					case SPACE: 	gameInstance.toggleGameIsPaused();				
				
					default:
					break;				
				
				}
			}
		});

	}

	public double getPixelSize()
	{
		return pixelSize;
	}

	public void setPixelSize(double pixelSize)
	{
		this.pixelSize = pixelSize;
	}

	public double getxOffset()
	{
		return xOffset;
	}

	public void setxOffset(double xOffset)
	{
		this.xOffset = xOffset;
	}

	public double getyOffset()
	{
		return yOffset;
	}

	public void setyOffset(double yOffset)
	{
		this.yOffset = yOffset;
	}

}
