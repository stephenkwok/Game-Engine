package authoringenvironment.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.LevelPreviewUnitReorderer;
import authoringenvironment.view.ActorsAndLevelsDisplay;
import authoringenvironment.view.GameAttributesDisplay;
import authoringenvironment.view.PreviewUnitWithEditable;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * This class controls and manages the GUI elements that allow an author to edit
 * a game
 * 
 * @author Stephen
 *
 */

public class GameEditingEnvironment implements Observer {

	private final ActorsAndLevelsDisplay myActorsAndLevelsDisplay;
	private final GameAttributesDisplay myGameAttributesDisplay;
	private final BorderPane myBorderPane;
	private final Controller myController;
	private final List<Level> myLevels;
	private final GameInfo myGameInfo;
	private final Stage myStage;

	public GameEditingEnvironment(Controller controller, Stage stage, List<Level> levels, GameInfo gameInfo) {
		myController = controller;
		myLevels = levels;
		myStage = stage;
		myGameInfo = gameInfo;
		myBorderPane = new BorderPane();
		myActorsAndLevelsDisplay = new ActorsAndLevelsDisplay(myBorderPane.widthProperty(),
				myBorderPane.heightProperty());
		myGameAttributesDisplay = new GameAttributesDisplay(myGameInfo);
		initializeBorderPane();
		initializeActorsAndLevelsDisplay();
	}

	/**
	 * Initializes the BorderPane containing all GUI Elements in the Game
	 * Editing Environment
	 */
	private void initializeBorderPane() {
		myBorderPane.prefWidthProperty().bind(myStage.widthProperty());
		myBorderPane.prefHeightProperty().bind(myStage.heightProperty());
		myBorderPane.setCenter(myActorsAndLevelsDisplay.getPane());
		myBorderPane.setLeft(myGameAttributesDisplay.getNode());
	}

	/**
	 * Initializes the Pane displaying all created Actors and Levels
	 */
	private void initializeActorsAndLevelsDisplay() {
		myActorsAndLevelsDisplay.addObserver(this);
	}

	/**
	 * Reorders Level Labels so that Labels with Levels that appear earlier in
	 * the game are displayed toward the top of the ScrollPane containing all
	 * Level Labels
	 */
	private void reorderLevels() {
		LevelPreviewUnitReorderer levelReorderer = new LevelPreviewUnitReorderer(myLevels, myActorsAndLevelsDisplay, this);
		levelReorderer.reorderLevels();
	}

	/**
	 * Creates a preview unit displaying a created actor
	 * 
	 * @param actor:
	 *            actor to be displayed by the preview unit
	 * @param actorEditor:
	 *            editing environment used to edit the actor
	 */
	public void createActorPreviewUnit(IEditableGameElement actor) {
		PreviewUnitWithEditable previewUnit = myActorsAndLevelsDisplay.createActorPreviewUnit(actor);
		previewUnit.addObserver(myController);
	}

	/**
	 * Creates a preview unit displaying a created level
	 * 
	 * @param level:
	 *            level to be displayed by the preview unit
	 * @param levelEditor:
	 *            editing environment used to edit the level
	 */
	public void createLevelPreviewUnit(IEditableGameElement level) {
		PreviewUnitWithEditable previewUnit = myActorsAndLevelsDisplay.createLevelPreviewUnit(level);
		previewUnit.addObserver(myController);
	}

	/**
	 * Updates all preview units to reflect changes in any Actors or Levels
	 */
	public void updatePreviewUnits() {
		myActorsAndLevelsDisplay.updatePreviewUnits();
	}

	/**
	 * 
	 * @return the BorderPane containing all GUI elements in the Game Editing
	 *         Environment
	 */
	public Pane getPane() {
		return myBorderPane;
	}

	/**
	 * Reorders the Game's Levels
	 */
	@Override
	public void update(Observable o, Object arg) {
		reorderLevels();
	}

}
