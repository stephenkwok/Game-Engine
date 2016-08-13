package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingElement;
import gameengine.controller.GameInfo;
import gui.view.ButtonGameType;
import gui.view.TextAreaGameDescriptionEditor;
import gui.view.TextAreaParent;
import gui.view.TextFieldNameEditor;
import gui.view.TextFieldWithButton;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This class enables the author to edit and set various attributes of the game
 * including the game's name, description, and preview image. It will be
 * displayed as the left pane of the Main Screen.
 * 
 * @author Stephen
 *
 */

public class GameAttributesDisplay implements IEditingElement {

	private static final String RESOURCE_BUNDLE_KEY = "mainScreenGUI";
	private static final double DEFAULT_PADDING = 10;
	private static final double CONTAINER_PREFERRED_WIDTH = 350.0;
	private static final int TEXT_AREA_ROWS = 3;
	private static final double TEXT_FIELD_WIDTH = 100.0;
	private static final double TEXT_FIELD_CONTAINER_SPACING = 10.0;
	private static final double SCROLLBAR_WIDTH = 30.0;
	private final ResourceBundle myResources;
	private IEditableGameElement myGameInfo;
	private HBox nameEditorContainer, gameTypeButtonContainer, welcomeMessage;
	private VBox editingEnvironmentContainer, gameDescriptionEditor;
	private ScrollPane myScrollPane;

	/**
	 * Creates the GUI element allowing the author to change various attributes of the Game
	 * @param gameInfo: the GameInfo object edited by the GameAttributesDisplay
	 */
	public GameAttributesDisplay(GameInfo gameInfo) {
		this.myGameInfo = gameInfo;
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
		initializeScrollPane();
		initializeEditingEnvironmentContainer();
	}
	
	/**
	 * Initializes the Game Editing Environment's parent container by adding all created
	 * nodes to it and binding each child node's width to the parent container's width
	 */
	private void initializeEditingEnvironmentContainer() {
		editingEnvironmentContainer.getChildren().addAll(welcomeMessage, nameEditorContainer, gameDescriptionEditor,
				gameTypeButtonContainer);
		editingEnvironmentContainer.getChildren().stream().forEach(node -> bindChildWidthToParentWidth(node));
	}
	
	/**
	 * Binds an HBox or VBox's width to the Game Editing Environment's parent container
	 * @param child
	 */
	private void bindChildWidthToParentWidth(Node child) {
		((Region) child).prefWidthProperty().bind(editingEnvironmentContainer.widthProperty());
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
		welcomeMessage = new HBox(new LabelMainScreenWelcome(myResources.getString("mainScreenWelcome")));
	}

	/**
	 * Initialize the Game Name Editor, which includes a text field for the
	 * author to enter a name for the game, and a button that, when clicked,
	 * allows the author to save the text field input as the game's name
	 */
	private void initializeGameNameEditor() {
		String mainPrompt = myResources.getString("gameName");
		String textFieldPrompt = myResources.getString("enterGameName");
		TextFieldWithButton nameEditor = new TextFieldNameEditor(mainPrompt, textFieldPrompt, TEXT_FIELD_WIDTH);
		nameEditor.setEditableElement(myGameInfo);
		nameEditor.setTextFieldHGrow();
		nameEditorContainer = (HBox) nameEditor.createNode();
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
	}

	private void initializeGameTypeButton() {
		ButtonGameType buttonGameType = new ButtonGameType(getGameInfo());
		Button button = (Button) buttonGameType.createNode();
		gameTypeButtonContainer = new HBox(button);
		button.prefWidthProperty().bind(gameTypeButtonContainer.widthProperty());
		gameTypeButtonContainer.setPadding(new Insets(DEFAULT_PADDING));
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
