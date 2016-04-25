package utilities.hud;

import gameengine.controller.Game;
import gameengine.model.AttributeType;

public class TLGCSValueFinder implements IValueFinder {

	private HUDController controller;
	private Game data; //for other projects, your data will be a different class
	
	@Override
	public Property find(String key) {
		Property ret = null;
		switch (key.toLowerCase()) {
			case "points":
				ret = data.getCurrentLevel().getMainCharacter().getAttribute(AttributeType.POINTS).getProperty();
				break;
			case "health":
				//reflection here to save LOC?
				break;
			case "ammo":
				//
				break;
			default:
				ret = new Property("Value Not Found", key);
				break;
		
		}
		ret.addObserver(controller);
		return ret;
	}



	@Override
	public void setController(HUDController controller) {
		this.controller = controller;
	}

	@Override
	public void setDataSource(Object dataSource) throws IllegalArgumentException {
		if (dataSource instanceof Game) {
			this.data = (Game) data;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
}
