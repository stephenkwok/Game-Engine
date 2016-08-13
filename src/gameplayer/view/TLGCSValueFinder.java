//package gameplayer.view;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.List;
//
//import gameengine.controller.Game;
//import gameengine.model.Actor;
//import gameengine.model.AttributeType;
//import gameengine.model.IPlayActor;
//import voogasalad.util.hud.source.HUDController;
//import voogasalad.util.hud.source.IValueFinder;
//import voogasalad.util.hud.source.Property;
//
//public class TLGCSValueFinder implements IValueFinder {
//	private Game data;
//	
//	@Override
//	public Collection<Property<?>> find(String key) {
//		Collection<Property<?>> properties = new ArrayList<>();
//		Property<?> ret = null;
//		boolean multiSuccess = false;
//		try {
//			switch (key.toLowerCase()) {
//				case "level time":
//					ret = data.getLevelTimeProperty();
//					break;
//				case "global time": case "time":
//					ret = data.getGlobalTimeProperty();
//					break;
//				default:
//					Collection<Property<?>> multipleProperties = findMany(key);
//					if (multipleProperties.size() != 0) {
//						properties.addAll(multipleProperties);
//						multiSuccess = true;
//					}
//					break;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		if (ret != null) {
//			properties.add(ret);
//		} else if (!multiSuccess){
//			properties.add(new Property<String>("Value not found", key));
//		}
//		
//		
//		
//		
//		
//		return properties;
//	}
//	
//	
//	private Collection<Property<?>> findMany(String key) {
//		List<Property<?>> properties = new ArrayList<>();
//		for (IPlayActor mainCharacter : data.getCurrentLevel().getMainCharacters()) {
//			try {
//				Property<?> ret = null;
//				switch (key.toLowerCase()) {
//					case "points":
//						ret = mainCharacter.getAttribute(AttributeType.POINTS).getProperty();
//						break;
//					case "health":
//						ret = mainCharacter.getAttribute(AttributeType.HEALTH).getProperty();
//						break;
//					default:
//						break;
//				}
//				if (ret != null && !properties.contains(ret)) {
//					properties.add(ret);
//				}
//			} catch (Exception e) {
//				// don't worry about it
//			}
//		}
//		
//		Collections.sort(properties, (Property<?> a, Property<?> b) 
//									-> a.getFieldName().compareTo(b.getFieldName()));
//		
//		
//		return properties;
//	}
//	
//	
//	
//	
//	
//	
//	@Override
//	public void setDataSource(Object dataSource) throws IllegalArgumentException {
//		if (dataSource instanceof Game) {
//			this.data = (Game) dataSource;
//		} else {
//			throw new IllegalArgumentException();
//		}
//	}
//
//
//
//}