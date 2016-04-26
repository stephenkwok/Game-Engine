package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.model.Actor;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;
import gameengine.model.Triggers.ITrigger;
import javafx.scene.input.KeyCode;

/**
 * Factory to create ITrigger objects
 * 
 * @author AnnieTang
 *
 */
public class TriggerFactory {
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final String TRIGGER_RESOURCE = "triggerfactory";
	private static final String DELIMITER = ",";
	private static final String TRIGGER_TYPES = "TriggerTypes";
	private static final String GAME_ENGINE = "gameengine.";
	private static final String MODEL = "model.";
	private static final String TRIGGERS = "Triggers.";
	private static final String CLASS = "Class";
	private static final String CREATE = "create";
	private ResourceBundle myResources;
	private List<Object> arguments;

	public TriggerFactory() {
		this.myResources = ResourceBundle.getBundle(TRIGGER_RESOURCE);
	}

	/**
	 * Creates new ITrigger based on nodeTypeKey passed in.
	 * 
	 * @return ITrigger: element corresponding to nodeTypeKey in ResourceBundle.
	 */
	public ITrigger createNewTrigger(String behaviorType, List<Object> arguments) {
		this.arguments = arguments;
		String triggerType = determineTriggerType(behaviorType);
		return createTrigger(triggerType, behaviorType);
	}

	/**
	 * Determines the element type based on the list of possible Trigger Types
	 * given by the resource file.
	 * 
	 * @return trigger type (e.g. CollisionTrigger, KeyTrigger, etc.)
	 */
	private String determineTriggerType(String behaviorType) {
		String[] keys = myResources.getString(TRIGGER_TYPES).split(DELIMITER);
		for (int i = 0; i < keys.length; i++) {
			if (Arrays.asList(myResources.getString(keys[i]).split(DELIMITER)).contains(behaviorType)) {
				return keys[i];
			}
		}
		return null;
	}

	/**
	 * Create the desired element.
	 * 
	 * @return IGUIElement for the desired element.
	 */
	private ITrigger createTrigger(String triggerType, String behaviorType) {
		String className = GAME_ENGINE + MODEL + TRIGGERS + myResources.getString(behaviorType + CLASS);
		try {
			Method createMethod = this.getClass().getDeclaredMethod(CREATE + triggerType, String.class, String.class);
			return (ITrigger) createMethod.invoke(this, behaviorType, className);
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
	 * 
	 * @param nodeType:
	 *            Type corresponding to the nodeTypeKey originally specified.
	 * @param className:
	 *            specific subclass of CollisionTrigger.
	 * @return ITrigger.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private ITrigger createCollisionTrigger(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(IPlayActor.class, IPlayActor.class);
		return (ITrigger) constructor.newInstance((IPlayActor) arguments.get(ZERO), (IPlayActor) arguments.get(ONE));
	}

	private ITrigger createKeyTrigger(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> keyClass = Class.forName(className);
		Constructor<?> constructor = keyClass.getConstructor(KeyCode.class);
		return (ITrigger) constructor.newInstance(arguments.get(ZERO));
	}

	private ITrigger createTickTrigger(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(int.class);
		return (ITrigger) constructor.newInstance(arguments.get(ZERO));

	}

	private ITrigger createClickTrigger(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(IGameElement.class);
		return (ITrigger) constructor.newInstance((IGameElement) arguments.get(ZERO));
	}

	private ITrigger createAttributeReached(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(AttributeType.class, int.class, IGameElement.class);
		return (ITrigger) constructor.newInstance(arguments.get(ZERO), arguments.get(ONE), arguments.get(TWO));
	}
}
