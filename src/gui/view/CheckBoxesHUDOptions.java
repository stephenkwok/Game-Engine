package gui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import gameengine.controller.GameInfo;
import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.AttributeType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Checkboxes for all HUD options.
 * @author amyzhao, stephen
 *
 */
public class CheckBoxesHUDOptions implements IGUIElement, IEditingElement {

	private static final String DELIMITER = ",";
	private static final String HUD_OPTIONS = "HUDOptions";
	private static final String HUD_PROMPT = "Choose items to display on the level scene:";
	private static final String GO = "Go";
	private static final Double CONTAINER_SPACING = 10.0;
	private static final Double CONTAINER_PADDING = 10.0;
	private IEditableGameElement myGameInfo;
	private VBox myContainer;
	private ResourceBundle myAttributesResources;
	private List<CheckBox> myHUDElements;
	private GUIFactory myFactory;
	private Controller myController;

	/**
	 * Constructs a CheckBoxesHUDOptions object for the given GameInfo object.
	 * @param gameInfo: GameInfo object.
	 * @param controller: controller for the authoring environment.
	 */
	public CheckBoxesHUDOptions(IEditableGameElement gameInfo, Controller controller) {
		this.myGameInfo = gameInfo;
		this.myController = controller;
		this.myAttributesResources = ResourceBundle.getBundle("HUDOptions");
		this.myContainer = new VBox(CONTAINER_SPACING);
		myContainer.setPadding(new Insets(CONTAINER_PADDING));
		myFactory = new GUIFactory(myAttributesResources);
		myHUDElements = new ArrayList<>();
	}
	
	/**
	 * Initializes the VBox containing the HUD checkboxes.
	 * @param key: key in resource file for the checkboxes to add.
	 * @param vbox: vbox to add checkboxes into.
	 */
	private void initializeHUD(String key, VBox vbox) {
		vbox.getChildren().add(new Label(HUD_PROMPT));
		List<Node> checkboxes = addElements(HUD_OPTIONS, vbox);
		for (int i = 0; i < checkboxes.size(); i++) {
			CheckBox cb = (CheckBox) checkboxes.get(i);
			cb.prefWidthProperty().bind(myContainer.widthProperty());
			myHUDElements.add(cb);
		}
		vbox.getChildren().addAll(checkboxes);
		Button checkHUDButton = new Button(GO);
		checkHUDButton.prefWidthProperty().bind(myContainer.widthProperty());
		checkHUDButton.setOnAction(e -> ((GameInfo) myGameInfo).setMyHUDOptions(getHUDElementsToDisplay()));
		vbox.getChildren().add(checkHUDButton);
	}

	/**
	 * Add checkbox elements to the vbox.
	 * @param key: key in resource file for elements to add.
	 * @param vbox: container to add checkboxes to.
	 * @return list of elements added.
	 */
	private List<Node> addElements(String key, VBox vbox) {
		String[] elements = myAttributesResources.getString(key).split(DELIMITER);
		List<Node> createdElements = new ArrayList<>();
		for (int i = 0; i < elements.length; i++) {
			createdElements.add((myFactory.createNewGUIObject(elements[i]).createNode()));
		}
		return createdElements;
	}

	/**
	 * Get the HUD elements that the user has selected.
	 * @return list of HUD elements that the user selected.
	 */
	public Map<String, Integer> getHUDElementsToDisplay() {
		Map<String, Integer> toDisplay = new HashMap<String, Integer>();
		for (int i = 0; i < myHUDElements.size(); i++) {
			if (myHUDElements.get(i).isSelected()) {
				String myHUDElementID = myHUDElements.get(i).getId();
				toDisplay.put(myHUDElementID, getInitialValueForHUDElement(myHUDElementID));
			}
		}
		return toDisplay;
	}

	/**
	 * Initializes the value for a given option to 0.
	 * @param myHUDElementID: ID of HUD Element to initialize.
	 * @return 0.
	 */
	// remove this once changes are made on the other end 
	private int getInitialValueForHUDElement(String myHUDElementID) {
		return 0;
	}

	/**
	 * Sets the editable element for the CheckBoxes.
	 */
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myGameInfo = element;
	}

	/**
	 * Returns the node for this object.
	 */
	@Override
	public Node createNode() {
		initializeHUD(HUD_OPTIONS, myContainer);
		return myContainer;
	}

}
