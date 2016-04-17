package gui.view;

import gui.controller.IScreenController;
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
		getButton().setOnAction(event -> loadProperties());
	}
	
	/**
     * Sets workspace preferences to those specified by the given XML. 
     */
    private void loadProperties() {
    	notifyController(promptForFileName(false));
    }
}
