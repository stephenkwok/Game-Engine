package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gameengine.model.ITrigger;
import javafx.scene.input.KeyCode;
/**
 * GUI representation of PressKey behavior, which requires single input in ComboBox form
 * @author AnnieTang
 */
public class KeyBehavior extends ComboBoxTextBehavior {
	private ITrigger myTrigger;
	
	public KeyBehavior(String behaviorType, ResourceBundle myResources) {
		super(behaviorType, myResources);
	}
	/**
	 * Return possible KeyInputs as options for ComboBox
	 * @return
	 */
	@Override
	protected List<String> getOptionsList() {
		return Arrays.asList(getResources().getString("KeyInputs").split(" "));
	}

	@Override
	protected void updateValueBasedOnEditable() {
		getComboBox().setValue(getValue());
	}
	@Override
	void createTriggerOrAction() {
		KeyCode keyCode = KeyCode.getKeyCode(getValue());
		List<Object> arguments = new ArrayList<>();
		arguments.add(keyCode);
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
		//add ITrigger 
		System.out.println(myTrigger);
	}

}
