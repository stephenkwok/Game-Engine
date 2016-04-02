package authoringenvironment.view;

import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.IEditableGameElement;
import gameengine.controller.ILevel;
import gameengine.model.IActor;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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
	private List<ClickableLabel> levelLabels;
	private List<ClickableLabel> actorLabels;

	public GUIMainScreen(Controller controller) {
		this.controller = controller;
		initializeEnvironment();
	}

	public void initializeEnvironment() {
		Stage stage = controller.getStage();
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-border-color: red;");
		borderPane.prefHeightProperty().bind(stage.heightProperty());
		borderPane.prefWidthProperty().bind(stage.widthProperty());
		scrollPaneContainer = new HBox();
		scrollPaneContainer.setStyle("-fx-border-color: blue;");
		HBox.setHgrow(scrollPaneContainer, Priority.ALWAYS);
		scrollPaneContainer.prefHeightProperty().bind(borderPane.heightProperty());
		scrollPaneContainer.prefWidthProperty().bind(borderPane.widthProperty());
		initLabelContainers();
		actorScrollPane = initScrollPane(actorLabelContainer.widthProperty(), actorLabelContainer);
		levelScrollPane = initScrollPane(levelLabelContainer.widthProperty(), levelLabelContainer);
		scrollPaneContainer.getChildren().addAll(levelScrollPane, actorScrollPane);
		borderPane.setCenter(scrollPaneContainer);
	}
	
	private void initLabelContainers() {
		actorLabelContainer = new VBox();
		levelLabelContainer = new VBox();
		actorLabelContainer.prefWidthProperty().bind(scrollPaneContainer.widthProperty());
		actorLabelContainer.prefHeightProperty().bind(scrollPaneContainer.heightProperty());
		levelLabelContainer.prefWidthProperty().bind(scrollPaneContainer.widthProperty());
		levelLabelContainer.prefHeightProperty().bind(scrollPaneContainer.heightProperty());
		actorLabelContainer.getChildren().add(createButton("Add New Actor", e -> addActor()));
		levelLabelContainer.getChildren().add(createButton("Add New Level", e -> addLevel()));
	}
	
	private Button createButton(String buttonText, EventHandler<ActionEvent> handler) {
		Button button = new Button(buttonText);
		button.setOnAction(handler);
		return button;
	}
	
	private ScrollPane initScrollPane(ReadOnlyDoubleProperty bindingProperty, VBox container) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(container);
		return scrollPane;
	}

	public Pane getPane() {
		return borderPane;
	}

	private void updateLabels(List<ClickableLabel> labels) {
		labels.stream().forEach(label -> label.update());
	}
	
	private void addActor() {
		IEditableGameElement newActor = new Actor();
		ActorLabel label = new ActorLabel(newActor, controller);
		actorLabelContainer.getChildren().add(label);
		controller.goToActorEditing((IActor) newActor);
	}
	
	private void addLevel() {
		IEditableGameElement newLevel = new Level();
		LevelLabel label = new LevelLabel(newLevel, controller);
		levelLabelContainer.getChildren().add(label);
		controller.goToLevelEditing((ILevel) newLevel);
	}
	
	@Override
	public void updateAllNodes() {
		updateLabels(levelLabels);
		updateLabels(actorLabels);
	}
}
