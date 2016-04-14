package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import gameengine.controller.GameInfo;
import gameengine.model.IAuthoringActor;
import gui.view.CheckBoxesHUDOptions;
import gui.view.IGUIElement;
import gui.view.TextAreaGameDescriptionEditor;
import gui.view.TextAreaParent;
import gui.view.TextFieldGameNameEditor;
import gui.view.TextFieldWithButton;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class enables the author to edit and set various attributes of the game
 * including the game's name, description, and preview image. It will be
 * displayed as the left pane of the Main Screen.
 * 
 * @author Stephen
 *
 */

public class GUIGameEditingEnvironment implements IGUIElement, IEditingElement {

	private IEditableGameElement myGameInfo;
	private static final String RESOURCE_BUNDLE_KEY = "mainScreenGUI";
	private static final double DEFAULT_PADDING = 10;
	private static final double CONTAINER_PREFERRED_WIDTH = 350.0;
	private static final int TEXT_AREA_ROWS = 5;
	private static final double TEXT_FIELD_WIDTH = 100.0;
	private static final double TEXT_FIELD_CONTAINER_SPACING = 10.0;
	private static final double TEXT_FIELD_CONTAINER_PADDING = 10.0;
	private static final double SCROLLBAR_WIDTH = 30.0;
	private final ResourceBundle myResources;
	private VBox editingEnvironmentContainer;
	private Label welcomeMessage;
	private HBox nameEditorContainer;
	private VBox gameDescriptionEditor;
	private VBox previewImageContainer;
	private VBox HUDOptionsDisplay;
	private Controller controller;
	private ScrollPane myScrollPane;
	private List<IAuthoringActor> myActors;

	public GUIGameEditingEnvironment(GameInfo gameInfo, Controller controller, List<IAuthoringActor> actors) {
		this.myGameInfo = gameInfo;
		this.myActors = actors;
		this.myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_KEY);
		this.controller = controller;
	}

	/**
	 * Sets the Game Editing Environment's instance of the GameInfo class
	 */
	@Override
	public void setEditableElement(IEditableGameElement editable) {
		myGameInfo = editable;
	}

	/**
	 * Initialize the VBox containing all GUI elements in the Game Editing Environment
	 * 
	 */
	private void initializeContainer() {
		editingEnvironmentContainer = new VBox();
		editingEnvironmentContainer.setPrefWidth(CONTAINER_PREFERRED_WIDTH);
		editingEnvironmentContainer.setStyle(myResources.getString("defaultBorderColor"));
	}

	/**
	 * Initialize the Label displaying text welcoming the author to the Game Authoring Environment
	 */
	private void initializeWelcomeMessage() {
		welcomeMessage = new LabelMainScreenWelcome(myResources.getString("mainScreenWelcome"));
	}

	/**
	 * Initialize the Game Name Editor, which includes a text field for the author to enter
	 * a name for the game, and a button that, when clicked, allows the author to save the text field 
	 * input as the game's name
	 */
	private void initializeGameNameEditor() {
		String mainPrompt = myResources.getString("gameName");
		String textFieldPrompt = myResources.getString("enterGameName");
		TextFieldWithButton nameEditor = new TextFieldGameNameEditor(mainPrompt, textFieldPrompt, TEXT_FIELD_WIDTH);
		nameEditor.setEditableElement(myGameInfo);
		nameEditorContainer = (HBox) nameEditor.createNode();
		nameEditorContainer.setSpacing(TEXT_FIELD_CONTAINER_SPACING);
		nameEditorContainer.setPadding(new Insets(TEXT_FIELD_CONTAINER_PADDING));
	}

	/**
	 * Initializes the Game Description Editor, which includes a text area for the author to 
	 * enter a description for the game. The Game Description Editor also contains a button
	 * that when clicked, sets the game's description.
	 */
	private void initializeGameDescriptionEditor() {
		String prompt = myResources.getString("promptForGameDescription");
		String buttonText = myResources.getString("save");
		TextAreaParent descriptionEditor = new TextAreaGameDescriptionEditor(prompt, buttonText, TEXT_AREA_ROWS);
		descriptionEditor.setEditableElement(myGameInfo);
		gameDescriptionEditor = (VBox) descriptionEditor.createNode();
	}

	/**
	 * Initializes the GUI Elements displaying the game's current preview image
	 */
	// hard coded values
	private void initializePreviewImageDisplay() {
		previewImageContainer = new VBox();
		// previewImageContainer.setAlignment(Pos.CENTER);
		previewImageContainer.setPadding(new Insets(DEFAULT_PADDING));
		Label previewImageLabel = new Label("Current Game Preview Image:");
		ImageView previewImage = new ImageView(
				new Image(getClass().getClassLoader().getResourceAsStream("default_game.jpg")));
		previewImageContainer.getChildren().addAll(previewImageLabel, previewImage);
	}

	/**
	 * Initializes the GUI element that displays checkboxes for each HUD Option
	 */
	private void initializeHUDOptionsDisplay() {
		CheckBoxesHUDOptions HUDOptions = new CheckBoxesHUDOptions(myGameInfo, controller, myActors);
		HUDOptionsDisplay = (VBox) HUDOptions.createNode();
	}

	/**
	 * Initializes the scroll pane that contains the Game Editing Environment's GUI elements 
	 * and allows for additional elements to be added should the height of the combined 
	 * elements exceed the height of the stage
	 */
	private void initializeScrollPane() {
		myScrollPane = new ScrollPane();
		myScrollPane.prefWidthProperty().bind(editingEnvironmentContainer.prefWidthProperty().add(SCROLLBAR_WIDTH));
		myScrollPane.setContent(editingEnvironmentContainer);
		myScrollPane.setPadding(new Insets(DEFAULT_PADDING));
	}

	/**
	 * Creates and returns the Node containing all of the Game Editing Environment's GUI Elements 
	 */
	@Override
	public Node createNode() {
		initializeContainer();
		initializeWelcomeMessage();
		initializeGameNameEditor();
		initializeGameDescriptionEditor();
		initializePreviewImageDisplay();
		initializeHUDOptionsDisplay();
		initializeScrollPane();
		editingEnvironmentContainer.getChildren().addAll(welcomeMessage, nameEditorContainer, gameDescriptionEditor,
				previewImageContainer, HUDOptionsDisplay);
		return myScrollPane;
	}

}
