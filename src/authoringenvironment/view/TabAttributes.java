package authoringenvironment.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gui.view.CheckBoxObject;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Abstract Tab for setting attributes to go in the Inspector Pane in either the Level or Actor Editing Environment GUI.
 * @author amyzhao, AnnieTang
 *
 */
public class TabAttributes extends TabParent {
	private static final int PADDING = 10;
	private static final String DELIMITER = ",";
	private static final String EDITOR_ELEMENTS = "EditorElements";
	private static final String HUD_OPTIONS = "HUDOptions";
	private static final String HUD_PROMPT = "Choose items to display on the level scene:";
	private ResourceBundle myAttributesResources;
	private GUIFactory myFactory;
	private Controller myController;
	private VBox myContent;
	private List<CheckBox> myHUDElements;
	
	public TabAttributes(Controller controller, ResourceBundle myResources, String tabText, String levelOptionsResource) {
		super(myResources, tabText);
		this.myAttributesResources = ResourceBundle.getBundle(levelOptionsResource);
		myFactory = new GUIFactory(myAttributesResources, myController);
		myHUDElements = new ArrayList<>();
		createElements();
	}

	private void createElements() {
		VBox vbox = new VBox(PADDING);
		vbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		vbox.getChildren().addAll(addElements(EDITOR_ELEMENTS, vbox));
		
		if (myAttributesResources.containsKey(HUD_OPTIONS)) {
			addHUD(HUD_OPTIONS, vbox);
		}
		
		myContent = vbox;
	}
	
	private void addHUD(String key, VBox vbox) {
		vbox.getChildren().add(new Label(HUD_PROMPT));
		List<Node> checkboxes = addElements(HUD_OPTIONS, vbox);
		for (int i = 0; i < checkboxes.size(); i++) {
			CheckBox cb = (CheckBox) checkboxes.get(i);
			myHUDElements.add(cb);
		}
		vbox.getChildren().addAll(checkboxes);
	}
	
	private List<Node> addElements(String key, VBox vbox) {
		String[] elements = myAttributesResources.getString(key).split(DELIMITER);
		List<Node> createdElements = new ArrayList<>();
		for (int i = 0; i < elements.length; i++) {
			IGUIElement elementToCreate = myFactory.createNewGUIObject(elements[i]);
			createdElements.add(elementToCreate.createNode());
		}
		return createdElements;
	}
	
	public List<String> getHUDElementsToDisplay() {
		List<String> toDisplay = new ArrayList<>();
		for (int i = 0; i < myHUDElements.size(); i++) {
			if (myHUDElements.get(i).isSelected()) {
				toDisplay.add(myHUDElements.get(i).getId());
			}
		}
		return toDisplay;
	}
	
	@Override
	Node getContent() {
		return myContent;
	}
}