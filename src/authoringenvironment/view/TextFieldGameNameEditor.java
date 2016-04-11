package authoringenvironment.view;

import gui.view.TextFieldWithButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This class extends the TextFieldWithButton class and allows the author to set the game's name
 * 
 * @author Stephen
 *
 */

public class TextFieldGameNameEditor extends TextFieldWithButton{
	
	public TextFieldGameNameEditor(String labelText, String prompt, Double textFieldWidth, EventHandler<ActionEvent> setGameName) {
		super(labelText, prompt, textFieldWidth);
		setButtonAction(setGameName);
	}

	
}
