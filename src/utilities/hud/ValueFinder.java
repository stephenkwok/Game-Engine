package utilities.hud;

public class ValueFinder implements IValueFinder {

	private HUDController controller;
	
	public ValueFinder(HUDController controller) {
		this.controller = controller;
	}
	
	
	
	@Override
	public Property find(String key) {
		Property ret = null;
		switch (key) {
		case "points":
			//
		case "health":
			//
		case "ammo":
			//...you get the point
		
		}
		ret.addObserver(controller);
		return ret;
	}
	
}
