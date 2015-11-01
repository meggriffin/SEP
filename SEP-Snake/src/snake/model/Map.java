package snake.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import snake.model.mapelement.MapElements;
import snake.utility.MapPosition;

public class Map {

	private int SizeX;
	private int SizeY;
	private ListProperty<MapElements> mapElements;

	public Map()
	{

	}

	/**
	 * basic testconstructor for a predefined map bug in
	 * 
	 * @param testmap
	 */
	public Map(String testmap)
	{
		ObservableList<MapElements> observableList = FXCollections.observableArrayList();
		mapElements = new SimpleListProperty<MapElements>(observableList);

		SizeX = 30;
		SizeY = 30;

		// mapElements.add(new Apple(new MapPosition(2, 4)));
		// mapElements.add(new Apple(new MapPosition(8, 29)));
		// mapElements.add(new Apple(new MapPosition(22, 23)));
		// mapElements.add(new Apple(new MapPosition(7, 10)));
		// mapElements.add(new Apple(new MapPosition(11, 6)));
		// mapElements.add(new DeBuffSpeedElement(new MapPosition(20, 20)));
		// mapElements.add(new TargetPorter(new MapPosition(7, 3), new
		// MapPosition(10, 7)));
		// mapElements.add(new WallElement(new MapPosition(0, 0)));
		// mapElements.add(new SuperApple(new MapPosition(14, 14)));

	}

	/**
	 * returns the MapElement with the give values of the mapPosition if there's
	 * no such element, returns null
	 * 
	 * @param mapPosition
	 * @return MapElements
	 */
	public synchronized MapElements findNode(MapPosition mapPosition)
	{
		MapElements returnValue = null;
		for (MapElements value : mapElements)
		{
			if (value.getMapPosition().compare(mapPosition))
			{
				returnValue = value;
			}
		}
		return returnValue;

	}
	public void addMapElement(MapElements mapElement){
		mapElements.add(mapElement);
	}
	public void removeMapElemnt(MapElements mapElement){
		mapElements.remove(mapElement);
	}

	/**
	 * returns Width value of the map
	 * 
	 * @return
	 */
	public int getSizeX()
	{
		return SizeX;
	}

	/**
	 * sets Width value of the map
	 * 
	 * @param sizeX
	 */
	public void setSizeX(int sizeX)
	{
		SizeX = sizeX;
	}

	/**
	 * returns height value of the map
	 * 
	 * @return
	 */
	public int getSizeY()
	{
		return SizeY;
	}

	/**
	 * sets height value of the map
	 * 
	 * @param sizeY
	 */
	public void setSizeY(int sizeY)
	{
		SizeY = sizeY;
	}

	/**
	 * returns ListProperty MapElements
	 * 
	 * @return
	 */
	public ListProperty<MapElements> getMapElements()
	{
		return mapElements;
	}

}
