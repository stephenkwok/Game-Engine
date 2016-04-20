package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.model.Actor;
import gameengine.model.IAction;

public class ActionFactory {	
	private static final String ACTION_RESOURCE = "action";
	private static final String DELIMITER = ",";
	private static final String ACTION_TYPES= "ActionTypes";
	private static final String GAME_ENGINE = "gameengine.";
	private static final String MODEL = "model.";
	private static final String ACTIONS = "Actions.";
	private static final String CLASS = "Class";
	private static final String CREATE = "create";
	private ResourceBundle myResources;
	private List<Object> arguments;
	
	public ActionFactory() {
		this.myResources = ResourceBundle.getBundle(ACTION_RESOURCE);
	}
	
	/**
	 * Creates new IAction based on actionType passed in. 
	 * @param actionType: Type of action you want to create
	 * @return IAction: element corresponding to actionType in ResourceBundle.
	 */
	public IAction createNewAction(String actionType, List<Object> arguments) {
		this.arguments = arguments;
		String elementType = determineActionType(actionType);
		return createAction(elementType, actionType);
	}

	/**
	 * Determines the element type based on the list of possible Trigger Types given by the resource file.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @return trigger type (e.g. CollisionTrigger, KeyTrigger, etc.)
	 */
	private String determineActionType(String actionType) {
		String[] keys = myResources.getString(ACTION_TYPES).split(DELIMITER);
		for (int i = 0; i < keys.length; i++) {
			if (Arrays.asList(myResources.getString(keys[i]).split(DELIMITER)).contains(actionType)) {
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
	private IAction createAction(String elementType, String actionType) {
		String className = GAME_ENGINE + MODEL + ACTIONS + myResources.getString(actionType + CLASS);
		try {
			Method createMethod = this.getClass().getDeclaredMethod(CREATE + elementType, String.class, String.class);
			return (IAction) createMethod.invoke(this, actionType, className);
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
	private IAction createDoubleBehavior(String nodeType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Double value = Double.parseDouble((String) arguments.get(0));
		Class<?> collisionClass = Class.forName(className);
		Constructor<?> constructor = collisionClass.getConstructor(Double.class);
		return (IAction) constructor.newInstance(value);
	}
	
	private IAction createLabel(){
		//TODO:
		return null;
	}
	
	private IAction createComboBoxBehavior(){
		//TODO:
		return null;
	}
	
}
