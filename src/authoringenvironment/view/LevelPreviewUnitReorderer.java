package authoringenvironment.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.Level;
import gui.view.PopUpParent;
import gui.view.PopUpSimpleErrorMessage;
import javafx.scene.layout.VBox;

/**
 * 
 * This class reorders the levels created as specified by the author and
 * generates a pop up informing the author whether the reordering was
 * successful, or if invalid input was entered
 * 
 * @author Stephen
 *
 */

public class LevelPreviewUnitReorderer {

	private static final String POPUP_ERROR_MESSAGE = "Invalid Input. Please try again";
	private static final String POPUP_BUTTON_TEXT = "Close Window";
	private static final int POPUP_WIDTH = 250;
	private static final int POPUP_HEIGHT = 100;
	private static final int MINIMUM_PLAY_POSITION = 1;
	private static final int MINIMUM_LIST_INDEX = 0;
	private List<PreviewUnitWithLevel> myLevelPreviewUnits;
	private VBox myPreviewUnitsContainer;
	private List<Level> myLevels;
	private IEditingEnvironment myLevelEditor;
	private List<PreviewUnitWithEditable> myPreviewUnits;
	private GUIMainScreen myMainScreen;
	private List<Integer> playPositions;
	private Set<Integer> uniquePlayPositions;

	public LevelPreviewUnitReorderer(List<PreviewUnitWithLevel> levelPreviewUnits, VBox previewUnitsContainer,
			List<Level> levels, IEditingEnvironment levelEditor, List<PreviewUnitWithEditable> allPreviewUnits,
			GUIMainScreen mainScreen) {
		myLevelPreviewUnits = levelPreviewUnits;
		myPreviewUnitsContainer = previewUnitsContainer;
		myLevels = levels;
		myLevelEditor = levelEditor;
		myPreviewUnits = allPreviewUnits;
		myMainScreen = mainScreen;
		performErrorChecking();
	}

	private void performErrorChecking() {
		playPositions = new ArrayList<Integer>();
		myLevelPreviewUnits.stream().forEach(display -> playPositions.add(display.getLevelPlayPosition()));
		uniquePlayPositions = new HashSet<Integer>(playPositions);
		Collections.sort(playPositions);
		if (validDataEntered())
			reorderLevels();
		else {
			@SuppressWarnings("unused")
			PopUpParent errorNotification = new PopUpSimpleErrorMessage(POPUP_WIDTH, POPUP_HEIGHT, POPUP_BUTTON_TEXT,
					POPUP_ERROR_MESSAGE);
		}
	}

	private void reorderLevels() {
		myLevelPreviewUnits.stream().forEach(unit -> unit.updateLevelPlayPosition());
		myLevelPreviewUnits.clear();
		myPreviewUnitsContainer.getChildren().clear();
		Collections.sort(myLevels);
		myLevels.stream().forEach(level -> myMainScreen.createLevelPreviewUnit(level, myLevelEditor));
		myPreviewUnits.stream().filter(unit -> myPreviewUnitsContainer.getChildren().contains(unit));
		myMainScreen.updatePreviewUnits();
	}

	private boolean validDataEntered() {
		return atLeastOneLevelCreated() && lowestPlayPositionEqualsOne() && highestPlayPositionEqualsMaxPlayPosition()
				&& noDuplicatePositionsEntered();
	}

	private boolean atLeastOneLevelCreated() {
		return !playPositions.isEmpty();
	}

	private boolean lowestPlayPositionEqualsOne() {
		return playPositions.get(MINIMUM_LIST_INDEX) == MINIMUM_PLAY_POSITION;
	}

	private boolean highestPlayPositionEqualsMaxPlayPosition() {
		int highestPossiblePlayPosition = playPositions.size();
		int indexOfHighestEnteredPlayPosition = playPositions.size() - 1;
		return playPositions.get(indexOfHighestEnteredPlayPosition) == highestPossiblePlayPosition;
	}

	private boolean noDuplicatePositionsEntered() {
		return playPositions.size() == uniquePlayPositions.size();
	}

}
