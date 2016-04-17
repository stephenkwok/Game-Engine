package gui.view;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;

/**
 * Button to create a new level and transition to level editing environment.
 * @author amyzhao
 *
 */
public class ButtonNewLevel extends ButtonParent {

	/**
	 * Constructs a button for adding a new level.
	 * @param myController: environment controller.
	 * @param buttonText: text for the button.
	 * @param imageName: image for the button.
	 */
	public ButtonNewLevel(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}
	
	/**
	 * On click, creates a new level, adds it to the list of levels, and transitions to the level editing environment.
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyController(null));
	}

}
