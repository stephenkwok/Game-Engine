package authoringenvironment.view;

import java.util.ResourceBundle;

import gameengine.model.IGameElement;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.TickTrigger;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * VBoxTickTriggerCreator
 * @author amyzhao
 *
 */
public class VBoxTickTriggerCreator extends VBox implements ILevelTriggerCreator {
	private static final double SPACING = 20;
	private static final String LABEL_TEXT = "TickLabelText";
	private TextField myTickTextField;
	private ResourceBundle myResources;
	
	/**
	 * Constructor for VBoxTickTriggerCreator
	 * @param resources: resource bundle to use.
	 * @param element: element to add trigger to.
	 */
	public VBoxTickTriggerCreator(ResourceBundle resources, IGameElement element) {
		myResources = resources;
		this.setSpacing(SPACING);
		init();
	}
	
	/**
	 * Initialize hbox for trigger creator.
	 */
	private void init() {
		HBox container = new HBox();
		Label label = new Label(myResources.getString(LABEL_TEXT));
		myTickTextField = new TextField();
		container.getChildren().addAll(label, myTickTextField);
		this.getChildren().add(container);
		
	}
	
	@Override
	public ITrigger createTrigger() {
		if (myTickTextField.getText() != null) {
			return new TickTrigger(Integer.parseInt(myTickTextField.getText()));
		} else {
			return null;
		}
	}
}
