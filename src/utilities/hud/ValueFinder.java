package utilities.hud;

import gameengine.controller.Game;

public class ValueFinder implements IValueFinder {

	private HUDController controller;
	private Game data; //for other projects, your data will be different
	
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



	@Override
	public void setController(HUDController controller) {
		this.controller = controller;
	}

	@Override
	public void setDataSource(Object dataSource) {
		this.data = (Game) data;
	}
	
}
