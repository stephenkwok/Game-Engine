package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.scene.Node;

public class CollisionBehavior extends ComboBoxBehavior {
	Controller myController;
	
	public CollisionBehavior(String behaviorType, ResourceBundle myResources,Controller myController) {
		super(behaviorType, myResources);
		this.myController = myController;
	}

	@Override
	protected List<String> getOptionsList() {
		return myController.getActorNames();
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}

	@Override
	public Node createNode() {
		// TODO Auto-generated method stub
		return null;
	}

}
