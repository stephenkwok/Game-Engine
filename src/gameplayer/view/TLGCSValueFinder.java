package gameplayer.view;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import gameengine.controller.Game;
import gameengine.model.AttributeType;
import voogasalad.util.hud.source.*;

public class TLGCSValueFinder implements IValueFinder {
	private Game data; //for other projects, your data will be a different class
	
	@Override
	public Collection<Property<?>> find(String key) {
		Collection<Property<?>> properties = new HashSet<>();
		Property<?> ret = null;
		try {
			
			switch (key.toLowerCase()) {
				case "points":
					//ret = data.getCurrentLevel().getMainCharacter().getAttribute(AttributeType.POINTS).getProperty();
					break;
				case "health":
					//ret = data.getCurrentLevel().getMainCharacter().getAttribute(AttributeType.HEALTH).getProperty();
					break;
				case "level time":
					ret = data.getLevelTimeProperty();
					break;
				case "global time": case "time":
					ret = data.getGlobalTimeProperty();
					break;
				default:
					properties.addAll(findMany(key));
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ret != null) {
			System.out.println(ret.hashCode() + " : " + ret.getFieldName());
			properties.add(ret);
		} else {
			properties.add(new Property<String>("Vaule not found", key));
		}
		
		
		return properties;
		//return ret == null ? new Property<String>("Value Not Found", key) : ret;
	}
	
	
	//will uncomment when stuff works
	private Collection<Property<?>> findMany(String key) {
		Collection<Property<?>> properties = new HashSet<>();
		/*
		for (Character c : data.getCurrentLevel().getMainCharacters()) {
			Property<?> ret = null;
			switch (key.toLowerCase()) {
				case "points":
					ret = data.getCurrentLevel().getMainCharacter().getAttribute(AttributeType.POINTS).getProperty();
					break;
				case "health":
					ret = data.getCurrentLevel().getMainCharacter().getAttribute(AttributeType.HEALTH).getProperty();
					break;
				default:
					break;
			}
			if (ret != null) {
				properties.add(ret);
			}
		}
		*/
		properties.add(new Property<String>("Value Not Found", key));
		return properties;
	}
	
	
	
	
	
	
	@Override
	public void setDataSource(Object dataSource) throws IllegalArgumentException {
		if (dataSource instanceof Game) {
			this.data = (Game) dataSource;
		} else {
			throw new IllegalArgumentException();
		}
	}


	public void setController(HUDController controller) {
		// TODO Auto-generated method stub
		
	}


}