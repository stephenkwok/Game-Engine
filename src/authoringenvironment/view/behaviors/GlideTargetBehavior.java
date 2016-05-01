package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.Actions.GlideTarget;
import gameengine.model.Triggers.CollisionTrigger;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class GlideTargetBehavior extends SelectActorBehavior {
	private static final String OFFSET_PROMPT = "Enter offset";
	private IAction myAction;
	private TextField offset;
	
	public GlideTargetBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources,
			IAuthoringActor myActor, List<IAuthoringActor> myActors) {
		super(myRule, myActorRule, behaviorType, myResources, myActor, myActors);
	}

	@Override
	public Node createNode() {
		HBox hbox = (HBox) super.createNode();
		offset = new TextField(OFFSET_PROMPT);
		hbox.getChildren().add(offset);
		return hbox;
	}
	
	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(getMyActor());
		arguments.add(Double.parseDouble(offset.getText()));
		arguments.add(getOtherActor());
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

	@Override
	public void updateValueBasedOnEditable() {
		try{
			getComboBox().setValue((IEditableGameElement) (((CollisionTrigger) getMyRule().getMyTrigger()).getMyCollisionActor()));
			offset.setText(""+((GlideTarget) getMyRule().getMyAction()).getGlideOffset());
		}catch(Exception e){
		}
	}

}
