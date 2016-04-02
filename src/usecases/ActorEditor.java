package usecases;

import authoringenvironment.model.ActorEditingEnvironment;
import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.IActor;
import javafx.scene.Group;
import javafx.scene.Scene;

public class ActorEditor implements IEditingEnvironment {

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
	public void setEditable(IEditableGameElement editable) {
		
	}

}
