package authoringenvironment.view.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActionFactory;
import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringActor;
import authoringenvironment.model.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.IRule;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class ShiftSceneBehavior extends ComboBoxBehavior {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final int HBOX_SPACING = 5;
	private String promptText;
	private String labelText;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	private ActorRule myActorRule;
	private String behaviorType;
	private IAuthoringActor myActor;
	private IRule myRule;
	private IAction myAction;
	private OffsetBehavior shiftAmount;
	
	public ShiftSceneBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myRule, myActorRule, behaviorType, myResources);
		this.myRule = myRule;
		this.myActorRule = myActorRule;
		this.behaviorType = behaviorType;
		this.promptText = myResources.getString(behaviorType + PROMPT);
		this.labelText = myResources.getString(behaviorType + LABEL);
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory();
		this.hbox = new HBox(HBOX_SPACING);
		this.myResources = myResources;
		shiftAmount = new OffsetBehavior(myRule, myActorRule, myActor, behaviorType, myResources);
	}
	
	/**
	 * Creates ComboBox Node.
	 */
	@Override
	public Node createNode() {
		HBox hbox = (HBox) super.createNode();
		hbox.getChildren().add(shiftAmount.createNode());
		return hbox;
	}

	@Override
	protected void createTriggerOrAction() {
		List<Object> arguments = new ArrayList<>();
		arguments.add(myActor);
		arguments.add(getValue());
		arguments.add(shiftAmount.getValue());
		myAction = getActionFactory().createNewAction(behaviorType, arguments);

	}

	@Override
	public void setTriggerOrAction() {
		setAction(this,myAction);

	}

	@Override
	public boolean isTrigger() {
		return false;
	}

	@Override
	protected List<String> getOptionsList() {
		return Arrays.asList(new String[]{"Up","Down","Right","Left"});
	}

	@Override
	public void updateValueBasedOnEditable() {
		try{
			getComboBox().setValue((String) myRule.getMyAction().getParameters()[1]);
			shiftAmount.updateValueBasedOnEditable();
		}catch(Exception e){
		}

	}

}
