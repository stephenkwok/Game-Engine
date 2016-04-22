package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.ActorRule;
import authoringenvironment.view.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.ITrigger;
import gui.view.ComboBoxTextCell;
/**
 * Parent abstract class for GUI representation of behaviors that take in a single parameter in ComboBox form
 * @author AnnieTang
 */ 
public abstract class ComboBoxBehavior extends ComboBoxTextCell implements IAuthoringRule{
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private String value;
	private String behaviorType;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	private ResourceBundle myResources;
	private ActorRule myActorRule; 
	
	public ComboBoxBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources){
		super(myResources.getString(behaviorType+PROMPT), myResources.getString(behaviorType+LABEL));
		this.behaviorType = behaviorType;
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory(); 
		this.myResources = myResources;
		this.myActorRule = myActorRule;
	}
	/**
	 * On click, set general field to ComboBox content
	 */
	@Override
	public void setButtonAction() {
		getComboButton().setOnAction(event->{
			this.value = (String) getComboBox().getValue();
			createTriggerOrAction();
		});
	}
	/**
	 * Create ITrigger or IAction depending on type of behavior
	 */
	abstract void createTriggerOrAction();
	/**
	 * Return list of elements in ComboBox
	 */
	@Override
	abstract protected List<String> getOptionsList();
	
	/**
	 * Return general field String
	 * @return
	 */
	public String getValue(){
		return this.value;
	}
	
	/**
	 * Return String of behavior type
	 * @return
	 */
	protected String getBehaviorType(){
		return this.behaviorType;
	}
	
	/**
	 * Gets the trigger factory. 
	 * @return factory
	 */
	protected TriggerFactory getTriggerFactory(){
		return this.triggerFactory;
	}
	
	/**
	 * Gets the action factory. 
	 * @return factory
	 */
	protected ActionFactory getActionFactory(){
		return this.actionFactory;
	}
	/**
	 * Get Resource Bundle
	 * @return
	 */
	protected ResourceBundle getResources(){
		return this.myResources;
	}
	
	@Override
	protected abstract void updateValueBasedOnEditable();
	
	@Override
	public void addTrigger(IAuthoringRule key, ITrigger value){
		myActorRule.addTrigger(key, value);
	}
	
	@Override
	public void addAction(IAuthoringRule key, IAction value){
		myActorRule.addAction(key, value);
	}
}
