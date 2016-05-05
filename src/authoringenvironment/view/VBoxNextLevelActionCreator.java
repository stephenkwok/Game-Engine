package authoringenvironment.view;

import gameengine.model.IGameElement;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.NextLevel;
import javafx.scene.layout.VBox;

/**
 * VBoxNextLevelActionCreator
 * @author amyzhao
 *
 */
public class VBoxNextLevelActionCreator extends VBox implements ILevelActionCreator {
	private IGameElement myElement;
	
	/**
	 * Constructor for VBoxNextLevelActionCreator.
	 * @param element: element to apply action to.
	 */
	public VBoxNextLevelActionCreator(IGameElement element) {
		myElement = element;
	}
	
	@Override
	public Action createAction() {
		return new NextLevel(myElement);
	}
	
}
