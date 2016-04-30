package authoringenvironment.model;

import gui.view.IGUIElement;

public interface IAuthoringBehavior extends IGUIElement {

	void setTriggerOrAction();

	boolean isTrigger();
	
	void updateValueBasedOnEditable();
	
	void setValue();

}