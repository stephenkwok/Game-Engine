package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.ActionFactory;
import authoringenvironment.model.ActorRule;
import authoringenvironment.model.IAuthoringBehavior;
import authoringenvironment.model.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.IRule;
import gameengine.model.Triggers.Trigger;
import gui.view.ComboBoxTextCell;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

/**
 * @author AnnieTang
 */
public abstract class ComboBoxBehavior extends ComboBoxTextCell implements IAuthoringBehavior {
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final String HEADER = "Failed to set rules.";
	private static final String CONTENT = "Please select a value for each field.";
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
	@Override
	public Node createNode(){
		HBox toReturn = (HBox) super.createNode();
		int index = toReturn.getChildren().size();
		toReturn.getChildren().remove(index-1);
		return toReturn;
	}

	/**
	 * On click, set general field to ComboBox content
	 */
	@Override
	public void setButtonAction() {
	}
	
	public void setValue(){
		this.value = getComboBox().getValue();
		if(value == null) showAlert(HEADER,CONTENT);
		createTriggerOrAction();
	}
	
	private void showAlert(String alertHeader, String alertContent) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(alertHeader);
		alert.setContentText(alertContent);
		alert.showAndWait();
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

	protected void setTrigger(IAuthoringBehavior key, Trigger value) {
		myActorRule.setTrigger(key, value);
	}

	protected void setAction(IAuthoringBehavior key, IAction value) {
		myActorRule.setAction(key, value);
	}
	
	protected IRule getMyRule(){
		return this.myRule;
	}
}