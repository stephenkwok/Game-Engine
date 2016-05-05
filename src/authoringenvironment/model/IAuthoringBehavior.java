package authoringenvironment.model;

import gui.view.IGUIElement;
/**
 * This interface is implemented by GUI representations of ITriggers and IActions in the ActorEditingEnvironment. 
 * @author AnnieTang
 *
 */
public interface IAuthoringBehavior extends IGUIElement {
	/**
	 * Depending on if the IAuthoringBehavior is a Trigger or an Action, set this object in ActorRule.java 
	 */
	void setTriggerOrAction();
	/**
	 * Returns if this IAuthoringBehavior is a Trigger.
	 * @return
	 */
	boolean isTrigger();
	/**
	 * When returning to the Actor Editing Environment, updates any fields or ComboBoxes to 
	 * contain the relevant information for each actor.
	 */
	void updateValueBasedOnEditable();
	/**
	 * Set fields or ComboBoxes to contain the relevant information for each actor.
	 */
	void setValue();

}