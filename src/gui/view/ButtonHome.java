package gui.view;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;
/**
 * Button to return to main screen
 * @author AnnieTang
 *
 */
public class ButtonHome extends ButtonParent{
	public ButtonHome(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	/**
	 * Returns to the main screen of the authoring environment.
	 */
	@Override
	protected void setButtonAction() {
		button.setOnAction(event -> {
			((Controller) myController).goToMainScreen();
		});
	}
}