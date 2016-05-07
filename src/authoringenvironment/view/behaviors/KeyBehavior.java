package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import gameengine.model.IRule;
import gameengine.model.Triggers.Trigger;
import gameengine.model.Triggers.KeyTrigger;
import javafx.scene.input.KeyCode;

/**
 * GUI representation of PressKey behavior, which requires single input in
 * ComboBox form
 * 
 * @author AnnieTang
 */
public class KeyBehavior extends ComboBoxBehavior {
	private Trigger myTrigger;

	public KeyBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
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
		try{
			getComboBox().setValue(((KeyTrigger) getMyRule().getMyTrigger()).getMyKey());
		}catch(Exception e){
		}
	}

	@Override
	public void setTriggerOrAction() {
		setTrigger(this, myTrigger);
	}

	@Override
	protected void createTriggerOrAction() {
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