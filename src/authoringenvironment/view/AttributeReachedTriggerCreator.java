package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.ITriggerCreator;
import gameengine.model.IGameElement;
import gameengine.model.Triggers.ITrigger;
import javafx.scene.layout.VBox;

public class AttributeReachedTriggerCreator extends VBox implements ITriggerCreator {
	private IGameElement myElement;
	
	public AttributeReachedTriggerCreator(ResourceBundle resources, IGameElement element) {
		myElement = element;
	}

	@Override
	public ITrigger createTrigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
