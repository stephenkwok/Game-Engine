package gui.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.view.HBoxWithLevel;
import gameengine.controller.Level;
import javafx.scene.layout.VBox;

public class PopUpReorderLevelErrorChecker extends PopUpParent {

	private static final String BACKGROUND_COLOR = "-fx-border-color: blue;";
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final int MINIMUM_PLAY_POSITION = 1;
	private static final int MINIMUM_LIST_INDEX = 0;
	private List<HBoxWithLevel> myLevelPreviewUnits;
	private List<Integer> playPositions;
	private Set<Integer> uniquePlayPositions;

	public PopUpReorderLevelErrorChecker(List<HBoxWithLevel> levelPreviewUnits, VBox previewUnitsContainer,
			List<Level> levels, IEditingEnvironment levelEditor) {
		super(BACKGROUND_COLOR, WIDTH, HEIGHT);
		myLevelPreviewUnits = levelPreviewUnits;
		performErrorChecking();
	}

	private void performErrorChecking() {
		playPositions = new ArrayList<Integer>();
		myLevelPreviewUnits.stream().forEach(display -> playPositions.add(display.getLevelPlayPosition()));
		uniquePlayPositions = new HashSet<Integer>(playPositions);
		Collections.sort(playPositions);
		if (validDataEntered())
			reorderLevels();
		else
			reportError();
	}

	private void reorderLevels() {

	}

	private void reportError() {

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
