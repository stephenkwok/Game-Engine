package gui.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;

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
	private static final String GUI_ELEMENT_TYPES = "GUIElementTypes";
	private static final String DELIMITER = ",";
	protected ResourceBundle myResources;
	protected IScreenController myController;

	public GUIFactory(ResourceBundle myResources, IScreenController myController2){
		this.myResources = myResources;
		this.myController = myController2;
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

	private IGUIElement createComboBox(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
			String prompt = myResources.getString(nodeType + PROMPT);
			Class<?> comboBox = Class.forName(className);
			Constructor<?> constructor = comboBox.getConstructor(ResourceBundle.class, String.class, Controller.class);
			return (IGUIElement) constructor.newInstance(myResources, prompt, myController);
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

	private IGUIElement createCheckBox(String nodeType) {
		return null;
	}


}