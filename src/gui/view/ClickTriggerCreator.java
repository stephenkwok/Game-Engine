package gui.view;

import java.util.ResourceBundle;

import gameengine.model.IGameElement;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.ITrigger;
import javafx.scene.layout.VBox;

public class ClickTriggerCreator extends VBox {
	private IGameElement myElement;
	
	public ClickTriggerCreator(ResourceBundle resources, IGameElement element) {
		myElement = element;
	}
	
	public ITrigger createTrigger() {
		return new ClickTrigger(myElement);
	}
}
