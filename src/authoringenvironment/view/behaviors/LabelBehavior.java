package authoringenvironment.view.behaviors;

import java.util.Observer;
import java.util.ResourceBundle;

import authoringenvironment.model.ActionFactory;
import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringBehavior;
import authoringenvironment.model.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.Triggers.ITrigger;
import gui.view.IGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Label;

public abstract class LabelBehavior implements IGUIElement, IAuthoringBehavior {
	private String behaviorType;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	private ActorRule myActorRule;

	public LabelBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		this.behaviorType = behaviorType;
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory();
		this.myActorRule = myActorRule;
	}

	@Override
	public Node createNode() {
		setValue();
		return new Label(behaviorType);
	}
	
	public void setValue(){
		createTriggerOrAction();
		setTriggerOrAction();
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
	
	@Override
	public void addNodeObserver(Observer observer) {
	}
}