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
				//ret = ((Attribute) mainCharacter.getAttribute(AttributeType.HEALTH)).getMyValue();
			case "health":
			//
			case "ammo":
			//...you get the point
			
			default:
				ret = new Property("Value Not Found", key);
		
		}
		
		ret.addObserver(controller);
		return ret;
	}
	
}
