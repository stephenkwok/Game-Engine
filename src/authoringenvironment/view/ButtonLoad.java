package authoringenvironment.view;

import authoringenvironment.controller.Controller;

public class ButtonLoad extends ButtonParent {

	public ButtonLoad(Controller myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
	}

	@Override
	void setButtonAction() {
		button.setOnAction(e -> loadProperties());
	}
	
	/**
     * Sets workspace preferences to those specified by the given XML. 
     */
    private void loadProperties() {
    	myController.loadGame(promptForFileName(false));
    }
}
