package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;

/**
 * Instantiates IGUIElements based on a ResourceBundle String key passed into createNewGUIObject(String nodeTypeKey).
 *
 * @author AnnieTang
 */

public class GUIFactory {
	private static final String TEXT = "Text";
	private static final String ICON = "Icon";
	private static final String CLASS = "Class";
	private static final String PROMPT = "Prompt";
	private static final String AUTHORING_ENV = "authoringenvironment.";
	private static final String VIEW = "view.";
	private ResourceBundle myResources;
	private Controller myController;

	public GUIFactory(ResourceBundle myResources, Controller myController){
		this.myResources = myResources;
		this.myController = myController;
	}

	/**
	 * Creates new IGUIElement based on nodeTypeKey passed in. 
	 * @param nodeTypeKey
	 * @return IGUIElement
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public IGUIElement createNewGUIObject(String nodeTypeKey) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String nodeType = myResources.getString(nodeTypeKey);
		if (isButton(nodeType)) {
			return createButton(nodeType);
		} else if (isComboBox(nodeType)) {
			return createComboBox(nodeType);
		}
		return null;
	}

	private boolean isButton(String nodeType) {
		return Arrays.asList(myResources.getString("Buttons").split(",")).contains(nodeType);
	}

	private boolean isComboBox(String nodeType) {
		return Arrays.asList(myResources.getString("ComboBoxes").split(",")).contains(nodeType);
	}

	private IGUIElement createButton(String nodeType) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String text = myResources.getString(nodeType + TEXT);
		String icon = myResources.getString(nodeType + ICON);
		String className = AUTHORING_ENV + VIEW + myResources.getString(nodeType + CLASS);
		try {
			Class<?> button = Class.forName(className);
			Constructor<?> constructor = button.getConstructor(Controller.class, String.class, String.class);
			return (IGUIElement) constructor.newInstance(myController, text, icon);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	private IGUIElement createComboBox(String nodeType) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String prompt = myResources.getString(nodeType + PROMPT);
		String className = AUTHORING_ENV + VIEW + myResources.getString(nodeType + CLASS);
		try {
			Class<?> comboBox = Class.forName(className);
			Constructor<?> constructor = comboBox.getConstructor(ResourceBundle.class, String.class, Controller.class);
			return (IGUIElement) constructor.newInstance(myResources, prompt, myController);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
}