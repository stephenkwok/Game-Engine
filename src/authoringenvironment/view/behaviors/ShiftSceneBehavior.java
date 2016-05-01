package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IAction;
import gameengine.model.IRule;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class ShiftSceneBehavior extends ComboBoxBehavior {
	private static final int ZERO = 0;
	private IAuthoringActor myActor;
	private IAction myAction;
	private OffsetBehavior shiftAmount;

	public ShiftSceneBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
		shiftAmount = new OffsetBehavior(myRule, myActorRule, myActor, behaviorType, myResources);
	}

	/**
	 * Creates ComboBox Node.
	 */
	@Override
	public Node createNode() {
		HBox hbox = (HBox) super.createNode();
		HBox toAdd = (HBox) shiftAmount.createNode();
		toAdd.getChildren().remove(ZERO);
		hbox.getChildren().add(toAdd);
		return hbox;
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		arguments.add(getValue());
		arguments.add(shiftAmount.getValue());
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
		System.out.println("HERE: " + myAction);
	}

	@Override
	public void setTriggerOrAction() {
		setAction(this, myAction);

	}

	@Override
	public boolean isTrigger() {
		return false;
	}

	@Override
	protected List<String> getOptionsList() {
		return Arrays.asList(new String[] { "Up", "Down", "Right", "Left" });
	}

	@Override
	public void updateValueBasedOnEditable() {
		try {
			getComboBox().setValue((String) getMyRule().getMyAction().getParameters()[1]);
			shiftAmount.updateValueBasedOnEditable();
		} catch (Exception e) {
		}

	}

}
