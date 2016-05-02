package authoringenvironment.model;

import java.util.List;

import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Sets a Game's preview image
 * 
 * @author Stephen
 *
 */

public class GamePreviewImageSetter {

	private static final int INDEX_OF_FIRST_LEVEL = 0;
	private static final String ERROR_MESSAGE = "Please create at least one level";
	private final GameInfo myGameInfo;
	private final List<Level> myLevels;
	private boolean gameImageSetSuccessful;

	/**
	 * Creates an image setter that sets the Game's preview image
	 * @param info: the GameInfo object being edited
	 * @param levels: the Game's list of levels
	 */
	public GamePreviewImageSetter(GameInfo info, List<Level> levels) {
		myGameInfo = info;
		myLevels = levels;
		gameImageSetSuccessful = false;
	}
	
	/**
	 * Sets a Game's preview image if at least one level has been created. Notifies
	 * author of error otherwise;
	 */
	public void setGameImage() {
		if (atLeastOneLevelExists()) {
			myGameInfo.setMyImageName(myLevels.get(INDEX_OF_FIRST_LEVEL).getMyBackgroundImgName());
			gameImageSetSuccessful = true;
		}
		else {
			notifyAuthorOfError();
			gameImageSetSuccessful = false;
		}
	}
	
	/**
	 * 
	 * @return true if at least one level has been created; false otherwise
	 */
	private boolean atLeastOneLevelExists() {
		return !myLevels.isEmpty();
	}
	
	/**
	 * Notifies the author that at least one Level must be created
	 */
	private void notifyAuthorOfError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(ERROR_MESSAGE);
		alert.show();
	}
	
	/**
	 * @return true if the Game's preview image was successfully set; false otherwise
	 */
	public boolean gameImageSetSuccessful() {
		return gameImageSetSuccessful;
	}
	
}
