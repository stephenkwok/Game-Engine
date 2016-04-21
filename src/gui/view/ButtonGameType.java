package gui.view;

import gameengine.controller.GameInfo;

/**
 * 
 * @author Stephen
 *
 */

public class ButtonGameType extends ButtonParent {

	private GameInfo myGameInfo;
	private static final String TOGGLE_PROMPT = "\n(Click Here To Toggle)";
	private static final String BUTTON_TEXT_DESTINATION = "Destination Game" + TOGGLE_PROMPT;
	private static final String BUTTON_IMAGE_DESTINATION = "destination_game.gif";
	private static final String BUTTON_TEXT_INFINITE = "Infinite-Scrolling Game" + TOGGLE_PROMPT;
	private static final String BUTTON_IMAGE_INFINITE = "infinite_scrolling_game.jpg";
	
	public ButtonGameType(GameInfo gameInfo) {
		super(BUTTON_TEXT_DESTINATION, BUTTON_IMAGE_DESTINATION);
		myGameInfo = gameInfo;
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> makeUpdates());
	}
	
	private void makeUpdates() {
		updateGameInfo();
		updateButton();
	}
	
	private void updateGameInfo() {
		myGameInfo.setIsDestinationGame(!myGameInfo.isDestinationGame());
	}
	
	private void updateButton() {
		boolean isDestinationGame = myGameInfo.isDestinationGame();
		String updatedButtonText = isDestinationGame ? BUTTON_TEXT_DESTINATION : BUTTON_TEXT_INFINITE;
		String updatedImageName = isDestinationGame ? BUTTON_IMAGE_DESTINATION : BUTTON_IMAGE_INFINITE;
		getButton().setText(updatedButtonText);
		setImageName(updatedImageName);
		setButtonIcon();
	}

}
