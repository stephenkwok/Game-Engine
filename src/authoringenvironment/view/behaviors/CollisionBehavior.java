package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;

public class CollisionBehavior extends ComboBoxBehavior {
	Controller myController;
	
	public CollisionBehavior(String behaviorType, ResourceBundle myResources,Controller myController) {
		super(behaviorType, myResources);
		this.myController = myController;
	}
	/**
	 * Return list of Actor names to be set as ComboBox options
	 */
	@Override
	protected List<String> getOptionsList() {
		return myController.getActorNames();
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}

}
