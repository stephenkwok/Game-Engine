package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IAuthoringBehavior;
import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.ActorRule;
import authoringenvironment.view.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.Triggers.ITrigger;
import gui.view.ComboBoxTextCell;

/**
 * @author AnnieTang
 */
public abstract class ComboBoxBehavior extends ComboBoxTextCell implements IAuthoringBehavior {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private String value;
	private String behaviorType;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	private ResourceBundle myResources;
	private ActorRule myActorRule;
	private IRule myRule;

	public ComboBoxBehavior(IRule myRule, ActorRule myActorRule, String behaviorType, ResourceBundle myResources) {
		super(myResources.getString(behaviorType + PROMPT), myResources.getString(behaviorType + LABEL));
		this.behaviorType = behaviorType;
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory();
		this.myResources = myResources;
		this.myActorRule = myActorRule;
		this.myRule = myRule;
	}

	/**
	 * On click, set general field to ComboBox content
	 */
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event -> {
			setValue();
		});
	}
	
	public void setValue(){
		this.value = (String) getComboBox().getValue();
		createTriggerOrAction();
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

	/**
	 * Return list of elements in ComboBox
	 */
	@Override
	abstract protected List<String> getOptionsList();

	/**
	 * Return general field String
	 * 
	 * @return
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Return String of behavior type
	 * 
	 * @return
	 */
	protected String getBehaviorType() {
		return this.behaviorType;
	}

	/**
	 * Gets the trigger factory.
	 * 
	 * @return factory
	 */
	protected TriggerFactory getTriggerFactory() {
		return this.triggerFactory;
	}

	/**
	 * Gets the action factory.
	 * 
	 * @return factory
	 */
	protected ActionFactory getActionFactory() {
		return this.actionFactory;
	}

	/**
	 * Get Resource Bundle
	 * 
	 * @return
	 */
	protected ResourceBundle getResources() {
		return this.myResources;
	}

	@Override
	public abstract void updateValueBasedOnEditable();

	protected void setTrigger(IAuthoringBehavior key, ITrigger value) {
		myActorRule.setTrigger(key, value);
	}

	protected void setAction(IAuthoringBehavior key, IAction value) {
		myActorRule.setAction(key, value);
	}
	
	protected IRule getMyRule(){
		return this.myRule;
	}
}