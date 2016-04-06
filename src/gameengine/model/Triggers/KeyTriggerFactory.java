package gameengine.model.Triggers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.input.KeyCode;

public class KeyTriggerFactory {

	public KeyTriggerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public KeyTrigger createTrigger(KeyCode key){
		String className = "gameengine.model.Triggers"+key.getName()+"KeyTrigger";
		try {
			Class cls = Class.forName(className);
			Constructor constructor = cls.getDeclaredConstructor();
			return (KeyTrigger)constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return null;
		}
	}

}
