package utilities.hud;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class HUDModel extends Observable {
	Map<String, Property> data;
	Map<String, Property> originalData;
	
	public HUDModel(Map<String, Property> data, Map<String, Property> originalData) {
		this.data = data;
		this.originalData = originalData;
	}
	
	public HUDModel(Map<String, Property> data) {
		this.data = data;
		originalData = new HashMap<String, Property>(data);
	}
	
	public void reset() {
		data = new HashMap<String, Property>(originalData);
		setChanged();
		notifyObservers(new ValueChange(null, data, "RESET"));
	}
	
	public Map<String, Property> getData() {
		return data;
	}
	
}
