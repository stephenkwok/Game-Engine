package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private ResourceBundle myResources;
	
	public TextAreaGameDescriptionEditor(String promptText, String buttonText, int prefRows, EventHandler<ActionEvent> handler) {
		super(promptText, buttonText, prefRows, handler);
		myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_KEY);
		setContainerPadding(new Insets(VBOX_PADDING));
		setTextAreaPromptText(myResources.getString("enterGameDescription"));
	}

}
