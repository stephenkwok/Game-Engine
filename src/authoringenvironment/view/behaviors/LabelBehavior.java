package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import authoringenvironment.view.ActionFactory;
import authoringenvironment.view.ActorRule;
import authoringenvironment.view.TriggerFactory;
import gameengine.model.IAction;
import gameengine.model.ITrigger;
import gui.view.IGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Label;

public abstract class LabelBehavior implements IGUIElement, IAuthoringRule{
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
	public void addTrigger(IAuthoringRule key, ITrigger value){
		myActorRule.addTrigger(key, value);
	}
	
	@Override
	public void addAction(IAuthoringRule key, IAction value){
		System.out.println(key);
		myActorRule.addAction(key, value);
	}
}
