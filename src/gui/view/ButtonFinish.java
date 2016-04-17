package gui.view;

import gui.controller.IScreenController;
/**
 * On click, saves the game and returns user to the game player splash screen.
 * @author AnnieTang
 *
 */
public class ButtonFinish extends ButtonParent {

	public ButtonFinish(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	/**
	 * Returns to the splash screen once the user is finished editing.
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyController(null));
	}

}
