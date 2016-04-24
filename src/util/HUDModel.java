package util;


import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class HUDModel extends Observable {
	
	private Map<String, Property> data;
	
	public HUDModel(Map<String, Property> data) {
		this.data = data;
	}
	
	public HUDModel() {
		this.data = new HashMap<>();
	}
	
	
	public Map<String, Property> getData() {
		return data;
	}
	
	public void handleChange(ValueChange change) {
		data.get(change.getFieldName()).setValue(change.getNewValue());
	}
	
}
