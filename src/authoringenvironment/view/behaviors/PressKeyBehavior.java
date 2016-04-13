package authoringenvironment.view.behaviors;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PressKeyBehavior extends ComboBoxBehavior {
	private ResourceBundle myResources;
	
	public PressKeyBehavior(String behaviorType, ResourceBundle myResources) {
		super(behaviorType, myResources);
		this.myResources = myResources;
	}
	/**
	 * Return possible KeyInputs as options for ComboBox
	 * @return
	 */
	@Override
	protected List<String> getOptionsList() {
		return Arrays.asList(myResources.getString("KeyInputs").split(" "));
	}

	@Override
	protected void updateValueBasedOnEditable() {
	}

}
