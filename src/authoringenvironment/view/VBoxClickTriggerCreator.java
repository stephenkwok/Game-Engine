package authoringenvironment.view;

import java.util.ResourceBundle;

import gameengine.model.IGameElement;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.ITrigger;
import javafx.scene.layout.VBox;

/**
 * VBoxClickTriggerCreator
 * @author amyzhao
 *
 */
public class VBoxClickTriggerCreator extends VBox implements ILevelTriggerCreator{
	private IGameElement myElement;
	
	/**
	 * Constructor for VBoxClickTriggerCreator
	 * @param resources: resource bundle to use.
	 * @param element: game element to add triger to.
	 */
	public VBoxClickTriggerCreator(ResourceBundle resources, IGameElement element) {
		myElement = element;
	}
	
	@Override
	public ITrigger createTrigger() {
		return new ClickTrigger(myElement);
	}
}
