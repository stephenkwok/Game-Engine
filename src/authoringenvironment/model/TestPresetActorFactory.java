package authoringenvironment.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.model.Actor;

/**
 * This classes tests the Preset Actor Factory
 * 
 * @author Stephen
 *
 */

public class TestPresetActorFactory {

	private static final String RESOURCE_BUNDLE_KEY = "presetActorsFactoryFlappyBird";
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {		
		PresetActorFactory PAF = new PresetActorFactory(ResourceBundle.getBundle(RESOURCE_BUNDLE_KEY));
		List<Actor> actors = PAF.getPresetActors();
		System.out.println(actors);
		for (Actor actor : actors) {
		}
	}

}
