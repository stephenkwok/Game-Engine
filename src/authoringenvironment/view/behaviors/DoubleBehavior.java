package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import authoringenvironment.model.ActionFactory;
import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringBehavior;
import authoringenvironment.model.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.Triggers.ITrigger;
import gui.view.TextFieldWithButton;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

/**
 * GUI representation of behaviors that take in a single Double as a parameter
 * 
 * @author AnnieTang
 */

public abstract class DoubleBehavior extends TextFieldWithButton implements IAuthoringBehavior {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String WIDTH = "Width";
	private static final String HEADER = "Failed to set rules.";
	private static final String CONTENT = "Please enter a value for each field.";
	private Double value;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	private String behaviorType;
	private ActorRule myActorRule;
	private IRule myRule;

	public DoubleBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myResources.getString(behaviorType + LABEL), myResources.getString(behaviorType + PROMPT),
				Double.parseDouble(myResources.getString(behaviorType + WIDTH)));
		this.behaviorType = behaviorType;
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory();
		this.myActorRule = myActorRule;
		this.myRule = myRule;
		setButtonAction(event -> {
			setValue();
		});
	}
	
	@Override
	public Node createNode(){
		HBox toReturn = (HBox) super.createNode();
		int index = toReturn.getChildren().size();
		toReturn.getChildren().remove(index-1);
		return toReturn;
	}
	
	@Override
	public void setValue(){
		try{
			this.value = Double.parseDouble(getTextFieldInput());
			createTriggerOrAction();
		}catch(Exception e){
			showAlert(HEADER,CONTENT);
		}
	}
	
	private void showAlert(String alertHeader, String alertContent) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertContent);
		alert.showAndWait();
	}
	
	@Override
	public abstract void updateValueBasedOnEditable();

	/**
	 * Create ITrigger or IAction depending on type of behavior
	 */
	protected abstract void createTriggerOrAction();

	/**
	 * Add ITrigger or IAction to actor IRule
	 */
	public abstract void setTriggerOrAction();

	/**
	 * Return if this behavior is a trigger
	 */
	public abstract boolean isTrigger();

	public double getValue() {
		return value;
	}

	protected String getBehaviorType() {
		return this.behaviorType;
	}

	protected TriggerFactory getTriggerFactory() {
		return this.triggerFactory;
	}

	protected ActionFactory getActionFactory() {
		return this.actionFactory;
	}

	public void setTrigger(IAuthoringBehavior key, ITrigger value) {
		myActorRule.setTrigger(key, value);
	}

	public void setAction(IAuthoringBehavior key, IAction value) {
		myActorRule.setAction(key, value);
	}
	
	protected ActorRule getActorRule(){
		return this.myActorRule;
	}
	
	protected IRule getMyRule(){
		return this.myRule;
	}
}