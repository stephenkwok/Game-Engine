package usecases;

import authoringenvironment.model.ActorEditingEnvironment;
import gameengine.model.IActor;
import javafx.scene.Group;
import javafx.scene.Scene;

public class ActorEditor implements ActorEditingEnvironment {

	private IActor actor;
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
	public Scene getScene() {
		return scene;
	}

	@Override
	public void setActor(IActor actor) {
		this.actor = actor;
	}

}
