//package gui.view;
//
//import java.util.*;
//
//import authoringenvironment.model.*;
//import gameengine.controller.GameInfo;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//
///**
// * Checkboxes for all HUD options.
// * 
// * @author amyzhao, stephen
// *
// */
//public class CheckBoxesHUDOptions extends Observable implements IGUIElement, IEditingElement {
//
//	private static final String DELIMITER = ",";
//	private static final String HUD_OPTIONS = "HUDOptions";
//	private static final String HUD_PROMPT = "Choose items to display on the level scene:";
//	private static final String GO = "Go";
//	private static final Double CONTAINER_SPACING = 10.0;
//	private static final Double CONTAINER_PADDING = 10.0;
//	private IEditableGameElement myGameInfo;
//	private VBox myContainer;
//	private ResourceBundle myAttributesResources;
//	private List<CheckBox> myHUDElements;
//	private GUIFactory myFactory;
//
//	/**
//	 * Constructs a CheckBoxesHUDOptions object for the given GameInfo object.
//	 * 
//	 * @param gameInfo:
//	 *            GameInfo object.
//	 * @param controller:
//	 *            controller for the authoring environment.
//	 */
//	public CheckBoxesHUDOptions(IEditableGameElement gameInfo) {
//		this.myGameInfo = gameInfo;
//		this.myAttributesResources = ResourceBundle.getBundle("HUDOptions");
//		this.myContainer = new VBox(CONTAINER_SPACING);
//		myContainer.setPadding(new Insets(CONTAINER_PADDING));
//		myFactory = new GUIFactory(myAttributesResources);
//		myHUDElements = new ArrayList<>();
//	}
//
//	/**
//	 * Initializes the VBox containing the HUD checkboxes.
//	 * 
//	 * @param key:
//	 *            key in resource file for the checkboxes to add.
//	 * @param vbox:
//	 *            vbox to add checkboxes into.
//	 */
//	private void initializeHUD(String key, VBox vbox) {
//		vbox.getChildren().add(new Label(HUD_PROMPT));
//		List<Node> checkboxes = addElements(HUD_OPTIONS, vbox);
//		for (int i = 0; i < checkboxes.size(); i++) {
//			CheckBox cb = (CheckBox) checkboxes.get(i);
//			cb.prefWidthProperty().bind(myContainer.widthProperty());
//			myHUDElements.add(cb);
//		}
//		vbox.getChildren().addAll(checkboxes);
//		Button checkHUDButton = new Button(GO);
//		checkHUDButton.prefWidthProperty().bind(myContainer.widthProperty());
//		checkHUDButton.setOnAction(e -> ((GameInfo) myGameInfo).setMyHUDOptions(getHUDElementsToDisplay()));
//		vbox.getChildren().add(checkHUDButton);
//	}
//
//	/**
//	 * Add checkbox elements to the vbox.
//	 * 
//	 * @param key:
//	 *            key in resource file for elements to add.
//	 * @param vbox:
//	 *            container to add checkboxes to.
//	 * @return list of elements added.
//	 */
//	private List<Node> addElements(String key, VBox vbox) {
//		String[] elements = myAttributesResources.getString(key).split(DELIMITER);
//		List<Node> createdElements = new ArrayList<>();
//		for (int i = 0; i < elements.length; i++) {
//			createdElements.add((myFactory.createNewGUIObject(elements[i]).createNode()));
//		}
//		return createdElements;
//	}
//
//	/**
//	 * Get the HUD elements that the user has selected.
//	 * 
//	 * @return list of HUD elements that the user selected.
//	 */
//	public List<String> getHUDElementsToDisplay() {
//		List<String> toDisplay = new ArrayList<String>();
//		myHUDElements.stream().filter(checkbox -> checkbox.isSelected())
//				.forEach(checkbox -> toDisplay.add(checkbox.getId()));
//		return toDisplay;
//	}
//
//	/**
//	 * Sets the editable element for the CheckBoxes.
//	 */
//	@Override
//	public void setEditableElement(IEditableGameElement element) {
//		myGameInfo = element;
//	}
//
//	/**
//	 * Returns the node for this object.
//	 */
//	@Override
//	public Node createNode() {
//		initializeHUD(HUD_OPTIONS, myContainer);
//		return myContainer;
//	}
//
//	@Override
//	public void addNodeObserver(Observer observer) {
//		this.addObserver(observer);
//	}
//
//}
