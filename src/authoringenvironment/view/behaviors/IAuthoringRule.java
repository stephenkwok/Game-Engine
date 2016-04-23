package authoringenvironment.view.behaviors;

import gameengine.model.IAction;
import gameengine.model.Triggers.ITrigger;

public interface IAuthoringRule {
	
	IAction getAction();
	
	ITrigger getTrigger();
	
}
