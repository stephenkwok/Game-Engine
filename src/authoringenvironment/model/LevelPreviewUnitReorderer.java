package authoringenvironment.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import authoringenvironment.controller.GameEditingEnvironment;
import authoringenvironment.view.ActorsAndLevelsDisplay;
import authoringenvironment.view.PreviewUnitWithEditable;
import authoringenvironment.view.PreviewUnitWithLevel;
import gameengine.controller.Level;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

/**
 * 
 * This class reorders the levels created as specified by the author. If invalid
 * data is entered, an error message is generated notifying the author.
 * 
 * @author Stephen
 *
 */

public class LevelPreviewUnitReorderer {

	private static final String ERROR_MESSAGE = "Invalid Input. Please try again";
	private static final int MINIMUM_PLAY_POSITION = 1;
	private static final int MINIMUM_LIST_INDEX = 0;
	private final List<PreviewUnitWithLevel> myLevelPreviewUnits;
	private final List<PreviewUnitWithEditable> myPreviewUnits;
	private final List<Level> myLevels;
	private final VBox myPreviewUnitsContainer;
	private final ActorsAndLevelsDisplay myPreviewUnitsDisplay;
	private final GameEditingEnvironment myGameEditor;
	private List<Integer> playPositions;
	private Set<Integer> uniquePlayPositions;

	public LevelPreviewUnitReorderer(List<Level> levels, ActorsAndLevelsDisplay display,
			GameEditingEnvironment gameEditor) {
		myLevelPreviewUnits = display.getLevelPreviewUnits();
		myPreviewUnitsContainer = display.getLevelPreviewUnitsContainer();
		myLevels = levels;
		myPreviewUnits = display.getPreviewUnits();
		myPreviewUnitsDisplay = display;
		myGameEditor = gameEditor;
		initializeErrorChecking();
	}

	/**
	 * Initializes error checking
	 */
	private void initializeErrorChecking() {
		playPositions = new ArrayList<Integer>();
		myLevelPreviewUnits.stream().forEach(display -> playPositions.add(display.getLevelPlayPosition()));
		uniquePlayPositions = new HashSet<Integer>(playPositions);
		Collections.sort(playPositions);
	}

	/**
	 * Updates play positions for each level, sorts the list of levels so that
	 * levels to be played earlier come first, and repopulates the preview unit
	 * container so that preview units with earlier levels appear at the top of
	 * the container
	 */
	public void reorderLevels() {
		if (!validDataEntered())
			notifyAuthorOfError();
		myLevelPreviewUnits.stream().forEach(unit -> unit.updateLevelPlayPosition());
		myLevelPreviewUnits.clear();
		myPreviewUnitsContainer.getChildren().clear();
		Collections.sort(myLevels);
		myLevels.stream().forEach(level -> myGameEditor.createLevelPreviewUnit(level));
		myPreviewUnits.stream().filter(unit -> myPreviewUnitsContainer.getChildren().contains(unit));
		myPreviewUnitsDisplay.updatePreviewUnits();
	}

	/**
	 * Notifies the author that invalid input was entered
	 */
	private void notifyAuthorOfError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(ERROR_MESSAGE);
		alert.show();
	}

	/**
	 * Verifies whether user's input for each level's play position is valid
	 * 
	 * @return true if user input is valid; false otherwise
	 */
	private boolean validDataEntered() {
		return atLeastOneLevelCreated() && lowestPlayPositionEqualsOne() && highestPlayPositionEqualsMaxPlayPosition()
				&& noDuplicatePositionsEntered();
	}

	/**
	 * Verifies that at least one level was created
	 * 
	 * @return true if at least one level created; false otherwise
	 */
	private boolean atLeastOneLevelCreated() {
		return !playPositions.isEmpty();
	}

	/**
	 * Verifies that the lowest play position entered is equal to the lowest
	 * possible play position (1)
	 * 
	 * @return true if lowest play position equals 1; false otherwise
	 */
	private boolean lowestPlayPositionEqualsOne() {
		return playPositions.get(MINIMUM_LIST_INDEX) == MINIMUM_PLAY_POSITION;
	}

	/**
	 * Verifies that the highest play position entered is equal to the highest
	 * possible play possible given the number of levels created
	 * 
	 * @return true if the highest play position entered equals the number of
	 *         levels created; false otherwise
	 */
	private boolean highestPlayPositionEqualsMaxPlayPosition() {
		int highestPossiblePlayPosition = playPositions.size();
		int indexOfHighestEnteredPlayPosition = playPositions.size() - 1;
		return playPositions.get(indexOfHighestEnteredPlayPosition) == highestPossiblePlayPosition;
	}

	/**
	 * Verifies that no duplicate play positions were entered
	 * 
	 * @return true if no duplicated play positions entered; false otherwise
	 */
	private boolean noDuplicatePositionsEntered() {
		return playPositions.size() == uniquePlayPositions.size();
	}

}
