package authoringenvironment.view;

import gameengine.model.Triggers.ITrigger;

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
	public ITrigger createTrigger();
}
