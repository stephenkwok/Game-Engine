package gui.view;

/**
 * Button to be displayed in the top pane of the Authoring Environment that opens
 * up the Authoring Environment Help Page when clicked
 * 
 * @author Stephen
 *
 */

public class ButtonHelpPage extends ButtonParent {
	
	public ButtonHelpPage(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	/**
	 * Opens up the Authoring Environment Help Page
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers("ButtonHelpPage"));
	}

}
