package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.IEditableGameElement;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.Actions.Spawn;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SpawnBehavior extends SelectActorBehavior {
	private IAction myAction;
	private TextField angleField;
	
	public SpawnBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources, 
			IAuthoringActor myActor, List<IAuthoringActor> myActors) {
		super(myRule, myActorRule, behaviorType, myResources, myActor, myActors);
	}
	
	@Override
	public Node createNode(){
		HBox hb = (HBox)super.createNode();
		Label angleLabel = new Label("Spawn at degree: ");
		angleField = new TextField();
		hb.getChildren().addAll(angleLabel,angleField);
		return hb;
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(getMyActor());
		arguments.add(getOtherActor());
		arguments.add(Double.parseDouble(angleField.getText()));
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
			getComboBox().setValue((IEditableGameElement) (((Spawn) getMyRule().getMyAction()).getMySpawnedActor()));
			angleField.setText(Double.toString((Double) ((Spawn)getMyRule().getMyAction()).getSpawnAngle()));
		}catch(Exception e){
		}
	}

}
