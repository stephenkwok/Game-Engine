package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import gameengine.model.IAction;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SoundBehavior extends LabelBehavior {
	private static final int PADDING = 20;
	private String soundName;
	private IAction myAction;
	private IAuthoringActor myActor;
	
	public SoundBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources, String soundName, IAuthoringActor myActor) {
		super(myActorRule, behaviorType, myResources);
		this.soundName = soundName;
		this.myActor = myActor;
	}

	@Override
	public Node createNode() {
		HBox toReturn = new HBox(PADDING);
		toReturn.getChildren().addAll(super.createNode(), new Label(soundName));
		return toReturn;
	}
	
	@Override
	public void updateValueBasedOnEditable() {
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		arguments.add(soundName);
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
	}

	@Override
	public void setTriggerOrAction() {
		setAction(this, myAction);
	}

	@Override
	public boolean isTrigger() {
		return false;
	}

}
