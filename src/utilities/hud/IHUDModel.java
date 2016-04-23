package utilities.hud;

import java.util.Map;

public interface IHUDModel {
	public void handleChange(ValueChange change);
	public Map<String, Property> getData();
}
