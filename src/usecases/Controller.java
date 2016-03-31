package usecases;

import java.util.ArrayList;
import java.util.List;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.controller.MainScreen;
import authoringenvironment.model.ICreatedLevel;
import gameengine.controller.ILevel;
import gameengine.model.IActor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	private List<IActor> createdActors;
	private List<ILevel> createdLevels;

//	public Controller (Stage stage) {
//		this.stage = stage;
//		this.stage.setScene(mainScreenScene);
//	}
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		initMainScreen();
		initLevelEditingEnvironment();
		initActorEditingEnvironment();
		createdActors = new ArrayList<IActor>();
		createdLevels = new ArrayList<ILevel>();
		this.stage = stage;
		stage.setTitle(TITLE);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
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
		HBox topBar = new HBox();
		topBar.getChildren().add(addActorButton);
		topBar.getChildren().add(addLevelButton);
		borderPane.setTop(topBar);
		root.getChildren().add(borderPane);
		mainScreenScene = new Scene(root);
	}

	public void show() {
		stage.show();
	}
	
	/**
	 * Instantiates new actor to be edited and switches scene to ActorEditingEnvironment
	 */
	private void addActor() {
		int actorID = createdActors.size() + 1;
		IActor newActor = new Actor(actorID, null); // take out null later
		createdActors.add(newActor);
		goToActorEditing(newActor);
	}
	
	/**
	 * Instantiates new level to be edited and switches scene to LevelEditingEnvironment
	 */
	private void addLevel() {
		ILevel newLevel = new Level(null); // take out null later
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
	 *            to be edited
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
