package authoringenvironment.view;

import java.util.ResourceBundle;

import gui.view.TextAreaParent;
import gui.view.TextFieldWithButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class functions as a container for all GUI elements in the Main Screen's
 * left pane. It includes a welcome message, a text field where the author can
 * edit the name of the game, and a text area where the author can edit the
 * game's description
 * 
 * @author Stephen
 *
 */

public class VBoxMainScreenLeftPane extends VBox {

	private static final String RESOURCE_BUNDLE_KEY = "mainScreenGUI";
	private static final double VBOX_PADDING = 20;
	private static final double VBOX_PREFERRED_WIDTH = 350.0;
	private static final int TEXT_AREA_ROWS = 5;
	private static final double TEXT_FIELD_WIDTH = 100.0;
	private static final double TEXT_FIELD_CONTAINER_SPACING = 10.0;
	private static final double TEXT_FIELD_CONTAINER_PADDING = 10.0;
	private final ResourceBundle myResources;
	private Label welcomeMessage;
	private HBox nameEditorContainer;
	private TextAreaParent gameDescriptionEditor;
	private EventHandler<ActionEvent> saveNameButtonAction;
	private EventHandler<ActionEvent> saveDescriptionButtonAction;

	public VBoxMainScreenLeftPane(EventHandler<ActionEvent> saveName, EventHandler<ActionEvent> saveDescription) {
		super(VBOX_PADDING);
		this.myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_KEY);
		this.saveNameButtonAction = saveName;
		this.saveDescriptionButtonAction = saveDescription;
		setPrefWidth(VBOX_PREFERRED_WIDTH);
		setStyle(myResources.getString("defaultBorderColor"));
		initWelcomeMessage();
		initGameNameEditor();
		initGameDescriptionEditor();
		this.getChildren().addAll(welcomeMessage, nameEditorContainer, gameDescriptionEditor.getCoupledNodes());
	}

	private void initWelcomeMessage() {
		welcomeMessage = new LabelMainScreenWelcome(myResources.getString("mainScreenWelcome"));
	}

	private void initGameNameEditor() {
		String mainPrompt = myResources.getString("gameName");
		String textFieldPrompt = myResources.getString("enterGameName");
		TextFieldWithButton nameEditor = new TextFieldGameNameEditor(mainPrompt, textFieldPrompt, TEXT_FIELD_WIDTH,
				saveNameButtonAction);
		nameEditorContainer = (HBox) nameEditor.createNode();
		nameEditorContainer.setSpacing(TEXT_FIELD_CONTAINER_SPACING);
		nameEditorContainer.setPadding(new Insets(TEXT_FIELD_CONTAINER_PADDING));
	}

	private void initGameDescriptionEditor() {
		String prompt = myResources.getString("promptForGameDescription");
		String buttonText = myResources.getString("save");
		gameDescriptionEditor = new TextAreaGameDescriptionEditor(prompt, buttonText, TEXT_AREA_ROWS,
				saveDescriptionButtonAction);
	}

}
