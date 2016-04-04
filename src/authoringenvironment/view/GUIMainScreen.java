package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.controller.Actor;
import gameengine.controller.ILevel;
import gameengine.controller.Level;
import gameengine.model.IActor;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Stephen
 *
 */

public class GUIMainScreen implements IGUI {

	private Controller controller;
	private VBox actorLabelContainer;
	private VBox levelLabelContainer;
	private ScrollPane actorScrollPane;
	private ScrollPane levelScrollPane;
	private HBox scrollPaneContainer;
	private BorderPane borderPane;
	private List<LabelClickable> clickableLabels;
	private List<IActor> createdActors;
	private List<ILevel> createdLevels;
	private IEditingEnvironment actorEditor;
	private IEditingEnvironment levelEditor;

	public GUIMainScreen(Controller controller, List<IActor> createdActors, List<ILevel> createdLevels,
			IEditingEnvironment actorEditor, IEditingEnvironment levelEditor) {
		this.controller = controller;
		this.createdActors = createdActors;
		this.createdLevels = createdLevels;
		this.actorEditor = actorEditor;
		this.levelEditor = levelEditor;
		clickableLabels = new ArrayList<LabelClickable>();
		initializeEnvironment();
	}

	public void initializeEnvironment() {
		initBorderPane();
		initScrollPaneContainer();
		initLabelContainers();
		actorScrollPane = initScrollPane(actorLabelContainer);
		levelScrollPane = initScrollPane(levelLabelContainer);
		scrollPaneContainer.getChildren().addAll(levelScrollPane, actorScrollPane);
		borderPane.setCenter(scrollPaneContainer);
	}

	private void initBorderPane() {
		Stage stage = controller.getStage();
		borderPane = new BorderPane();
		borderPane.prefHeightProperty().bind(stage.heightProperty());
		borderPane.prefWidthProperty().bind(stage.widthProperty());
	}

	private void initScrollPaneContainer() {
		scrollPaneContainer = new HBox();
		// HBox.setHgrow(scrollPaneContainer, Priority.ALWAYS);
		bindNodeSizeToParentSize(scrollPaneContainer, borderPane);
	}

	private void initLabelContainers() {
		actorLabelContainer = createLabelContainer();
		levelLabelContainer = createLabelContainer();
	}

	private VBox createLabelContainer() {
		VBox container = new VBox();
		bindNodeSizeToParentSize(container, scrollPaneContainer);
		// HARD-CODED VALUE
		container.setMaxWidth(630);
		return container;
	}

	private ScrollPane initScrollPane(VBox labelContainer) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(labelContainer);
		return scrollPane;
	}

	private void bindNodeSizeToParentSize(Region child, Region parent) {
		child.prefWidthProperty().bind(parent.widthProperty());
		child.prefHeightProperty().bind(parent.heightProperty());
	}

	public Pane getPane() {
		return borderPane;
	}

	public void addActor() {
		IEditableGameElement newActor = new Actor();
		IActor actorAdded = (IActor) newActor;
		createdActors.add(actorAdded);
		createLabel(newActor, actorEditor, actorLabelContainer);
		controller.goToEditingEnvironment(newActor, actorEditor);
	}

	public void addLevel() {
		IEditableGameElement newLevel = new Level();
		ILevel levelAdded = (ILevel) newLevel;
		createdLevels.add(levelAdded);
		createLabel(newLevel, levelEditor, levelLabelContainer);
		controller.goToEditingEnvironment(newLevel, levelEditor);
	}

	public void createLabel(IEditableGameElement editable, IEditingEnvironment environment, VBox container) {
		LabelClickable label = new LabelClickable(editable, environment, controller);
		container.getChildren().add(label);
		clickableLabels.add(label);
	}

	@Override
	public void updateAllNodes() {
		updateLabels();
	}

	private void updateLabels() {
		clickableLabels.stream().forEach(label -> label.update());
	}
}
