package authoringenvironment.model;

import gameengine.model.IGameElement;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.NextLevel;
import javafx.scene.layout.VBox;

public class NextLevelActionCreator extends VBox implements IActionCreator {
	private IGameElement myElement;
	
	public NextLevelActionCreator(IGameElement element) {
		myElement = element;
	}
	
	@Override
	public Action createAction() {
		return new NextLevel(myElement);
	}
	
}
