package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.Triggers.ITrigger;
import gui.view.IGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Label;

public abstract class LabelBehavior implements IGUIElement, IAuthoringRule{
	private String behaviorType;
	private TriggerFactory triggerFactory;
	private ActionFactory actionFactory;
	
	public LabelBehavior(String behaviorType, ResourceBundle myResources) {
		this.behaviorType = behaviorType;
		this.triggerFactory = new TriggerFactory();
		this.actionFactory = new ActionFactory();
	}

	@Override
	public Node createNode() {
		createTriggerOrAction();
		return new Label(behaviorType);
	}
	
	protected String getBehaviorType(){
		return this.behaviorType;
	}
	
	protected TriggerFactory getTriggerFactory(){
		return this.triggerFactory;
	}
	
	protected ActionFactory getActionFactory(){
		return this.actionFactory;
	}
	
	abstract void createTriggerOrAction();

	@Override
	public abstract IAction getAction();

	@Override
	public abstract ITrigger getTrigger();
}
