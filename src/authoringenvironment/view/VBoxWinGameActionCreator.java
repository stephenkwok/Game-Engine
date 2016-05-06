package authoringenvironment.view;

import gameengine.model.IGameElement;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.WinGame;
import javafx.scene.layout.VBox;

/**
 * VBoxWinGameActionCreator
 * @author amyzhao
 *
 */
public class VBoxWinGameActionCreator extends VBox implements ILevelActionCreator {
	private IGameElement myElement;
	
	/**
	 * Constructor for VBoxWinGameActionCreator.
	 * @param element: element to apply action to.
	 */
	public VBoxWinGameActionCreator(IGameElement element) {
		myElement = element;
	}
	
	@Override
	public Action createAction() {
		return new WinGame(myElement);
	}
}
