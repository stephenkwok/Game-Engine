package gui.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;
import javafx.event.EventHandler;

/**
 * Instantiates IGUIElements based on a ResourceBundle String key passed into createNewGUIObject(String nodeTypeKey).
 *
 * @author AnnieTang, amyzhao
 */

public class GUIFactory {
	protected static final String TEXT = "Text";
	protected static final String ICON = "Icon";
	protected static final String CLASS = "Class";
	protected static final String PROMPT = "Prompt";
	protected static final String GUI = "gui.";
	protected static final String VIEW = "view.";
	private static final String CREATE = "create";
	private static final String LABEL = "Label";
	private static final String WIDTH = "Width";
	private static final String GUI_ELEMENT_TYPES = "GUIElementTypes";
	private static final String DELIMITER = ",";
	private static final String SPACING = "Spacing";
	protected ResourceBundle myResources;
	protected IScreenController myController;

	public GUIFactory(ResourceBundle myResources, IScreenController myController){
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
	public IGUIElement createNewGUIObject(String nodeTypeKey) {
		String nodeType = myResources.getString(nodeTypeKey);
		String elementType = determineElementType(nodeType);
		return createElement(elementType, nodeType);
	}

	private String determineElementType(String nodeType) {
		String[] keys = myResources.getString(GUI_ELEMENT_TYPES).split(DELIMITER);
		for (int i = 0; i < keys.length; i++) {
			if (Arrays.asList(myResources.getString(keys[i]).split(DELIMITER)).contains(nodeType)) {
				return keys[i];
			}
		}
		return null;
	}
	
	private IGUIElement createElement(String elementType, String nodeType) {
		String className = GUI + VIEW + myResources.getString(nodeType + CLASS);
		try {
			Method createMethod = this.getClass().getDeclaredMethod(CREATE + elementType, String.class, String.class);
			return (IGUIElement) createMethod.invoke(this, nodeType, className);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private IGUIElement createComboBox(String nodeType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			String prompt = myResources.getString(nodeType + PROMPT);
			String label = myResources.getString(nodeType + LABEL);
			Class<?> comboBox = Class.forName(className);
			Constructor<?> constructor = comboBox.getConstructor(ResourceBundle.class, String.class, String.class);
			return (IGUIElement) constructor.newInstance(myResources, prompt, label);
	}

	private IGUIElement createButton(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
			String text = myResources.getString(nodeType + TEXT);
			String icon = myResources.getString(nodeType + ICON);
			Class<?> button = Class.forName(className);
			Constructor<?> constructor = button.getConstructor(IScreenController.class, String.class, String.class);
			return (IGUIElement) constructor.newInstance(myController, text, icon);
	}
	
	private IGUIElement createPane(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
			Class<?> pane = Class.forName(className);
			Constructor<?> constructor = pane.getConstructor(IScreenController.class);
			return (IGUIElement) constructor.newInstance(myController);
	}

	private IGUIElement createMenu(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
			String text = myResources.getString(nodeType + TEXT);
			Class<?> menu = Class.forName(className);
			Constructor<?> constructor = menu.getConstructor(IScreenController.class, String.class);
			return (IGUIElement) constructor.newInstance(myController, text);
	}

	private IGUIElement createMenuBar(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
			Class<?> menu = Class.forName(className);
			Constructor<?> constructor = menu.getConstructor(IScreenController.class);
			return (IGUIElement) constructor.newInstance(myController);
	}

	// TODO: set eventhandler
	
	private IGUIElement createTextFieldWithButton(String nodeType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> textfield = Class.forName(className);
		String labelText = myResources.getString(nodeType + LABEL);
		String promptText = myResources.getString(nodeType + PROMPT);
		Double width = Double.valueOf(myResources.getString(nodeType + WIDTH));
		Constructor<?> constructor = textfield.getConstructor(String.class, String.class, Double.class, EventHandler.class);
		return (IGUIElement) constructor.newInstance(labelText, promptText, width, null);

	}
	
	private IGUIElement createCheckBoxObject(String nodeType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> checkbox = Class.forName(className);
		String promptText = myResources.getString(nodeType + PROMPT);
		int spacing = Integer.parseInt(myResources.getString(nodeType + SPACING));
		int width = Integer.parseInt(myResources.getString(nodeType + WIDTH));
		Constructor<?> constructor = checkbox.getConstructor(String.class, int.class, int.class);
		return (IGUIElement) constructor.newInstance(promptText, spacing, width);
	}

}