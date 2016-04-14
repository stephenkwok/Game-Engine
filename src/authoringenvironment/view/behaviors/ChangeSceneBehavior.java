package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.scene.Node;
/**
 * GUI representation of ChangeScene behavior, which requires single input in ComboBox form
 * @author AnnieTang
 */
public class ChangeSceneBehavior extends ComboBoxBehavior{
	private Controller myController;
	
	public ChangeSceneBehavior(String behaviorType, ResourceBundle myResources, Controller myController) {
		super(behaviorType, myResources);
		this.myController = myController;
	}
	/**
	 * Return list of Levels that user can change Scene to 
	 */
	@Override
	protected List<String> getOptionsList() {
		return myController.getLevelNames();
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
