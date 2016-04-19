package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ResourceBundle;

import gameengine.model.Actor;
import gameengine.model.ITrigger;

public class TriggerFactory {
	private static final String DELIMITER = ",";
	private static final String TRIGGER_TYPES= "TriggerTypes";
	private static final String GAME_ENGINE = "gameengine.";
	private static final String MODEL = "model.";
	private static final String TRIGGERS = "Triggers.";
	private static final String CLASS = "Class";
	private static final String CREATE = "create";
	private ResourceBundle myResources;
	private Object argument;
	
	public TriggerFactory(ResourceBundle myResources) {
		this.myResources = myResources;
	}
	/**
	 * Creates new ITrigger based on nodeTypeKey passed in. 
	 * @param nodeTypeKey: Name of object you want to create.
	 * @return ITrigger: element corresponding to nodeTypeKey in ResourceBundle.
	 */
	public ITrigger createNewTrigger(String triggerTypeKey, Object argument) {
		this.argument = argument;
		String triggerType = myResources.getString(triggerTypeKey);
		String elementType = determineElementType(triggerType);
		return createElement(elementType, triggerType);
	}

	/**
	 * Determines the element type based on the list of possible Trigger Types given by the resource file.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @return trigger type (e.g. CollisionTrigger, KeyTrigger, etc.)
	 */
	private String determineElementType(String triggerType) {
		String[] keys = myResources.getString(TRIGGER_TYPES).split(DELIMITER);
		for (int i = 0; i < keys.length; i++) {
			if (Arrays.asList(myResources.getString(keys[i]).split(DELIMITER)).contains(triggerType)) {
				return keys[i];
			}
		}
		return null;
	}
	
	/**
	 * Create the desired element.
	 * @param elementType: ComboBox, TextFieldWithButton, Button, Menu, Pane, MenuBar, or CheckBoxObject.
	 * @param nodeType: name of the elementType as specified in the properties file.
	 * @return IGUIElement for the desired element.
	 */
	private ITrigger createElement(String elementType, String triggerType) {
		String className = GAME_ENGINE + MODEL + TRIGGERS + myResources.getString(triggerType + CLASS);
		try {
			Method createMethod = this.getClass().getDeclaredMethod(CREATE + elementType, String.class, String.class);
			return (ITrigger) createMethod.invoke(this, triggerType, className);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Creates a Collision Trigger.
	 * @param nodeType: Type corresponding to the nodeTypeKey originally specified.
	 * @param className: specific subclass of CollisionTrigger.
	 * @return ITrigger.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private ITrigger createCollisionTrigger(String nodeType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		////
		Actor myActor = (Actor) this.argument;
		Actor otherActor = new Actor();
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(Actor.class, Actor.class);
		return (ITrigger) constructor.newInstance(myActor, otherActor);
	}
}
