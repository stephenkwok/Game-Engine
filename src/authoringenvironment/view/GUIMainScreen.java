package authoringenvironment.view;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import gameengine.controller.ILevel;
import gameengine.model.IActor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import usecases.Actor;
import usecases.Level;

public class GUIMainScreen implements IGUI {
	
	private Controller controller;
	private VBox actorLabelContainer;
	private VBox levelLabelContainer;
	private ScrollPane actorScrollPane;
	private ScrollPane levelScrollPane;
	private HBox scrollPaneContainer;
	private BorderPane borderPane;
	private List<ClickableLabel> clickableLabels;

	public GUIMainScreen(Controller controller) {
		this.controller = controller;
		clickableLabels = new ArrayList<ClickableLabel>();
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
//		borderPane.setStyle("-fx-border-color: red;");
		borderPane.prefHeightProperty().bind(stage.heightProperty());
		borderPane.prefWidthProperty().bind(stage.widthProperty());
	}
	
	private void initScrollPaneContainer() {
		scrollPaneContainer = new HBox();
//		scrollPaneContainer.setStyle("-fx-border-color: blue;");
//		HBox.setHgrow(scrollPaneContainer, Priority.ALWAYS);
		bindNodeSizeToParentSize(scrollPaneContainer, borderPane);
	}
	
	private void initLabelContainers() {
		actorLabelContainer = createLabelContainer("Add New Actor", e -> addActor());
		levelLabelContainer = createLabelContainer("Add New Level", e -> addLevel());
	}
	
	private VBox createLabelContainer(String buttonText, EventHandler<ActionEvent> buttonEvent) {
		VBox container = new VBox();
		bindNodeSizeToParentSize(container, scrollPaneContainer);
		// HARD-CODED VALUE
		container.setMaxWidth(630);
		container.setAlignment(Pos.TOP_CENTER);
		container.getChildren().add(createButton(buttonText, buttonEvent));
		return container;
	}
	
	private void bindNodeSizeToParentSize(Region child, Region parent) {
		child.prefWidthProperty().bind(parent.widthProperty());
		child.prefHeightProperty().bind(parent.heightProperty());
	}
	
	private Button createButton(String buttonText, EventHandler<ActionEvent> handler) {
		Button button = new Button(buttonText);
		button.setOnAction(handler);
		return button;
	}
	
	private ScrollPane initScrollPane(VBox labelContainer) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(labelContainer);
		return scrollPane;
	}

	public Pane getPane() {
		return borderPane;
	}
	
	private void addActor() {
		IEditableGameElement newActor = new Actor();
		ActorLabel label = new ActorLabel(newActor, controller);
		processLabel(actorLabelContainer, label);
		controller.goToActorEditing((IActor) newActor);
	}
	
	private void addLevel() {
		IEditableGameElement newLevel = new Level();
		LevelLabel label = new LevelLabel(newLevel, controller);
		processLabel(levelLabelContainer, label);
		controller.goToLevelEditing((ILevel) newLevel);
	}
	
	private void processLabel(VBox container, ClickableLabel label) {
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

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}
}
