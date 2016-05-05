package authoringenvironment.view;

import gameengine.model.Actions.Action;

/**
 * ILevelActionCreator interface
 * @author amyzhao
 *
 */
public interface ILevelActionCreator {

	/**
	 * Create an action.
	 * @return action.
	 */
	public Action createAction();
	
}
