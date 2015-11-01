package snake.model.mapelement;

import snake.utility.MapPosition;

public abstract class MapElements implements CollisionHandling{

	private MapElementType mapElementType;
	
	

	private MapPosition mapPosition;

	/**
	 * returns mapPosition of the MapElement
	 * 
	 * @return
	 */
	public MapPosition getMapPosition()
	{
		return mapPosition;
	}

	// TODO hjashj
	/**
	 * sets Map Position
	 * 
	 * @param xPosition
	 * @param yPosition
	 */
	public void setMapPosition(int xPosition, int yPosition)
	{
		mapPosition = new MapPosition(xPosition, yPosition);
	}

	/**
	 * sets mapPositon of the MapElement
	 * 
	 * @param mapPosition
	 */
	public void setMapPosition(MapPosition mapPosition)
	{
		this.mapPosition = mapPosition;
	}
	public MapElementType getMapElementType() {
		return mapElementType;
	}
	protected void setMapElementType(MapElementType mapElementType){
		this.mapElementType = mapElementType;
	}
}
