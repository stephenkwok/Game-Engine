package gui.view;

/**
 * Button to be displayed in the top pane of the Authoring Environment that opens
 * up the Authoring Environment Help Page when clicked
 * 
 * @author Stephen
 *
 */

public class ButtonHelpGameEditingEnvironment extends ButtonHelpParent {
	public ButtonHelpGameEditingEnvironment(String buttonText, String imageName) {
		super(buttonText, imageName);
	}
	
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers(this.getClass().getSimpleName()));
	}
	
}

