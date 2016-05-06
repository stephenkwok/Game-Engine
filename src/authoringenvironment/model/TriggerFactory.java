package authoringenvironment.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoringenvironment.view.AlertGenerator;
import gameengine.model.Actor;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
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
	@XStreamOmitField
	private ResourceBundle myResources;
	private List<Object> arguments;
	private final AlertGenerator myAlertGenerator;

	public TriggerFactory() {
		this.myResources = ResourceBundle.getBundle(TRIGGER_RESOURCE);
		this.myAlertGenerator = new AlertGenerator();
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
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			myAlertGenerator.generateAlert(e.getClass().toString());
		} 
		return null;
	}

	/**
	 * Creates a Collision Trigger.
	 * 
	 * @param behaviorType:
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
		Constructor<?> constructor = collisionClass.getConstructor(Actor.class, Actor.class, Boolean.class);
		return (ITrigger) constructor.newInstance((Actor) arguments.get(ZERO), (Actor) arguments.get(ONE), (Boolean) arguments.get(TWO));
	}

	/**
	 * Creates a Key Trigger.
	 * 
	 * @param behaviorType:
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
	private ITrigger createKeyTrigger(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> keyClass = Class.forName(className);
		Constructor<?> constructor = keyClass.getConstructor(KeyCode.class);
		return (ITrigger) constructor.newInstance(arguments.get(ZERO));
	}

	/**
	 * Creates a Tick Trigger.
	 * 
	 * @param behaviorType:
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
	private ITrigger createTickTrigger(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(Integer.class);
		return (ITrigger) constructor.newInstance(((Double) arguments.get(ZERO)).intValue());

	}

	/**
	 * Creates a Click Trigger.
	 * 
	 * @param behaviorType:
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
	private ITrigger createClickTrigger(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(IGameElement.class);
		return (ITrigger) constructor.newInstance((IGameElement) arguments.get(ZERO));
	}

	/**
	 * Creates an Attribute Reached behavior.
	 * 
	 * @param behaviorType:
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
	private ITrigger createAttributeReached(String behaviorType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(IGameElement.class,AttributeType.class,Integer.class);
		return (ITrigger) constructor.newInstance((IGameElement) arguments.get(ZERO), arguments.get(ONE), (int) arguments.get(TWO));
	}
}