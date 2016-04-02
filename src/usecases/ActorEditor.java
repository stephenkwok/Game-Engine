package usecases;

import authoringenvironment.model.ActorEditingEnvironment;
import gameengine.model.IActor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

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
	public void setActor(IActor actor) {
		this.actor = actor;
	}

	@Override
	public Pane getPane() {
		// TODO Auto-generated method stub
		return null;
	}

}
