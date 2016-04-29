package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	private int CHECK_WIDTH = 90;
	private ITrigger myTrigger;
	private boolean oneTime;

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
		CheckBox checkBox = (CheckBox) new CheckBoxObject("Apply 1x",CHECK_WIDTH).createNode();
		checkBox.setOnAction(e->{
			oneTime = checkBox.isSelected();
		});
		hbox.getChildren().add(2,checkBox);
		return hbox;
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(getMyActor());
		arguments.add(getOtherActor());
		arguments.add(oneTime);
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
			
		}catch(Exception e){
		}
	}
}
