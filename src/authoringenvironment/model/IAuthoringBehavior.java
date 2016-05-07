package authoringenvironment.model;

import gui.view.IGUIElement;

/**
 * IAuthoringBehavior interface
 * @author annietang
 *
 */
public interface IAuthoringBehavior extends IGUIElement {

	/**
	 * Sets trigger or action.
	 */
	void setTriggerOrAction();

	/**
	 * Is trigger.
	 * @return true if it is a trigger; false o.w.
	 */
	boolean isTrigger();
	
	/**
	 * Update the displayed value based on the editable's set value.
	 */
	void updateValueBasedOnEditable();
	
	/**
	 * Set the value for the behavior.
	 */
	void setValue();

}