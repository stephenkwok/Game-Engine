package gui.view;
import gui.controller.IScreenController;
/**
 * Button to save game
 * @author AnnieTang
 *
 */
public class ButtonSave extends ButtonParent {

	public ButtonSave(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> notifyObservers(promptForFileName(true)));
	}	
}
