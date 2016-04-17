package gui.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.ResourceBundle;
import gui.controller.IScreenController;

/**
 * Instantiates IGUIElements based on a ResourceBundle String key passed into createNewGUIObject(String nodeTypeKey).
 *
 * @author AnnieTang, amyzhao
 */

public class GUIFactory {
	private static final String TEXT = "Text";
	private static final String ICON = "Icon";
	private static final String CLASS = "Class";
	private static final String PROMPT = "Prompt";
	private static final String GUI = "gui.";
	private static final String VIEW = "view.";
	private static final String CREATE = "create";
	private static final String LABEL = "Label";
	private static final String WIDTH = "Width";
	private static final String GUI_ELEMENT_TYPES = "GUIElementTypes";
	private static final String DELIMITER = ",";
	private ResourceBundle myResources;
	private IScreenController myController;

	/**
	 * Constructs a GUIFactory with elements specified in a given ResourceBundle.
	 * @param myResources: ResourceBundle containing the elements to be made.
	 * @param myController: environment's controller.
	 */
	public GUIFactory(ResourceBundle myResources, IScreenController myController){
		this.myResources = myResources;
		this.myController = myController;
	}
	
	public GUIFactory(ResourceBundle resourceBundle) {
		this.myResources = resourceBundle;
	}

	/**
	 * Creates new IGUIElement based on nodeTypeKey passed in. 
	 * @param nodeTypeKey: Name of object you want to create.
	 * @return IGUIElement: element corresponding to nodeTypeKey in ResourceBundle.
	 */
	public IGUIElement createNewGUIObject(String nodeTypeKey) {
		String nodeType = myResources.getString(nodeTypeKey);
		String elementType = determineElementType(nodeType);
		return createElement(elementType, nodeType);
	}

	/**
	 * Determines the element type based on the list of possible GUI Element Types given by the resource file.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @return element type (e.g. ComboBox, TextField, CheckBoxObject, etc.)
	 */
	private String determineElementType(String nodeType) {
		String[] keys = myResources.getString(GUI_ELEMENT_TYPES).split(DELIMITER);
		for (int i = 0; i < keys.length; i++) {
			if (Arrays.asList(myResources.getString(keys[i]).split(DELIMITER)).contains(nodeType)) {
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

	/**
	 * Creates a ComboBox.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @param className: specific subclass of ComboBoxParent.
	 * @return IGUIElement for the ComboBox.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IGUIElement createComboBox(String nodeType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			String prompt = myResources.getString(nodeType + PROMPT);
			String label = myResources.getString(nodeType + LABEL);
			Class<?> comboBox = Class.forName(className);
			Constructor<?> constructor = comboBox.getConstructor(ResourceBundle.class, String.class, String.class);
			return (IGUIElement) constructor.newInstance(myResources, prompt, label);
	}

	/**
	 * Creates a Button.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @param className: specific subclass of ButtonParent.
	 * @return IGUIElement for the Button.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IGUIElement createButton(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
			String text = myResources.getString(nodeType + TEXT);
			String icon = myResources.getString(nodeType + ICON);
			Class<?> button = Class.forName(className);
			Constructor<?> constructor = button.getConstructor(IScreenController.class, String.class, String.class);
			return (IGUIElement) constructor.newInstance(myController, text, icon);
	}
	
	/**
	 * Creates a Pane.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @param className: specific subclass of PaneParent.
	 * @return IGUIElement for the Pane.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IGUIElement createPane(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
			Class<?> pane = Class.forName(className);
			Constructor<?> constructor = pane.getConstructor(IScreenController.class);
			return (IGUIElement) constructor.newInstance(myController);
	}

	/**
	 * Creates a Menu.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @param className: specific subclass of MenuParent.
	 * @return IGUIElement for the Menu.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IGUIElement createMenu(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
			String text = myResources.getString(nodeType + TEXT);
			Class<?> menu = Class.forName(className);
			Constructor<?> constructor = menu.getConstructor(IScreenController.class, String.class);
			return (IGUIElement) constructor.newInstance(myController, text);
	}

	/**
	 * Creates a MenuBar.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @param className: specific subclass of MenuBarParent.
	 * @return IGUIElement for the MenuBar.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IGUIElement createMenuBar(String nodeType, String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
			Class<?> menu = Class.forName(className);
			Constructor<?> constructor = menu.getConstructor(IScreenController.class);
			return (IGUIElement) constructor.newInstance(myController);
	}

	/**
	 * Creates a TextFieldWithButton.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @param className: specific subclass of TextFieldWithButton.
	 * @return IGUIElement for the TextFieldWithButton.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IGUIElement createTextFieldWithButton(String nodeType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> textfield = Class.forName(className);
		String labelText = myResources.getString(nodeType + LABEL);
		String promptText = myResources.getString(nodeType + PROMPT);
		Double width = Double.valueOf(myResources.getString(nodeType + WIDTH));
		Constructor<?> constructor = textfield.getConstructor(String.class, String.class, Double.class);
		return (IGUIElement) constructor.newInstance(labelText, promptText, width);

	}
	
	/**
	 * Creates a CheckBoxObject.
	 * @param nodeType: nodeType corresponding to the nodeTypeKey originally specified.
	 * @param className: CheckBoxObject.
	 * @return IGUIElement for the CheckBoxObject.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private IGUIElement createCheckBoxObject(String nodeType, String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> checkbox = Class.forName(className);
		String promptText = myResources.getString(nodeType + PROMPT);
		int width = Integer.parseInt(myResources.getString(nodeType + WIDTH));
		Constructor<?> constructor = checkbox.getConstructor(String.class, int.class);
		return (IGUIElement) constructor.newInstance(promptText, width);
	}

}