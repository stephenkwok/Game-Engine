package gui.view;

import java.util.*;

import authoringenvironment.model.*;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Checkboxes for all HUD options.
 * 
 * @author amyzhao, stephen
 *
 */
public class CheckBoxesGarbageCollection extends Observable implements IGUIElement, IEditingElement {

	private static final String DELIMITER = ",";
	private static final String RESOURCE = "garbageCollectionOptions";
	private static final String OPTIONS = "Options";
	private static final String START_PROMPT = "StartPrompt";
	private static final String END_PROMPT = "EndPrompt";
	private static final String GO = "Go";
	private static final Double CONTAINER_SPACING = 10.0;
	private static final Double CONTAINER_PADDING = 10.0;
	private static final String LEFT = "Left";
	private static final String RIGHT = "Right";
	private static final String TOP = "Top";
	private static final String BOTTOM = "Bottom";
	private Level myLevel;
	private VBox myContainer;
	private ResourceBundle myAttributesResources;
	private List<CheckBox> mySides;
	private GUIFactory myFactory;
	private Map<String, IAuthoringActor> garbageCollectors;

	/**
	 * Constructs a CheckBoxesHUDOptions object for the given GameInfo object.
	 * 
	 * @param gameInfo:
	 *            GameInfo object.
	 * @param controller:
	 *            controller for the authoring environment.
	 */
	public CheckBoxesGarbageCollection() {
		this.myAttributesResources = ResourceBundle.getBundle(RESOURCE);
		this.myContainer = new VBox(CONTAINER_SPACING);
		myContainer.setPadding(new Insets(CONTAINER_PADDING));
		myFactory = new GUIFactory(myAttributesResources);
		mySides = new ArrayList<>();
		garbageCollectors = new HashMap<>();
		init();
	}

	/**
	 * Initializes the VBox containing the HUD checkboxes.
	 * 
	 * @param key:
	 *            key in resource file for the checkboxes to add.
	 * @param vbox:
	 *            vbox to add checkboxes into.
	 */
	private void init() {
		myContainer.getChildren().add(new Label(myAttributesResources.getString(START_PROMPT)));
		List<Node> checkboxes = addElements(OPTIONS, myContainer);
		int i = 0;
		while (i < checkboxes.size()) {
			HBox container = new HBox();
			for (int j = 0; j < 2; j++) {
				CheckBox cb = (CheckBox) checkboxes.get(i);
				cb.prefWidthProperty().bind(myContainer.widthProperty());
				mySides.add(cb);
				container.getChildren().add(cb);
				i++;
			}
			myContainer.getChildren().add(container);
		}
		Button checkHUDButton = new Button(GO);
		checkHUDButton.prefWidthProperty().bind(myContainer.widthProperty());
		checkHUDButton.setOnAction(e -> updateGarbageCollectingActors(getSides()));
		myContainer.getChildren().add(new Label(myAttributesResources.getString(END_PROMPT)));
		myContainer.getChildren().add(checkHUDButton);
	}

	private void updateGarbageCollectingActors(List<String> sides) {
		for (int i = 0; i < sides.size(); i++) {
			IAuthoringActor garbageCollector;
			if (garbageCollectors.containsKey(sides.get(i))) {
				garbageCollector = garbageCollectors.get(sides.get(i));
				myLevel.removeActor((Actor) garbageCollector);
				
				garbageCollector = new Actor();

				garbageCollectors.put(sides.get(i), garbageCollector);
			}
		}
	}

	/**
	 * Add checkbox elements to the vbox.
	 * 
	 * @param key:
	 *            key in resource file for elements to add.
	 * @param vbox:
	 *            container to add checkboxes to.
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
	 * 
	 * @return list of HUD elements that the user selected.
	 */
	public List<String> getSides() {
		List<String> sides = new ArrayList<String>();
		mySides.stream().filter(checkbox -> checkbox.isSelected())
		.forEach(checkbox -> sides.add(checkbox.getId()));
		return sides;
	}

	/**
	 * Sets the editable element for the CheckBoxes.
	 */
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myLevel = (Level) element;
	}

	/**
	 * Returns the node for this object.
	 */
	@Override
	public Node createNode() {
		return myContainer;
	}

	@Override
	public void addNodeObserver(Observer observer) {
		this.addObserver(observer);
	}

}
