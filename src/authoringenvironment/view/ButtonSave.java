package authoringenvironment.view;
import authoringenvironment.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * Button to save game
 * @author AnnieTang
 *
 */
public class ButtonSave extends ButtonParent {

	public ButtonSave(Controller myController, String buttonText, String imageName, EventHandler<ActionEvent> handler) {
		super(myController, buttonText, imageName, handler);
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> myController.saveGame(promptForFileName(true)));
	}	
}
