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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ShiftSceneBehavior extends ComboBoxBehavior {
	private static final String OFFSET = "Offset";
	private TextField offset;
	private IAction myAction;
	private IAuthoringActor myActor;
	
	public ShiftSceneBehavior(IAuthoringActor myActor, IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
		this.myActor = myActor;
	}
	
	@Override
	public Node createNode() {
		HBox hbox = (HBox) super.createNode();
		offset = new TextField(OFFSET);
		hbox.getChildren().add(offset);
		return hbox;
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		arguments.add(getValue());
		arguments.add(Double.parseDouble(offset.getText()));
		myAction = getActionFactory().createNewAction(getBehaviorType(), arguments);
		setTriggerOrAction();
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
			offset.setText(""+getMyRule().getMyAction().getParameters()[2]);
		} catch (Exception e) {
		}
	}

}
