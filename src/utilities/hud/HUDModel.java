package utilities.hud;


import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class HUDModel extends Observable implements IHUDModel {
	
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
