package snake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import snake.model.Map;
import snake.model.MapChangeHelper;
import snake.model.SnakeInstance;
import snake.model.entities.Entity;
import snake.model.entities.GameMechanicsThread;
import snake.model.entities.SnakeThread;
import snake.model.mapelement.MapElements;
import snake.view.GameGraphicsViewController;

public class GameInstance implements Runnable {


	
	private GameGraphicsViewController gameGraphicsViewController;

	private BooleanProperty gameIsPaused = new SimpleBooleanProperty(true);
	private BooleanProperty gameOver = new SimpleBooleanProperty(false);
	private int gameTickTime;
	private double scoreMultiplier = 1;
	
	private Map map;
	private IntegerProperty highscore;
	private List<Entity> gameEntities;
	private ListProperty<String >effects;
	private MapChangeHelper mapChanges;
	
	GameMechanicsThread gameMechanicsThread;
	SnakeThread snakeThread;

	/**
	 * The Game Loop
	 */
	@Override
	public void run() {
		
		while(!gameOver.get()){
			try{
				if(gameIsPaused.get()){
					Thread.sleep(200);
				}else{
					long startSystemTime = System.currentTimeMillis();
					
					mapChanges  = new MapChangeHelper();
					
					//TODO why does it not work each element should 
					//NEED Solution does not allow to delet elemts from this list while iterating throu it
					//gameEntities.forEach(element-> element.update(gameTickTime));
					for (Iterator<Entity> iterator = gameEntities.iterator(); iterator.hasNext(); ) {
					    Entity value = iterator.next();
					    	value.update(gameTickTime);
					    
					}
				/*DOES not work!! Cant edit intself at runtime
					for(Entity element:gameEntities){
						element.update(gameTickTime);
					}
				*/
					 
					gameGraphicsViewController.updateCanvas(mapChanges);
					
					pushMapChangesToStack();
					
					//lets the Thread for the rest of his clocktime not used for calculation
					Thread.sleep(gameTickTime - (System.currentTimeMillis()- startSystemTime));			
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}			
		}		
		System.out.println("GameOver");
	}
	/**
	 * Starts a game instance and allows players/snakes to be added
	 */
	public GameInstance(GameGraphicsViewController controller, Map map)
	{
		this.setMap(map);
		
		highscore  = new SimpleIntegerProperty(0);
		gameEntities = new ArrayList<Entity>();
		mapChanges = new MapChangeHelper();
		
		ObservableList<String> observableList = FXCollections.observableArrayList();
		effects = new SimpleListProperty<String>(observableList);
		gameTickTime = (int) 2500 / (map.getSizeY());
		
		gameGraphicsViewController = controller;
		gameGraphicsViewController.setNewObservedGameInstance(this);
		
		Thread thread = new Thread(this);
		thread.start();
		
	}/**
	 * Method to add a new SnakeThread
	 * 
	 * @param SnakeInstance
	 */
	public void newSnake(SnakeInstance snakeInstance)
	{
		SnakeThread snakeThread = new SnakeThread(this, snakeInstance);
		gameGraphicsViewController.addKeyListener(snakeThread, this);
	}

	/**
	 * Thread for spawning buffs apples and other game mechanics only 1 for the
	 * moment the apple, maybe more threads for more buffs
	 */
	public void newGameMechanic()
	{
		gameMechanicsThread = new GameMechanicsThread(this, 2);
		
	}
	
	
	
	
	public void pushMapChangesToStack(){
		//TODO reminder
		//pushes the current StackHelpers to the list for network 
	}
	public void addEntityToEntityList(Entity entity){
		gameEntities.add(entity);
	}
	public void removeEntityFromEntityList(Entity entity){
		
	}

	
	
	
	
	public IntegerProperty getHighscoreProperty(){
		return highscore;
	}

	/**
	 * add point to highscore. Multiplier included
	 * 
	 * @param points
	 */
	public void addPointsToHighscore(int points)
	{
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	getHighscoreProperty().set(getHighscore()+points);
	        	if (getHighscore() <0){
	        		getHighscoreProperty().set(0);
	        	}
	        }
	    });
		
	}


	/**
	 * returns the score multiplier.
	 * 
	 * @return
	 */
	public double getScoreMultiplier()
	{
		return scoreMultiplier;
	}

	/**
	 * sets the score multiplier
	 * 
	 * @param scoreMultiplier
	 */
	public void setScoreMultiplier(double scoreMultiplier)
	{
		this.scoreMultiplier = scoreMultiplier;
	}

	/**
	 * returns the highscore
	 * 
	 * @return int highscore
	 */
	public int getHighscore()
	{
		return highscore.get();
	}

	public void addEffect(String effect){
		effects.add(effect);
	}
	public void removeEffect(String effect){
		//TODO might give trouble 
		effects.remove(effect);
	}



	/**
	 * sets the map parameter
	 * 
	 * @param map
	 */
	public void setMap(Map map)
	{
		this.map = map;
	}
	public void removeElementFromMap(MapElements mapElement){
		map.removeMapElemnt(mapElement);
		mapChanges.addDeletedMapElement(mapElement);
	}
	public void addElementToMap(MapElements mapElement){
		map.addMapElement(mapElement);
		mapChanges.addAddedMapElement(mapElement);
	}

	public Boolean getGameIsPaused()
	{
		return gameIsPaused.getValue();
	}

	public void toggleGameIsPaused()
	{
		if (gameIsPaused.get())
		{
			gameIsPaused.set(false);
		} else
		{
			gameIsPaused.set(true);
		}
	}
	public void unPauseGame(){
		gameIsPaused.set(false);
	}
	public int getGameTickTime() {
		return gameTickTime;
	}
	public void setGameTickTime(int gameTickTime) {
		this.gameTickTime = gameTickTime;
	}
	public Map getMap() {
		return map;
	}
	public void gameOver(){
		gameOver.set(true);
	}



}
