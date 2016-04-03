package authoringenvironment.view;

import authoringenvironment.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * Button to load game.
 * @author AnnieTang
 *
 */
public class ButtonLoad extends ButtonParent {
	
	public ButtonLoad(Controller myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(event -> loadProperties());
	}
	
	/**
     * Sets workspace preferences to those specified by the given XML. 
     */
    private void loadProperties() {
    	myController.loadGame(promptForFileName(false));
    }
}
