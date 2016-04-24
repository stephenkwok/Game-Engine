package authoringenvironment.view;

import java.util.*;
import authoringenvironment.model.*;
import gameengine.controller.GameInfo;
import gui.view.*;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * This class enables the author to edit and set various attributes of the game
 * including the game's name, description, and preview image. It will be
 * displayed as the left pane of the Main Screen.
 * 
 * @author Stephen
 *
 */

public class GameEditingEnvironment implements IEditingElement {

	private static final String RESOURCE_BUNDLE_KEY = "mainScreenGUI";
	private static final double DEFAULT_PADDING = 10;
	private static final double CONTAINER_PREFERRED_WIDTH = 350.0;
	private static final int TEXT_AREA_ROWS = 3;
	private static final double TEXT_FIELD_WIDTH = 100.0;
	private static final double TEXT_FIELD_CONTAINER_SPACING = 10.0;
	private static final double SCROLLBAR_WIDTH = 30.0;
	private final Stage myStage;
	private final ResourceBundle myResources;
	private IEditableGameElement myGameInfo;
	private Label welcomeMessage;
	private HBox nameEditorContainer, gameTypeButtonContainer, previewImageContainer;
	private VBox editingEnvironmentContainer, gameDescriptionEditor, HUDOptionsDisplay;
	private ScrollPane myScrollPane;

	public GameEditingEnvironment(GameInfo gameInfo, Stage stage) {
		this.myGameInfo = gameInfo;
		this.myStage = stage;
		this.myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_KEY);
		initializeEditingEnvironment();
	}

	/**
	 * Initializes the Game Editing Environment
	 */
	private void initializeEditingEnvironment() {
		initializeContainer();
		initializeWelcomeMessage();
		initializeGameNameEditor();
		initializeGameDescriptionEditor();
		initializeGameTypeButton();
		initializePreviewImageDisplay();
		initializeHUDOptionsDisplay();
		initializeScrollPane();
		editingEnvironmentContainer.getChildren().addAll(welcomeMessage, nameEditorContainer, gameDescriptionEditor,
				gameTypeButtonContainer, previewImageContainer, HUDOptionsDisplay);
	}

	/**
	 * Initialize the VBox containing all GUI elements in the Game Editing
	 * Environment
	 * 
	 */
	private void initializeContainer() {
		editingEnvironmentContainer = new VBox();
		editingEnvironmentContainer.setPrefWidth(CONTAINER_PREFERRED_WIDTH);
		editingEnvironmentContainer.setStyle(myResources.getString("defaultBorderColor"));
	}

	/**
	 * Initialize the Label displaying text welcoming the author to the Game
	 * Authoring Environment
	 */
	private void initializeWelcomeMessage() {
		welcomeMessage = new LabelMainScreenWelcome(myResources.getString("mainScreenWelcome"));
	}

	/**
	 * Initialize the Game Name Editor, which includes a text field for the
	 * author to enter a name for the game, and a button that, when clicked,
	 * allows the author to save the text field input as the game's name
	 */
	private void initializeGameNameEditor() {
		String mainPrompt = myResources.getString("gameName");
		String textFieldPrompt = myResources.getString("enterGameName");
		TextFieldWithButton nameEditor = new TextFieldGameNameEditor(mainPrompt, textFieldPrompt, TEXT_FIELD_WIDTH);
		nameEditor.setEditableElement(myGameInfo);
		nameEditorContainer = (HBox) nameEditor.createNode();
		nameEditorContainer.prefWidthProperty().bind(editingEnvironmentContainer.widthProperty());
		nameEditorContainer.setSpacing(TEXT_FIELD_CONTAINER_SPACING);
		nameEditorContainer.setPadding(new Insets(DEFAULT_PADDING));
	}

	/**
	 * Initializes the Game Description Editor, which includes a text area for
	 * the author to enter a description for the game. The Game Description
	 * Editor also contains a button that when clicked, sets the game's
	 * description.
	 */
	private void initializeGameDescriptionEditor() {
		String prompt = myResources.getString("promptForGameDescription");
		String buttonText = myResources.getString("save");
		TextAreaParent descriptionEditor = new TextAreaGameDescriptionEditor(prompt, buttonText, TEXT_AREA_ROWS);
		descriptionEditor.setEditableElement(myGameInfo);
		gameDescriptionEditor = (VBox) descriptionEditor.createNode();
		gameDescriptionEditor.prefWidthProperty().bind(editingEnvironmentContainer.widthProperty());
	}

	private void initializeGameTypeButton() {
		ButtonGameType buttonGameType = new ButtonGameType(getGameInfo());
		Button button = (Button) buttonGameType.createNode();
		gameTypeButtonContainer = new HBox(button);
		gameTypeButtonContainer.prefWidthProperty().bind(editingEnvironmentContainer.widthProperty());
		button.prefWidthProperty().bind(gameTypeButtonContainer.widthProperty());
		gameTypeButtonContainer.setPadding(new Insets(DEFAULT_PADDING));
	}

	/**
	 * Initializes the GUI Elements displaying the game's current preview image
	 */
	private void initializePreviewImageDisplay() {
		previewImageContainer = new HBox();
		String gameImageName = ((GameInfo) myGameInfo).getMyImageName();
		ButtonFileChooserGameImage buttonGameImage = new ButtonFileChooserGameImage(gameImageName, this, myStage);
		Button button = (Button) buttonGameImage.createNode();
		button.prefWidthProperty().bind(previewImageContainer.widthProperty());
		previewImageContainer.getChildren().add(button);
		previewImageContainer.setPadding(new Insets(DEFAULT_PADDING));
		previewImageContainer.prefWidthProperty().bind(editingEnvironmentContainer.widthProperty());
	}

	/**
	 * Initializes the GUI element that displays checkboxes for each HUD Option
	 */
	private void initializeHUDOptionsDisplay() {
		CheckBoxesHUDOptions HUDOptions = new CheckBoxesHUDOptions(myGameInfo);
		HUDOptionsDisplay = (VBox) HUDOptions.createNode();
	}

	/**
	 * Initializes the scroll pane that contains the Game Editing Environment's
	 * GUI elements and allows for additional elements to be added should the
	 * height of the combined elements exceed the height of the stage
	 */
	private void initializeScrollPane() {
		myScrollPane = new ScrollPane();
		myScrollPane.prefWidthProperty().bind(editingEnvironmentContainer.prefWidthProperty().add(SCROLLBAR_WIDTH));
		myScrollPane.setContent(editingEnvironmentContainer);
		myScrollPane.setPadding(new Insets(DEFAULT_PADDING));
	}

	/**
	 * 
	 * @return the Node containing all Nodes in the Game Editing Environment
	 */
	public Node getNode() {
		return myScrollPane;
	}
	
	/**
	 * 
	 * @return the Game Editing Environment's instance of GameInfo
	 */
	public GameInfo getGameInfo() {
		return (GameInfo) myGameInfo;
	}

	/**
	 * Sets the Game Editing Environment's editable to a new editable
	 */
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myGameInfo = element;
	}
	
}
