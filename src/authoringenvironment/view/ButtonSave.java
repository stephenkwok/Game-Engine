package authoringenvironment.view;
import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;
import gui.view.ButtonParent;
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
		button.setOnAction(e -> ((Controller) myController).saveGame(promptForFileName(true)));
	}	
}
