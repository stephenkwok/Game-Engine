package authoringenvironment.view;
import authoringenvironment.controller.Controller;

public class ButtonSave extends ButtonParent {

	public ButtonSave(Controller myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	@Override
	void setButtonAction() {
		button.setOnAction(e -> myController.saveGame(promptForFileName(true)));
	}	
}
