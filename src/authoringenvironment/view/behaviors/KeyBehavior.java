package authoringenvironment.view.behaviors;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
/**
 * GUI representation of PressKey behavior, which requires single input in ComboBox form
 * @author AnnieTang
 */
public class KeyBehavior extends ComboBoxTextBehavior {
	private ResourceBundle myResources;
	
	public KeyBehavior(String behaviorType, ResourceBundle myResources) {
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
