package gui.view;

import gameengine.controller.GameInfo;

/**
 * 
 * This class creates a button displaying the Game's current playing mode (destination or infinite scrolling);
 * On click, the playing mode is toggled and the button's image and text is updated to reflect the 
 * new playing mode.
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
	private static final int BUTTON_ICON_SIZE = 45;
	
	public ButtonGameType(GameInfo gameInfo) {
		super(BUTTON_TEXT_DESTINATION, BUTTON_IMAGE_DESTINATION);
		myGameInfo = gameInfo;
		setIconSize(BUTTON_ICON_SIZE);
		updateButton();
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> makeUpdates());
	}
	
	/**
	 * Updates this class' instance of GameInfo so that the game type either switches
	 * from destination to infinite-scrolling, or from infinite-scrolling to destination.
	 * Also updates the button's image and text to reflect this change.
	 */
	private void makeUpdates() {
		updateGameInfo();
		updateButton();
	}
	
	/**
	 * Sets GameInfo's IsDestinationGame boolean to the opposite of its current value
	 */
	private void updateGameInfo() {
		myGameInfo.setIsDestinationGame(!myGameInfo.isDestinationGame());
	}
	
	/**
	 * Changes the button's text and image to reflect the Game's updated game type
	 */
	private void updateButton() {
		boolean isDestinationGame = myGameInfo.isDestinationGame();
		String updatedButtonText = isDestinationGame ? BUTTON_TEXT_DESTINATION : BUTTON_TEXT_INFINITE;
		String updatedImageName = isDestinationGame ? BUTTON_IMAGE_DESTINATION : BUTTON_IMAGE_INFINITE;
		getButton().setText(updatedButtonText);
		setImageName(updatedImageName);
		setButtonIcon();
	}

}
