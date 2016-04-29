package authoringenvironment.model;

import gameengine.model.IAction;
import gameengine.model.IGameElement;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.LoseGame;
import javafx.scene.layout.VBox;

public class LoseGameActionCreator extends VBox implements IActionCreator {
	private IGameElement myElement;
	
	public LoseGameActionCreator(IGameElement element) {
		myElement = element;
	}
	
	@Override
	public Action createAction() {
		return new LoseGame(myElement);
	}

}
