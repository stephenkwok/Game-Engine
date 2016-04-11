package gui.view;

import authoringenvironment.controller.Controller;

/**
 * This class extends the TextFieldWithButton class and allows the author to set the game's name
 * 
 * @author Stephen
 *
 */

public class TextFieldGameNameEditor extends TextFieldWithButton{
	
	private Controller controller;
	
	public TextFieldGameNameEditor(String labelText, String prompt, Double textFieldWidth, Controller controller) {
		super(labelText, prompt, textFieldWidth);
		this.controller = controller;
	}
	

	
}
