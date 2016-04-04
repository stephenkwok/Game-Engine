package authoringenvironment.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Tab for setting level attributes to go in the Inspector Pane in the Level Editing Environment GUI.
 * @author amyzhao
 *
 */
public class TabLevelAttributes extends TabParent {
	private static final String LEVEL_OPTIONS_RESOURCE = "levelEditorOptions";
	private static final String LEVEL_EDITOR_ELEMENTS = "LevelEditorElements";
	private static final String PROMPT = "Select";
	private static final String DELIMITER = ",";
	private static final String LABEL = "Label";
	private static final String OPTIONS = "Options";
	private static final String TEXTVIEW = "Textviews";
	private static final String COMBOBOXES = "Comboboxes";
	private static final String AUTHORING_ENV = "authoringenvironment.";
	private static final String VIEW = "view.";
	private static final String CLASS = "Class";
	private ResourceBundle myResources;
	
	public TabLevelAttributes(ResourceBundle myResources, String tabText) {
		super(myResources, tabText);
		this.myResources = ResourceBundle.getBundle(LEVEL_OPTIONS_RESOURCE);
	}

	@Override
	void setContent() {
		VBox vbox = new VBox();
		
		String[] elements = myResources.getString(LEVEL_EDITOR_ELEMENTS).split(DELIMITER);
		for (int i = 0; i < elements.length; i++) {
			HBox hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			Label label = new Label(myResources.getString(elements[i] + LABEL));
			IGUIElement elementToCreate = null;
			try {
				elementToCreate = createNewGUIObject(elements[i]);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hbox.getChildren().addAll(label, elementToCreate.createNode());
			vbox.getChildren().add(hbox);
		}
		tab.setContent(vbox);
	}

	private IGUIElement createNewGUIObject(String nodeType) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if (isTextView(nodeType)) {
		//	return createTextView(nodeType);
		} else if (isComboBox(nodeType)) {
			return createComboBox(nodeType);
		}
		return null;
	}
	
	private boolean isTextView(String nodeType) {
		return Arrays.asList(myResources.getString(TEXTVIEW).split(",")).contains(nodeType);
	}

	private boolean isComboBox(String nodeType) {
		return Arrays.asList(myResources.getString(COMBOBOXES).split(",")).contains(nodeType);
	}
/*
	private IGUIElement createTextView(String nodeType) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
	}*/

	private IGUIElement createComboBox(String nodeType) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String className = AUTHORING_ENV + VIEW + myResources.getString(nodeType + CLASS);
		String options = myResources.getString(nodeType + OPTIONS);
		List<String> optionsList = Arrays.asList(options.split(DELIMITER));
		try {
			Class<?> comboBox = Class.forName(className);
			Constructor<?> constructor = comboBox.getConstructor(ResourceBundle.class, String.class, List.class);
			return (IGUIElement) constructor.newInstance(myResources, PROMPT, optionsList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
}
