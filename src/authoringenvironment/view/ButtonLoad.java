package authoringenvironment.view;

import authoringenvironment.controller.Controller;
import gui.controller.IScreenController;
import gui.view.ButtonParent;
/**
 * Button to load game.
 * @author AnnieTang
 *
 */
public class ButtonLoad extends ButtonParent {
	
	public ButtonLoad(IScreenController myController, String buttonText, String imageName) {
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
    	((Controller) myController).loadGame(promptForFileName(false));
    }
}
