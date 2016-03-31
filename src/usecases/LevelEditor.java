package usecases;

import java.util.List;

import authoringenvironment.controller.LevelEditingEnvironment;
import authoringenvironment.model.ICreatedActor;
import authoringenvironment.model.ICreatedLevel;
import javafx.scene.Group;
import javafx.scene.Scene;

public class LevelEditor implements LevelEditingEnvironment {

	private ICreatedLevel level;
	private List<ICreatedActor> actors;
	private Group root;
	private Scene scene;
	
	public LevelEditor(){
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeEnvironment() {
		root = new Group();
		scene = new Scene(root);	
	}

	@Override
	public void setLevel(ICreatedLevel level, List<ICreatedActor> actors) {
		this.level = level;
		this.actors = actors;
	}
	
	public Scene getScene() {
		return scene;
	}

}
