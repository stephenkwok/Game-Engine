package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.view.ActorRule;
import gameengine.model.Triggers.ITrigger;
import javafx.scene.input.KeyCode;

/**
 * GUI representation of PressKey behavior, which requires single input in
 * ComboBox form
 * 
 * @author AnnieTang
 */
public class KeyBehavior extends ComboBoxBehavior {
	private ITrigger myTrigger;

	public KeyBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myActorRule, behaviorType, myResources);
	}

	/**
	 * Return possible KeyInputs as options for ComboBox
	 * 
	 * @return
	 */
	@Override
	protected List<String> getOptionsList() {
		return Arrays.asList(getResources().getString("KeyInputs").split(" "));
	}

	@Override
	public void updateValueBasedOnEditable() {
//		getComboBox().setValue(getValue());
	}

	@Override
	public void setTriggerOrAction() {
		setTrigger(this, myTrigger);
	}

	@Override
	protected void createTriggerOrAction() {
		if (myTrigger != null) {
			// remove this trigger from ActorRule
		}
		KeyCode keyCode = KeyCode.getKeyCode(getValue());
		List<Object> arguments = new ArrayList<>();
		arguments.add(keyCode);
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
		setTriggerOrAction();
	}

	@Override
	public boolean isTrigger() {
		return true;
	}
}