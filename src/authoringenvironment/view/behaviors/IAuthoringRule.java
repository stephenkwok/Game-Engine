package authoringenvironment.view.behaviors;

import gameengine.model.IAction;
import gameengine.model.ITrigger;

public interface IAuthoringRule {
	
	IAction getAction();
	
	ITrigger getTrigger();
	
}
