package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
/**
 * 
 * @author AnnieTang
 *
 */
public class ChangeSceneBehavior extends ComboBoxBehavior{
	private Controller myController;
	
	public ChangeSceneBehavior(String behaviorType, ResourceBundle myResources, Controller myController) {
		super(behaviorType, myResources);
		this.myController = myController;
	}

	@Override
	protected List<String> getOptionsList() {
		return myController.getLevelNames();
	}

}
