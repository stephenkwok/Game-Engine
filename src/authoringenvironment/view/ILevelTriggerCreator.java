package authoringenvironment.view;

import gameengine.model.Triggers.Trigger;

/**
 * Create a level trigger.
 * @author amyzhao
 *
 */
public interface ILevelTriggerCreator {
	
	/**
	 * Creates a trigger.
	 * @return trigger.
	 */
	public Trigger createTrigger();
}
