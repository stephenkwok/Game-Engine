package gui.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import authoringenvironment.model.IEditingEnvironment;
import authoringenvironment.view.GUIMainScreen;
import authoringenvironment.view.HBoxWithEditable;
import authoringenvironment.view.HBoxWithLevel;
import gameengine.controller.Level;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopUpLevelReorderer extends PopUpParent {

	private static final String SUCCESS_MESSAGE = "Reorder Successful!";
	private static final String INVALID_INPUT_MESSAGE = "Invalid Input. Please try again";
	private static final String BUTTON_TEXT = "Close Window";
	private static final int WIDTH = 250;
	private static final int HEIGHT = 100;
	private static final int MINIMUM_PLAY_POSITION = 1;
	private static final int MINIMUM_LIST_INDEX = 0;
	private List<HBoxWithLevel> myLevelPreviewUnits;
	private VBox myPreviewUnitsContainer;
	private List<Level> myLevels;
	private IEditingEnvironment myLevelEditor;
	private List<HBoxWithEditable> myPreviewUnits;
	private GUIMainScreen myMainScreen;
	private List<Integer> playPositions;
	private Set<Integer> uniquePlayPositions;
	private Button myCloseButton;
	private Label myLabel;

	public PopUpLevelReorderer(List<HBoxWithLevel> levelPreviewUnits, VBox previewUnitsContainer,
			List<Level> levels, IEditingEnvironment levelEditor, List<HBoxWithEditable> allPreviewUnits,
			GUIMainScreen mainScreen) {
		super(WIDTH, HEIGHT);
		myLevelPreviewUnits = levelPreviewUnits;
		myPreviewUnitsContainer = previewUnitsContainer;
		myLevels = levels;
		myLevelEditor = levelEditor;
		myPreviewUnits = allPreviewUnits;
		myMainScreen = mainScreen;
		initializeCloseButton();
		initializeLabel();
		addNodesToPopUp();
		performErrorChecking();
	}
	
	private void initializeLabel() {
		myLabel = new Label();
		myLabel.setAlignment(Pos.CENTER);
		myLabel.prefWidthProperty().bind(getContainer().widthProperty());
	}
	
	private void initializeCloseButton() {
		myCloseButton = new Button(BUTTON_TEXT);
		myCloseButton.prefWidthProperty().bind(getContainer().widthProperty());
		myCloseButton.setOnAction(e -> closePopUp());
	}
	
	private void addNodesToPopUp() {
		getContainer().getChildren().addAll(myLabel, myCloseButton);
	}

	private void performErrorChecking() {
		playPositions = new ArrayList<Integer>();
		myLevelPreviewUnits.stream().forEach(display -> playPositions.add(display.getLevelPlayPosition()));
		uniquePlayPositions = new HashSet<Integer>(playPositions);
		Collections.sort(playPositions);
		if (validDataEntered()) {
			reorderLevels();
			reportResult(SUCCESS_MESSAGE);
		}
		else
			reportResult(INVALID_INPUT_MESSAGE);
	}

	private void reorderLevels() {
		myLevelPreviewUnits.stream().forEach(unit -> unit.updateLevelPlayPosition());
		myLevelPreviewUnits.clear();
		myPreviewUnitsContainer.getChildren().clear();
		Collections.sort(myLevels);
		myLevels.stream().forEach(level -> myMainScreen.createLevelLabel(level, myLevelEditor));
		myPreviewUnits.stream().filter(unit -> myPreviewUnitsContainer.getChildren().contains(unit));
		myMainScreen.updatePreviewUnits();
	}
	
	private void reportResult(String message) {
		myLabel.setText(message);
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
