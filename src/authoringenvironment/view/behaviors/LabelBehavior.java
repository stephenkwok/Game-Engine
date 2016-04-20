package authoringenvironment.view.behaviors;

import java.util.ResourceBundle;

import authoringenvironment.view.TriggerFactory;
import gui.view.EditingElementParent;
import gui.view.IGUIElement;
import javafx.scene.Node;
import javafx.scene.control.Label;

public abstract class LabelBehavior implements IGUIElement{
	private String behaviorType;
	private TriggerFactory triggerFactory;
	
	public LabelBehavior(String behaviorType, ResourceBundle myResources) {
		this.behaviorType = behaviorType;
		this.triggerFactory = new TriggerFactory();
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
	
	abstract void createTriggerOrAction();
}
