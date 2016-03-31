package usecases;

import authoringenvironment.controller.ActorEditingEnvironment;
import authoringenvironment.model.ICreatedActor;
import javafx.scene.Group;
import javafx.scene.Scene;

public class ActorEditor implements ActorEditingEnvironment {

	private ICreatedActor actor;
	private Scene scene;
	private Group root;
	
	public ActorEditor() {

	}

	@Override
	public void initializeEnvironment() {
		root = new Group();
		scene = new Scene(root);
	}

	@Override
	public void setActor(ICreatedActor actor) {
		this.actor = actor;
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
