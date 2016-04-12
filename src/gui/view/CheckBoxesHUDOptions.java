package gui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import gameengine.controller.GameInfo;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CheckBoxesHUDOptions implements IGUIElement, IGUIEditingElement {
	
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
	
	
	public CheckBoxesHUDOptions(IEditableGameElement gameInfo, Controller controller) {
		this.myGameInfo = gameInfo;
		this.myController = controller;
		this.myAttributesResources = ResourceBundle.getBundle("HUDOptions");
		this.myContainer = new VBox(CONTAINER_SPACING);
		myContainer.setPadding(new Insets(CONTAINER_PADDING));
		myFactory = new GUIFactory(myAttributesResources, myController);
		myHUDElements = new ArrayList<>();
	}
	
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
		checkHUDButton.setOnAction(e -> updateGameInfo());
		vbox.getChildren().add(checkHUDButton);
	}
	
	private void updateGameInfo() {
		((GameInfo) myGameInfo).setMyHUDOptions(getHUDElementsToDisplay());
		// create map
	}
	
	private List<Node> addElements(String key, VBox vbox) {
		String[] elements = myAttributesResources.getString(key).split(DELIMITER);
		List<Node> createdElements = new ArrayList<>();
		for (int i = 0; i < elements.length; i++) {
			createdElements.add((myFactory.createNewGUIObject(elements[i]).createNode()));
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
	public void setEditableElement(IEditableGameElement element) {
		myGameInfo = element;	
	}

	@Override
	public Node createNode() {
		initializeHUD(HUD_OPTIONS, myContainer);
		return myContainer;
	}

}
