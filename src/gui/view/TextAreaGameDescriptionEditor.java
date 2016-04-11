package gui.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.geometry.Insets;

/**
 * Creates a VBox containing a Label prompting author to edit the game description, a TextArea in which the author
 * can write the game description, and a Button allowing the author to save the text within the TextArea as the 
 * game's description
 * 
 * @author Stephen
 *
 */

public class TextAreaGameDescriptionEditor extends TextAreaParent {
	
	private static final double VBOX_PADDING = 10.0;
	private static final String RESOURCE_BUNDLE_KEY = "mainScreenGui";
	private static final String GAME_EDITOR_PROMPT_KEY = "enterGameDescription";
	private ResourceBundle myResources;
	private Controller controller;
	
	public TextAreaGameDescriptionEditor(String promptText, String buttonText, int prefRows, Controller controller) {
		super(promptText, buttonText, prefRows);
		this.controller = controller;
		myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_KEY);
		setContainerPadding(new Insets(VBOX_PADDING));
		setTextAreaPromptText(myResources.getString(GAME_EDITOR_PROMPT_KEY));
	}

	@Override
	protected void declareButtonAction() {
		setButtonAction(e -> controller.setGameDescription());
	}

}
