package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.ITriggerCreator;
import gameengine.model.IGameElement;
import gameengine.model.Triggers.ITrigger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AttributeReachedTriggerCreator extends VBox implements ITriggerCreator {
	private IGameElement myElement;
	private ResourceBundle myResources;
	private ComboBox myElementComboBox;
	
	public AttributeReachedTriggerCreator(ResourceBundle resources, IGameElement element) {
		myElement = element;
		myResources = resources;
		init();
	}

	private void init() {
		String[] labelText = myResources.getString("AttributeReachedLabelText").split(",");
		Label[] labels = new Label[labelText.length];
		for (int i = 0; i < labelText.length; i++) {
			labels[i] = new Label(labelText[i]);
		}
		
	}
	
	@Override
	public ITrigger createTrigger() {
		// TODO Auto-generated method stub
		return null;
	}

}
