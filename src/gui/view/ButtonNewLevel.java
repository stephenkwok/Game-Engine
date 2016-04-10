package gui.view;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;

/**
 * Button to create a new level and transition to level editing environment.
 * @author amyzhao
 *
 */
public class ButtonNewLevel extends ButtonParent {

	public ButtonNewLevel(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}
	
	/**
	 * On click, creates a new level, adds it to the list of levels, and transitions to the level editing environment.
	 */
	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> ((Controller) myController).addLevel());
	}

}
