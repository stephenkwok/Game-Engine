package usecases;

import java.util.List;

import authoringenvironment.controller.Controller;
import authoringenvironment.model.ActorEditingEnvironment;
import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;
import authoringenvironment.model.LevelEditingEnvironment;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Controller extends Application implements Controller {
	
	private Stage stage;
	private Scene mainScreenScene;
	private Group root;
	private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Main Screen";
    private LevelEditingEnvironment levelEditor;
    private ActorEditingEnvironment actorEditor;
    private ICreatedLevel currentLevel;
    private ICreatedActor currentActor;
    private BorderPane borderPane;
    private List<ICreatedActor> availableActors;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		initMainScreen();
		initLevelEditingEnvironment();
		initActorEditingEnvironment();
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
		addActorButton.setOnAction(e -> goToActorEditing(currentActor));
		Button addLevelButton = new Button("Add Level");
		addActorButton.setOnAction(e -> goToActorEditing(currentActor));
		addLevelButton.setOnAction(e -> goToLevelEditing(currentLevel, availableActors));
		HBox topBar = new HBox();
		topBar.getChildren().add(addActorButton);
		topBar.getChildren().add(addLevelButton);
		borderPane.setTop(topBar);
		root.getChildren().add(borderPane);
		mainScreenScene = new Scene(root);
	}

	@Override
	public void show() {
		stage.show();
	}

	/**
	 * 
	 * When the user presses a button to add a new level, the LevelEditor is passed the level
	 * to be edited and a list of Actors that can be added to that level. The stage is then
	 * set to the scene of the Level Editor
	 * 
	 * @param level to be edited
	 * @param createdActors that can be added to that level
	 */
	@Override
	public void goToLevelEditing(ICreatedLevel level, List<ICreatedActor> createdActors) {
		levelEditor.setLevel(level, createdActors);	
		stage.setScene(levelEditor.getScene());
	}

	/**
	 * 
	 * When the user presses a button to add a new actor, the ActorEditor is passed the actor
	 * to be edited. The stage is then set to the scene of the ActorEditor
	 * 
	 * @param actor to be edited
	 */
	@Override
	public void goToActorEditing(ICreatedActor actor) {
		actorEditor.setActor(actor);
		stage.setScene(actorEditor.getScene());
	}

	@Override
	public void saveGame() {
		
	}
	
	public void initLevelEditingEnvironment() {
		levelEditor = new LevelEditor();
	}
	
	public void initActorEditingEnvironment() {
		actorEditor = new ActorEditor();
	}

}
