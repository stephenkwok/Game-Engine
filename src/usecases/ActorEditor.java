package usecases;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.model.IEditingEnvironment;
import gameengine.model.IActor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

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
	public Pane getPane() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEditable(IEditableGameElement editable) {
		
	}

}
