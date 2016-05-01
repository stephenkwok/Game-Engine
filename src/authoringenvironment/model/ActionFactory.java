package authoringenvironment.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gameengine.model.Actor;
import gameengine.model.AttributeType;
import gameengine.model.IAction;
import gameengine.model.IGameElement;

/**
 * Factory to create IAction objects
 * 
 * @author AnnieTang
 *
 */
public class ActionFactory {
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final String ACTION_RESOURCE = "actionfactory";
	private static final String DELIMITER = ",";
	private static final String ACTION_TYPES = "ActionTypes";
	private static final String GAME_ENGINE = "gameengine.";
	private static final String MODEL = "model.";
	private static final String ACTIONS = "Actions.";
	private static final String CLASS = "Class";
	private static final String CREATE = "create";
	@XStreamOmitField
	private ResourceBundle myResources;
	private List<Object> arguments;

	public ActionFactory() {
		this.myResources = ResourceBundle.getBundle(ACTION_RESOURCE);
	}

	/**
	 * Creates new IAction based on behavior type passed in.
	 * 
	 * @return IAction
	 */
	public IAction createNewAction(String behaviorType, List<Object> arguments) {
		this.arguments = arguments;
		String actionType = determineActionType(behaviorType);
		return createAction(actionType, behaviorType);
	}

	/**
	 * Determines the element type based on the list of possible ACTIONTypes
	 * given by the resource file.
	 * 
	 * @return Action type
	 */
	private String determineActionType(String behaviorType) {
		String[] keys = myResources.getString(ACTION_TYPES).split(DELIMITER);
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
	private IAction createAction(String actionType, String behaviorType) {
		String className = GAME_ENGINE + MODEL + ACTIONS + myResources.getString(behaviorType + CLASS);
		try {
			Method createMethod = this.getClass().getDeclaredMethod(CREATE + actionType, String.class, String.class);
			return (IAction) createMethod.invoke(this, behaviorType, className);
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

	private IAction createSelfActionActionIGameElement(String actionType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(IGameElement.class);
		return (IAction) constructor.newInstance((IGameElement) arguments.get(ZERO));
	}
	
	private IAction createSelfActionActionActor(String actionType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(Actor.class);
		return (IAction) constructor.newInstance((Actor) arguments.get(ZERO));
	}

	private IAction createChangeAttributeBehavior(String actionType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(IGameElement.class, AttributeType.class, Integer.class);
		return (IAction) constructor.newInstance((IGameElement) arguments.get(ZERO), arguments.get(ONE), arguments.get(TWO));
	}
	
	private IAction createOffsetBehavior(String actionType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(Actor.class, Double.class);
		return (IAction) constructor.newInstance((Actor) arguments.get(ZERO), arguments.get(ONE));
	}
	
	private IAction createSpawnBehavior(String actionName, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(Actor.class, Actor.class);
		return (IAction) constructor.newInstance((Actor) arguments.get(ZERO), (Actor) arguments.get(ONE));
	}
	
	private IAction createCreateActorBehavior(String actionType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		try{
			Constructor<?> constructor = clazz.getConstructor(IGameElement.class, Actor.class, Double.class, Double.class, Double.class, Double.class);
			return (IAction) constructor.newInstance((IGameElement) arguments.get(ZERO), (Actor) arguments.get(ONE), arguments.get(TWO), arguments.get(THREE), arguments.get(FOUR), arguments.get(FIVE));
		}catch(Exception e){
			System.out.println("single");
			Constructor<?> constructor = clazz.getConstructor(IGameElement.class, Actor.class, Double.class, Double.class);
			return (IAction) constructor.newInstance((IGameElement) arguments.get(ZERO), (Actor) arguments.get(ONE), arguments.get(TWO), arguments.get(THREE));
		}
	}
	
	private IAction createGlideTargetBehavior(String actionType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(Actor.class, Double.class, Actor.class);
		return (IAction) constructor.newInstance((Actor) arguments.get(ZERO), arguments.get(ONE), (Actor) arguments.get(TWO));
	}
	
	private IAction createSoundBehavior(String actionType, String className)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getConstructor(IGameElement.class, String.class);
		return (IAction) constructor.newInstance((IGameElement) arguments.get(ZERO), arguments.get(ONE));
	}

}
