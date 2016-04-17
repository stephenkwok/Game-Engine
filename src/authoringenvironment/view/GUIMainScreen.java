package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;
import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.GameInfo;
import gui.view.IGUI;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.control.Label;
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

public class GUIMainScreen implements IGUI {

	private static final int NUM_SCROLLPANES = 2;
	private DoubleExpression screenWidth;
	private DoubleExpression screenHeight;
	private Controller controller;
	private VBox actorLabelContainer;
	private VBox levelLabelContainer;
	private ScrollPane actorScrollPane;
	private ScrollPane levelScrollPane;
	private HBox scrollPaneContainer;
	private BorderPane borderPane;
	private List<LabelClickable> clickableLabels;
	private GameInfo gameInfo;

	public GUIMainScreen(Controller controller, GameInfo gameInfo, DoubleExpression screenWidth,
			DoubleExpression screenHeight) {
		this.controller = controller;
		this.gameInfo = gameInfo;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		clickableLabels = new ArrayList<LabelClickable>();
		levelScrollPane = new ScrollPane();
		actorScrollPane = new ScrollPane();
		initializeEnvironment();
	}

	/**
	 * Initializes the Main Screen's GUI Elements
	 */
	public void initializeEnvironment() {
		initBorderPane();
		initScrollPaneContainer();
		initLabelContainers();
		initScrollPanes();
		initializeGameEditingEnvironment();
		scrollPaneContainer.getChildren().addAll(levelScrollPane, actorScrollPane);
		borderPane.setCenter(scrollPaneContainer);
	}

	/**
	 * Initializes the Game Editing Environment and sets it as the BorderPane's
	 * left pane
	 */
	private void initializeGameEditingEnvironment() {
		GameEditingEnvironment gameEditingEnvironment = new GameEditingEnvironment(gameInfo, controller);
		borderPane.setLeft(gameEditingEnvironment.createNode());
	}

	/**
	 * Initializes the BorderPane for the MainScreen
	 */
	private void initBorderPane() {
		borderPane = new BorderPane();
		bindNodeSizeToGivenSize(borderPane, screenWidth, screenHeight);
	}

	/**
	 * Initializes the HBox containing the two ScrollPanes holding the labels
	 * representing the actors and levels to be edited
	 */
	private void initScrollPaneContainer() {
		scrollPaneContainer = new HBox();
		bindNodeSizeToGivenSize(scrollPaneContainer, borderPane.widthProperty(), borderPane.heightProperty());
	}

	/**
	 * Initializes the ScrollPanes holding the labels representing the actors
	 * and levels to be edited
	 */
	private void initScrollPanes() {
		initScrollPane(actorScrollPane, actorLabelContainer);
		initScrollPane(levelScrollPane, levelLabelContainer);
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
	private void initScrollPane(ScrollPane scrollPane, VBox container) {
		bindNodeSizeToGivenSize(scrollPane, scrollPaneContainer.widthProperty().divide(NUM_SCROLLPANES), null);
		scrollPane.setContent(container);
	}

	/**
	 * Initializes the VBox containers holding labels representing actors and
	 * levels to be edited
	 */
	private void initLabelContainers() {
		actorLabelContainer = createLabelContainer(actorScrollPane);
		levelLabelContainer = createLabelContainer(levelScrollPane);
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
	private VBox createLabelContainer(ScrollPane parentScrollPane) {
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
	 * @param actor
	 *            associated with the Label generated
	 */
	public LabelClickable createActorLabel(IEditableGameElement actor, IEditingEnvironment actorEditor) {
		return createLabel(actor, actorEditor, actorLabelContainer);
	}

	/**
	 * Creates a LabelClickable associated with a Level
	 * 
	 * @param level
	 *            associated with the Label generated
	 */
	public LabelClickable createLevelLabel(IEditableGameElement level, IEditingEnvironment levelEditor) {
		return createLabel(level, levelEditor, levelLabelContainer);
	}

	/**
	 * Initializes LabelClickable associated with an actor or level
	 * 
	 * @param editable
	 *            associated with LabelClickable
	 * @param environment
	 *            associated with LabelClickable
	 * @param container
	 *            to hold the Label
	 */
	private LabelClickable createLabel(IEditableGameElement editable, IEditingEnvironment environment, VBox container) {
		LabelClickable labelWrapper = new LabelClickable(editable, environment);
		Label label = labelWrapper.getLabel();
		bindNodeSizeToGivenSize(label, container.widthProperty(), null);
		container.getChildren().add(label);
		clickableLabels.add(labelWrapper);
		return labelWrapper;
	}

	/**
	 * Updates all LabelClickables to account for any changes in the name or
	 * image of Actors and Levels
	 */
	public void updateAllNodes() {
		clickableLabels.stream().forEach(label -> label.update());
	}

}
