package authoringenvironment.view;

import gameengine.model.IGameElement;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.LoseGame;
import javafx.scene.layout.VBox;

public class VBoxLoseGameActionCreator extends VBox implements ILevelActionCreator {
	private IGameElement myElement;
	
	public VBoxLoseGameActionCreator(IGameElement element) {
		myElement = element;
	}
	
	@Override
	public Action createAction() {
		return new LoseGame(myElement);
	}

}
