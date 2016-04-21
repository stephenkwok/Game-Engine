package authoringenvironment.view.behaviors;

import gameengine.model.IAction;
import gameengine.model.ITrigger;
import gui.view.IGUIElement;

public interface IAuthoringRule extends IGUIElement {
	
	IAction getAction();
	
	ITrigger getTrigger();
	
}
