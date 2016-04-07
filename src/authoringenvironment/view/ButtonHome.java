package authoringenvironment.view;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;
import gui.view.ButtonParent;
/**
 * Button to return to main screen
 * @author AnnieTang
 *
 */
public class ButtonHome extends ButtonParent{
	public ButtonHome(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(event -> {
			((Controller) myController).goToMainScreen();
		});
	}
}