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
	EventHandler<ActionEvent> handler;
	
	public ButtonLoad(Controller myController, String buttonText, String imageName, EventHandler<ActionEvent> handler) {
		super(myController, buttonText, imageName, handler);
		this.handler = handler;
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
