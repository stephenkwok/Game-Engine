package authoringenvironment.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.Level;
import gui.view.IGUI;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * 
 * This class sets up all the GUI elements on the Game Authoring Environment's
 * Main Screen
 * 
 * @author Stephen
 *
 */

public class GUIMainScreen implements IGUI, Observer {

	private static final int NUM_SCROLLPANES = 2;
	private DoubleExpression screenWidth, screenHeight;
	private VBox actorPreviewContainer, levelPreviewContainer, createdLevelsDisplay, createdActorsDisplay;
	private ScrollPane actorScrollPane, levelScrollPane;
	private HBoxDisplayHeader actorsDisplayHeader, levelsDisplayHeader;
	private HBox centerPane;
	private BorderPane borderPane;
	private List<HBoxWithEditable> allPreviewDisplays;
	private List<HBoxWithLevel> levelPreviewDisplays;
	private List<Level> levels;
	private IEditingEnvironment levelEditor;
	private GameEditingEnvironment gameEditor;

	public GUIMainScreen(GameEditingEnvironment gameEditor, DoubleExpression screenWidth, DoubleExpression screenHeight,
			List<Level> levels, IEditingEnvironment levelEditor) {
		this.gameEditor = gameEditor;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.levels = levels;
		this.levelEditor = levelEditor;
		allPreviewDisplays = new ArrayList<>();
		levelPreviewDisplays = new ArrayList<>();
		actorScrollPane = new ScrollPane();
		levelScrollPane = new ScrollPane();
		initializeEnvironment();
	}

	/**
	 * Initializes the Main Screen's GUI Elements
	 */
	public void initializeEnvironment() {
		initializeBorderPane();
		initializeCenterPane();
		initializeCreatedEditablesDisplay();
		initializeCreatedEditablesDisplayHeaders();
		initializeEditableGameElementPreviewContainers();
		initializeScrollPanes();
		initializeGameEditingEnvironment();
		createdActorsDisplay.getChildren().addAll(actorsDisplayHeader.getHBox(), actorScrollPane);
		createdLevelsDisplay.getChildren().addAll(levelsDisplayHeader.getHBox(), levelScrollPane);
		centerPane.getChildren().addAll(createdActorsDisplay, createdLevelsDisplay);
		borderPane.setCenter(centerPane);
	}

	/**
	 * Initializes the Game Editing Environment and sets it as the BorderPane's
	 * left pane
	 */
	private void initializeGameEditingEnvironment() {
		borderPane.setLeft(gameEditor.createNode());
	}

	/**
	 * Initializes the BorderPane for the MainScreen
	 */
	private void initializeBorderPane() {
		borderPane = new BorderPane();
		bindNodeSizeToGivenSize(borderPane, screenWidth, screenHeight);
	}

	/**
	 * Initializes the HBox containing the two ScrollPanes holding the labels
	 * representing the actors and levels to be edited
	 */
	private void initializeCenterPane() {
		centerPane = new HBox();
		bindNodeSizeToGivenSize(centerPane, borderPane.widthProperty(), borderPane.heightProperty());
	}

	private void initializeCreatedEditablesDisplay() {
		createdActorsDisplay = new VBox();
		createdLevelsDisplay = new VBox();
		bindNodeSizeToGivenSize(createdActorsDisplay, centerPane.widthProperty().divide(NUM_SCROLLPANES), null);
		bindNodeSizeToGivenSize(createdLevelsDisplay, centerPane.widthProperty().divide(NUM_SCROLLPANES), null);
	}

	private void initializeCreatedEditablesDisplayHeaders() {
		actorsDisplayHeader = new HBoxDisplayHeaderActor(createdActorsDisplay.prefWidthProperty());
		levelsDisplayHeader = new HBoxDisplayHeaderLevel(createdLevelsDisplay.prefWidthProperty());
		levelsDisplayHeader.addObserver(this);
	}

	/**
	 * Initializes the ScrollPanes holding the labels representing the actors
	 * and levels to be edited
	 */
	private void initializeScrollPanes() {
		initScrollPane(actorScrollPane, createdActorsDisplay.widthProperty(), createdActorsDisplay.heightProperty(),
				actorPreviewContainer);
		initScrollPane(levelScrollPane, createdLevelsDisplay.widthProperty(), createdLevelsDisplay.heightProperty(),
				levelPreviewContainer);
	}

	/**
	 * Initializes a ScrollPane and has its size bound to half of the center
	 * pane's width and sets its contents to the container holding the elements
	 * to be placed within the ScrollPane
	 * 
	 * @param scrollPane
	 *            whose size is to be bound and whose content is to be set
	 * @param container
	 *            the container holding the elements to be placed within the
	 *            ScrollPane
	 */
	private void initScrollPane(ScrollPane scrollPane, DoubleExpression bindWidth, DoubleExpression bindHeight,
			Node initialContent) {
		bindNodeSizeToGivenSize(scrollPane, bindWidth,
				bindHeight.subtract(levelsDisplayHeader.getHBox().heightProperty()));
		scrollPane.setContent(initialContent); // can't just subtract by
												// levelsDisplayHeader
	}

	/**
	 * Initializes the VBox containers holding labels representing actors and
	 * levels to be edited
	 */
	private void initializeEditableGameElementPreviewContainers() {
		actorPreviewContainer = createEditableGameElementPreviewContainer(actorScrollPane);
		levelPreviewContainer = createEditableGameElementPreviewContainer(levelScrollPane);
	}

	/**
	 * Initializes a single Label container and binds its size to a ScrollPane's
	 * width and height
	 * 
	 * @param parentScrollPane
	 *            - ScrollPane to which the Label container's width and height
	 *            will be bound
	 * @return the Label container initialized
	 */
	private VBox createEditableGameElementPreviewContainer(ScrollPane parentScrollPane) {
		VBox container = new VBox();
		bindNodeSizeToGivenSize(container, parentScrollPane.widthProperty(), parentScrollPane.heightProperty());
		return container;
	}

	/**
	 * Binds a region to a given width and height
	 * 
	 * @param child
	 *            - region whose width and height are to be bound
	 * @param width
	 *            - the width the child's width will be bound to
	 * @param height
	 *            - the height the child's height will be bound to
	 */
	private void bindNodeSizeToGivenSize(Region child, DoubleExpression width, DoubleExpression height) {
		if (width != null)
			child.prefWidthProperty().bind(width);
		if (height != null)
			child.prefHeightProperty().bind(height);
	}

	/**
	 * Returns the BorderPane containing all GUI elements in the MainScreen
	 */
	public Pane getPane() {
		return borderPane;
	}

	/**
	 * Creates a LabelClickable associated with an Actor
	 * 
	 * @param actor:
	 *            actor to be linked to LabelClickable being created
	 * @param actorEditor:
	 *            IEditingEnvironment in which Actor is to be edited
	 * @return a LabelClickable associated with an Actor
	 */
	public HBoxWithEditable createActorLabel(IEditableGameElement actor, IEditingEnvironment actorEditor) {
		HBoxWithEditable actorPreviewUnit = new HBoxWithEditable(actor, actorEditor);
		initializePreviewUnit(actorPreviewUnit, actorPreviewContainer);
		return actorPreviewUnit;
	}

	/**
	 * 
	 * @param level:
	 *            level to be linked to LabelClickable being created
	 * @param levelEditor:
	 *            IEditingEnvironment in which Level is to be edited
	 * @return a LabelClickable associated with a Level
	 */
	public HBoxWithEditable createLevelLabel(IEditableGameElement level, IEditingEnvironment levelEditor) {
		HBoxWithLevel levelPreviewUnit = new HBoxWithLevel(level, levelEditor);
		initializePreviewUnit(levelPreviewUnit, levelPreviewContainer);
		levelPreviewDisplays.add(levelPreviewUnit);
		return levelPreviewUnit;
	}

	private void initializePreviewUnit(HBoxWithEditable previewUnit, VBox parent) {
		HBox previewUnitHBox = previewUnit.getHBox();
		bindNodeSizeToGivenSize(previewUnitHBox, parent.widthProperty(), null);
		parent.getChildren().add(previewUnitHBox);
		allPreviewDisplays.add(previewUnit);
	}

	// /**
	// * Initializes LabelClickable associated with an actor or level
	// *
	// * @param editable:
	// * IEditableGameElement associated with LabelClickable
	// * @param environment:
	// * IEditingEnvironment in which editable is to be edited
	// * @param container:
	// * Node that will contain the LabelClickable created
	// */
	// private HBoxWithEditable createLabel(IEditableGameElement editable,
	// IEditingEnvironment environment,
	// VBox container) {
	// HBoxWithEditable labelWrapper = new HBoxWithEditable(editable,
	// environment);
	// Label label = labelWrapper.getLabel();
	// bindNodeSizeToGivenSize(label, container.widthProperty(), null);
	// container.getChildren().add(label);
	// allLabels.add(labelWrapper);
	// return labelWrapper;
	// }

	/**
	 * Updates all LabelClickables to account for any changes in the name or
	 * image of Actors and Levels
	 */
	public void updatePreviewDisplays() {
		allPreviewDisplays.stream().forEach(label -> label.update());
	}

	/**
	 * Reorders Level Labels so that Labels with Levels that appear earlier in
	 * the game are displayed toward the top of the ScrollPane containing all
	 * Level Labels
	 */
	private void reorderLevelLabels() {
		levelPreviewDisplays.stream().forEach(display -> display.updateLevelPlayPosition());
		levelPreviewDisplays.clear();
		levelPreviewContainer.getChildren().clear();
		Collections.sort(levels);
		// labels aren't adding controller as observer
		levels.stream().forEach(level -> createLevelLabel(level, levelEditor));
		// levelPreviewDisplays.stream()
		// .forEach(display ->
		// display.setEditableElement(levels.get(levelPreviewDisplays.indexOf(display))));
		allPreviewDisplays.stream().filter(label -> levelPreviewContainer.getChildren().contains(label));
		updatePreviewDisplays();
	}

	@Override
	public void update(Observable o, Object arg) {
		errorCheckPlayPositions();
	}

	private void errorCheckPlayPositions() {
		List<Integer> playPositions = new ArrayList<Integer>();
		levelPreviewDisplays.stream().forEach(display -> playPositions.add(display.getLevelPlayPosition()));
		// System.out.println("Play Positions: " + playPositions);
		Collections.sort(playPositions);
		// System.out.println("Sorted Play Positions: " + playPositions);
		Set<Integer> uniquePlayPositions = new HashSet<Integer>(playPositions);
		// System.out.println(!playPositions.isEmpty());
		// System.out.println(playPositions.get(0) == 1);
		// System.out.println(playPositions.get(playPositions.size() - 1) ==
		// playPositions.size());
		// System.out.println(playPositions.size() ==
		// uniquePlayPositions.size());
		if (!playPositions.isEmpty() && playPositions.get(0) == 1
				&& playPositions.get(playPositions.size() - 1) == playPositions.size()
				&& playPositions.size() == uniquePlayPositions.size()) {
			reorderLevelLabels();
		} else {
			System.out.println("Fail");
		}
	}

}