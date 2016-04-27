package authoringenvironment.view;

import java.util.ResourceBundle;

import gameengine.model.IGameElement;
import javafx.scene.layout.VBox;

public class AttributeReachedTriggerCreator extends VBox {
	private IGameElement myElement;
	
	public AttributeReachedTriggerCreator(ResourceBundle resources, IGameElement element) {
		myElement = element;
	}

}
