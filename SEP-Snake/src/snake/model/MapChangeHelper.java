package snake.model;

import java.util.ArrayList;
import java.util.List;

import snake.model.mapelement.MapElements;

/**
 * TODO All If someone got a better name refactor it!
 * @author Balthasar Schüss
 *
 */
public class MapChangeHelper {

	private List<MapElements> createdElements;
	private List<MapElements> deletedElements;
	
	
	public MapChangeHelper(){
		createdElements = new ArrayList<MapElements>();
		deletedElements = new ArrayList<MapElements>();
		
	}
	
	public void addDeletedMapElement(MapElements deleted){
		deletedElements.add(deleted);
	}
	public void addAddedMapElement(MapElements added){
		createdElements.add(added);
	}
	public List<MapElements> getCreatedElements() {
		return createdElements;
	}

	public List<MapElements> getDeletedElements() {
		return deletedElements;
	}

	
	
	
	
}
