package authoringenvironment.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import gameengine.model.Actor;

public class TestPresetActorFactory {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PresetActorFactory PAF = new PresetActorFactory();
		List<Actor> actors = PAF.getPresetActors();
		System.out.println(actors);
		for (Actor actor : actors) {
			System.out.println(actor.getID());
			System.out.println(actor.isMainPlayer());
		}
	}

}
