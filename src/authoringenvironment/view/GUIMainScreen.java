package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
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

	public GUIMainScreen(Controller controller, List<IActor> createdActors, List<ILevel> createdLevels) {
		this.controller = controller;
		this.createdActors = createdActors;
		this.createdLevels = createdLevels;
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
//		HBox.setHgrow(scrollPaneContainer, Priority.ALWAYS);
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
		container.setAlignment(Pos.TOP_CENTER);
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
		LabelActor label = new LabelActor(newActor, controller);
		processLabel(actorLabelContainer, label);
		IActor actorAdded = (IActor) newActor;
		createdActors.add(actorAdded);
		controller.goToActorEditing(actorAdded);
	}
	
	public void addLevel() {
		IEditableGameElement newLevel = new Level();
		LabelLevel label = new LabelLevel(newLevel, controller);
		processLabel(levelLabelContainer, label);
		ILevel levelAdded = (ILevel) newLevel;
		createdLevels.add(levelAdded);
		controller.goToLevelEditing(levelAdded);
	}
	
	private void processLabel(VBox container, LabelClickable label) {
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
