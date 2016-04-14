package gui.view;

import gameengine.controller.GameInfo;
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
	
	public TextAreaGameDescriptionEditor(String promptText, String buttonText, int prefRows) {
		super(promptText, buttonText, prefRows);
		setContainerPadding(new Insets(VBOX_PADDING));
		setButtonAction(e -> ((GameInfo) getEditableElement()).setMyDescription(getTextAreaInput()));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		setTextAreaPromptText(((GameInfo) getEditableElement()).getMyDescription());		
	}

}
