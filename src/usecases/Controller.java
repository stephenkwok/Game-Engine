package usecases;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.controller.LevelEditingEnvironment;
import gameengine.controller.ILevel;
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
	private LevelEditingEnvironment levelEditor;
	private ActorEditingEnvironment actorEditor;
	private BorderPane borderPane;
	private HBox container;
	private List<IActor> createdActors;
	private List<ILevel> createdLevels;
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
		createdActors = new ArrayList<IActor>();
		createdLevels = new ArrayList<ILevel>();
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
	
	private void createLabel(String text, Image image, ) {
		// need text, image, and onClick event 
	}
	
	// Could use marker interface pattern to remove duplicated code
	private void updateScrollPanes() {
		// would like getName() method in Actor
		actorLabelContainer.getChildren().clear();
		for (IActor actor : createdActors) {
			Actor myActor = (Actor) actor; // how to avoid casting here?
			int ID = actor.getID();
			Image image = myActor.getImage();
			Label label = new Label(String.valueOf(ID), new ImageView(image));
			label.setOnMouseClicked(e -> goToActorEditing(actor));
			actorLabelContainer.getChildren().add(label);
		}
		// need getImage() method in Level
		levelLabelContainer.getChildren().clear();
		for (ILevel level : createdLevels) {
			Level myLevel = (Level) level; // how to avoid casting here?
			String name = level.getName();
//			Image image = level.getImage();
//			Label label = new Label(name, new ImageView(image));
//			label.setOnMouseClicked(e -> goToLevelEditing(level, createdLevels));
//			levelLabelContainer.getChildren().add(label);
		}
	}
	
//	private void updateScrollPane(VBox container, List<EditableGameElement> elements) {
//		container.getChildren().clear();
//	}

	public void show() {
		stage.show();
	}
	
	/**
	 * Instantiates new actor to be edited and switches scene to ActorEditingEnvironment
	 */
	private void addActor() {
		int actorID = createdActors.size() + 1; // 
		IActor newActor = new Actor(actorID);
		createdActors.add(newActor);
		goToActorEditing(newActor);
	}
	
	/**
	 * Instantiates new level to be edited and switches scene to LevelEditingEnvironment
	 */
	private void addLevel() {
		ILevel newLevel = new Level(); 
		createdLevels.add(newLevel);
		goToLevelEditing(newLevel, createdActors);
	}

	/**
	 * 
	 * When the user presses a button to add a new level, the LevelEditor is
	 * passed the level to be edited and a list of Actors that can be added to
	 * that level. The stage is then set to the scene of the Level Editor
	 * 
	 * @param level
	 *            to be edited
	 * @param createdActors
	 *            that can be added to that level
	 */

	public void goToLevelEditing(ILevel level, List<IActor> createdActors) {
		levelEditor.setLevel(level, createdActors);
		stage.setScene(levelEditor.getScene());
	}

	/**
	 * 
	 * When the user presses a button to add a new actor, the ActorEditor is
	 * passed the actor to be edited. The stage is then set to the scene of the
	 * ActorEditor
	 * 
	 * @param actor
	 *            IActor to be edited
	 */

	public void goToActorEditing(IActor actor) {
		actorEditor.setActor(actor);
		stage.setScene(actorEditor.getScene());
	}

	public void saveGame() {

	}

	public void initLevelEditingEnvironment() {
		levelEditor = new LevelEditor();
	}

	public void initActorEditingEnvironment() {
		actorEditor = new ActorEditor();
	}

}
