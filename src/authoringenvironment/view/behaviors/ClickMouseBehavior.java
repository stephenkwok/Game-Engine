package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;

public class ClickMouseBehavior extends ComboBoxBehavior {
	private Controller myController; 
	
	public ClickMouseBehavior(String behaviorType, ResourceBundle myResources, Controller myController) {
		super(behaviorType, myResources);
		this.myController = myController;
	}

	@Override
	protected List<String> getOptionsList() {
		return null; //TODO:
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}

}
