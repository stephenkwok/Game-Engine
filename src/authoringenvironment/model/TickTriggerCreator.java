package authoringenvironment.model;

import java.util.ResourceBundle;

import gameengine.model.IGameElement;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.KeyTrigger;
import gameengine.model.Triggers.TickTrigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TickTriggerCreator extends VBox implements ITriggerCreator {
	private static final double SPACING = 20;
	private TextField myTickTextField;
	private ResourceBundle myResources;
	
	public TickTriggerCreator(ResourceBundle resources, IGameElement element) {
		myResources = resources;
		this.setSpacing(SPACING);
		init();
	}
	
	private void init() {
		HBox container = new HBox();
		Label label = new Label(myResources.getString("TickLabelText"));
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
