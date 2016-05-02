package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IRule;
import gameengine.model.Triggers.CollisionTrigger;
import gameengine.model.Triggers.ITrigger;
import gui.view.CheckBoxObject;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

public class CollisionBehavior extends SelectActorBehavior {
	private static final String APPLY_ONCE = "Only apply once";
	private static final int CHECK_WIDTH = 200;
	private ITrigger myTrigger;
	private CheckBox myCheckBox;


	public CollisionBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources, 
			IAuthoringActor myActor, List<IAuthoringActor> myActors) {
		super(myRule, myActorRule, behaviorType, myResources, myActor, myActors);
	}

	@Override
	public void setTriggerOrAction() {
		setTrigger(this, myTrigger);
	}
	
	@Override
	public Node createNode() {
		HBox hbox = (HBox) super.createNode();
		myCheckBox = (CheckBox) new CheckBoxObject(APPLY_ONCE,CHECK_WIDTH).createNode();
		hbox.getChildren().add(2,myCheckBox);
		return hbox;
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(getMyActor());
		arguments.add(getOtherActor());
		arguments.add(myCheckBox.isSelected());
		myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);
	}

	@Override
	public boolean isTrigger() {
		return true;
	}

	@Override
	public void updateValueBasedOnEditable() {
		try{
			getComboBox().setValue((IEditableGameElement) (((CollisionTrigger) getMyRule().getMyTrigger()).getMyCollisionActor()));
			myCheckBox.setSelected(((CollisionTrigger) getMyRule().getMyTrigger()).isOneTime());
		}catch(Exception e){
		}
	}
}
