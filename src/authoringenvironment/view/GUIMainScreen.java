package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.GameInfo;
import gui.view.IGUI;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
	private Controller controller;
	private VBox actorLabelContainer;
	private VBox levelLabelContainer;
	private ScrollPane actorScrollPane;
	private ScrollPane levelScrollPane;
	private HBox scrollPaneContainer;
	private BorderPane borderPane;
	private List<LabelClickable> clickableLabels;
	private IEditingEnvironment actorEditor;
	private IEditingEnvironment levelEditor;
	private GameInfo gameInfo;

	public GUIMainScreen(Controller controller, IEditingEnvironment actorEditor, IEditingEnvironment levelEditor,
			GameInfo gameInfo) {
		this.controller = controller;
		this.actorEditor = actorEditor;
		this.levelEditor = levelEditor;
		this.gameInfo = gameInfo;
		clickableLabels = new ArrayList<LabelClickable>();
		levelScrollPane = new ScrollPane();
		actorScrollPane = new ScrollPane();
		initializeEnvironment();
	}

	public void initializeEnvironment() {
		initBorderPane();
		initScrollPaneContainer();
		initLabelContainers();
		initScrollPanes();
		initLeftPane();
		scrollPaneContainer.getChildren().addAll(levelScrollPane, actorScrollPane);
		borderPane.setCenter(scrollPaneContainer);
	}

	private void initLeftPane() {
		GUIGameEditingEnvironment gameEditingEnvironment = new GUIGameEditingEnvironment(gameInfo, controller);
		borderPane.setLeft(gameEditingEnvironment.createNode());
	}

	private void initBorderPane() {
		Stage stage = controller.getStage();
		borderPane = new BorderPane();
		bindNodeSizeToGivenSize(borderPane, stage.widthProperty(), stage.heightProperty());
	}

	private void initScrollPaneContainer() {
		scrollPaneContainer = new HBox();
		bindNodeSizeToGivenSize(scrollPaneContainer, borderPane.widthProperty(), borderPane.heightProperty());
	}

	private void initScrollPanes() {
		initScrollPane(actorScrollPane, actorLabelContainer);
		initScrollPane(levelScrollPane, levelLabelContainer);
	}

	private void initScrollPane(ScrollPane scrollPane, VBox container) {
		bindNodeSizeToGivenSize(scrollPane, scrollPaneContainer.widthProperty().divide(NUM_SCROLLPANES), null);
		scrollPane.setContent(container);
	}

	private void initLabelContainers() {
		actorLabelContainer = createLabelContainer(actorScrollPane);
		levelLabelContainer = createLabelContainer(levelScrollPane);
	}

	private VBox createLabelContainer(ScrollPane parentScrollPane) {
		VBox container = new VBox();
		bindNodeSizeToGivenSize(container, parentScrollPane.widthProperty(), parentScrollPane.heightProperty());
		return container;
	}

	private void bindNodeSizeToGivenSize(Region child, DoubleExpression width, DoubleExpression height) {
		if (width != null)
			child.prefWidthProperty().bind(width);
		if (height != null)
			child.prefHeightProperty().bind(height);
	}

	public Pane getPane() {
		return borderPane;
	}

	public void createActorLabel(IEditableGameElement actor) {
		createLabel(actor, actorEditor, actorLabelContainer);
	}

	public void createLevelLabel(IEditableGameElement level) {
		createLabel(level, levelEditor, levelLabelContainer);
	}

	private void createLabel(IEditableGameElement editable, IEditingEnvironment environment, VBox container) {
		LabelClickable label = new LabelClickable(editable, environment, controller);
		bindNodeSizeToGivenSize(label, container.widthProperty(), null);
		container.getChildren().add(label);
		clickableLabels.add(label);
	}

	public void updateAllNodes() {
		clickableLabels.stream().forEach(label -> label.update());
	}

}
