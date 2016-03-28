package usecases;

import java.util.List;

import authoringenvironment.controller.MainScreen;
import authoringenvironment.model.IActor;
import authoringenvironment.model.ILevel;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;

public class MainScreenInstance implements MainScreen {
	
	Group root;
	BorderPane borderPane;

	public MainScreenInstance() {
		root = new Group();
		initializeBorderPane();
	}

	@Override
	public void show() {
		root.getChildren().add(borderPane);
	}
	
	public void hide() {
		root.getChildren().remove(borderPane);
	}

	@Override
	public void goToSceneEditing(ILevel level, List<IActor> createdActors) {
		
		
	}

	@Override
	public void goToActorEditing(IActor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
		
	}
	
	public void initializeBorderPane() {
		
	}

}
