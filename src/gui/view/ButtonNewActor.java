package gui.view;

import authoringenvironment.controller.Controller;

/**
 * Button for adding a new actor.
 * 
 * @author amyzhao
 *
 */
public class ButtonNewActor extends ButtonParent {

	/**
	 * Constructs a button for adding a new actor.
	 * 
	 * @param myController:
	 *            environment controller.
	 * @param buttonText:
	 *            text for the button.
	 * @param imageName:
	 *            image for the button.
	 */
	public ButtonNewActor(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	/**
	 * On click, add a new actor via the controller.
	 */
	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> notifyObservers("ButtonNewActor"));
	}

}
