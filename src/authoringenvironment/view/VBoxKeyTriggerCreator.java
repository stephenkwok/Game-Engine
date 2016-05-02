package authoringenvironment.view;

import java.util.ResourceBundle;

import gameengine.model.IGameElement;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.KeyTrigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VBoxKeyTriggerCreator extends VBox implements ILevelTriggerCreator {
	private static final String KEY_INPUT = "KeyInputs";
	private static final String LABEL_TEXT = "KeyLabelText";
	private static final String DELIMITER = " ";
	private static final double SPACING = 20;
	private ComboBox myKeyCodeComboBox;
	private ObservableList<String> myOptions;
	private ResourceBundle myResources;
	
	public VBoxKeyTriggerCreator(ResourceBundle resources, IGameElement element) {
		myResources = resources;
		this.setSpacing(SPACING);
		init();
	}
	
	private void init() {
		HBox container = new HBox();
		Label label = new Label(myResources.getString(LABEL_TEXT));
		myOptions = FXCollections.observableArrayList(myResources.getString(KEY_INPUT).split(DELIMITER));
		myKeyCodeComboBox = new ComboBox<>(myOptions);
		container.getChildren().addAll(label, myKeyCodeComboBox);
		this.getChildren().add(container);
		
	}
	
	@Override
	public ITrigger createTrigger() {
		if (myKeyCodeComboBox.getValue() != null) {
			return new KeyTrigger(KeyCode.getKeyCode((String) myKeyCodeComboBox.getValue()));
		} else {
			return null;
		}
	}
}
