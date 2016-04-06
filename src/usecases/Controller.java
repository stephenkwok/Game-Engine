package usecases;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.actors.PowerUpActor;
import gameengine.controller.ILevel;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.IActor;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller extends Application {

	private Stage stage;
	private Scene mainScreenScene;
	private Group root;
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Main Screen";
	private IEditingEnvironment levelEditor;
	private IEditingEnvironment actorEditor;
	private BorderPane borderPane;
	private HBox container;
	private List<IEditableGameElement> createdActors;
	private List<IEditableGameElement> createdLevels;
	private VBox actorLabelContainer;
	private VBox levelLabelContainer;
	private ScrollPane actorScrollPane;
	private ScrollPane levelScrollPane;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle(TITLE);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		initMainScreen();
		initLevelEditingEnvironment();
		initActorEditingEnvironment();
		createdActors = new ArrayList<IEditableGameElement>();
		createdLevels = new ArrayList<IEditableGameElement>();
		stage.setScene(mainScreenScene);
		show();
	}

	private void initMainScreen() {
		root = new Group();
		borderPane = new BorderPane();
		Button addActorButton = new Button("Add Actor");
		addActorButton.setOnAction(e -> addActor());
		Button addLevelButton = new Button("Add Level");
		addLevelButton.setOnAction(e -> addLevel());
		initCenter();
		HBox topBar = new HBox();
		topBar.setAlignment(Pos.CENTER);
		topBar.getChildren().add(addActorButton);
		topBar.getChildren().add(addLevelButton);
		borderPane.setTop(topBar);
		borderPane.prefHeightProperty().bind(stage.heightProperty());
		borderPane.prefWidthProperty().bind(stage.widthProperty());
		root.getChildren().add(borderPane);
		mainScreenScene = new Scene(root);

	}

	private void initCenter() {
		container = new HBox();
		container.prefHeightProperty().bind(borderPane.heightProperty());
		container.prefWidthProperty().bind(borderPane.widthProperty());
		levelScrollPane = initScrollPane(container.widthProperty(), levelLabelContainer);
		actorScrollPane = initScrollPane(container.widthProperty(), actorLabelContainer);
		container.getChildren().addAll(levelScrollPane, actorScrollPane);
		borderPane.setCenter(container);
	}

	private ScrollPane initScrollPane(ReadOnlyDoubleProperty bindingProperty, VBox container) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.prefWidthProperty().bind(bindingProperty);
		scrollPane.setContent(container);
		return scrollPane;
	}

	private Label createLabel(IEditableGameElement element, IEditingEnvironment environment) {
		Label label = new Label(element.getName(), new ImageView(element.getImage()));
		label.setOnMouseClicked(e -> goToEditingEnviroment(environment, element));
		return label;
	}

	private void goToEditingEnviroment(IEditingEnvironment environment, IEditableGameElement editable) {
		environment.setEditable(editable);
//		stage.setScene(environment.getPane());
	}
	
	public void updateScrollPanes() {
		updateScrollPane(actorLabelContainer, createdActors, actorEditor);
		updateScrollPane(levelLabelContainer, createdLevels, levelEditor);
	}

	private void updateScrollPane(VBox container, List<IEditableGameElement> elements, IEditingEnvironment environment) {
		container.getChildren().clear();
		for (IEditableGameElement element : elements) {
			Label label = createLabel(element, environment);
			container.getChildren().add(label);
		}
	}

	public void show() {
		stage.show();
	}

	/**
	 * Instantiates new actor to be edited and switches scene to
	 * ActorEditingEnvironment
	 */
	private void addActor() {
		IEditableGameElement newActor = new PowerUpActor();
		createdActors.add(newActor);
		goToEditingEnviroment(actorEditor, newActor);
	}

	/**
	 * Instantiates new level to be edited and switches scene to
	 * LevelEditingEnvironment
	 */
	private void addLevel() {
		IEditableGameElement newLevel = new Level();
		createdLevels.add(newLevel);
		goToEditingEnviroment(levelEditor, newLevel);
	}

	public void saveGame() {

	}

	public void initLevelEditingEnvironment() {
		// levelEditor = new LevelEditor();
	}

	public void initActorEditingEnvironment() {
		// actorEditor = new ActorEditor();
	}

}
