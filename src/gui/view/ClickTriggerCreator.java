package gui.view;

import java.util.ResourceBundle;

import authoringenvironment.model.ITriggerCreator;
import gameengine.model.IGameElement;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.ITrigger;
import javafx.scene.layout.VBox;

public class ClickTriggerCreator extends VBox implements ITriggerCreator{
	private IGameElement myElement;
	
	public ClickTriggerCreator(ResourceBundle resources, IGameElement element) {
		myElement = element;
	}
	
	@Override
	public ITrigger createTrigger() {
		return new ClickTrigger(myElement);
	}
}
