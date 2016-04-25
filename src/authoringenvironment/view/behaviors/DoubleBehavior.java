package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.ActorRule;
import authoringenvironment.view.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.Triggers.ITrigger;
import gui.view.TextFieldWithButton;

/**
 * GUI representation of behaviors that take in a single Double as a parameter
 * 
 * @author AnnieTang
 */

public abstract class DoubleBehavior extends TextFieldWithButton implements IAuthoringBehavior {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String WIDTH = "Width";
	private double value;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	private String behaviorType;
	private ActorRule myActorRule;

	public DoubleBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myResources.getString(behaviorType + LABEL), myResources.getString(behaviorType + PROMPT),
				Double.parseDouble(myResources.getString(behaviorType + WIDTH)));
		this.behaviorType = behaviorType;
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory();
		this.myActorRule = myActorRule;
		setButtonAction(event -> {
			this.value = Double.parseDouble(getTextFieldInput());
			createTriggerOrAction();
		});
	}

	@Override
	protected void updateValueBasedOnEditable() {
		setTextFieldValue(String.valueOf(value));
	}

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
}
