package gamedata.view;

import java.io.File;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import gui.view.ComboBoxGame;
import gui.view.Screen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;

public class FileChooserScreen extends Screen implements Observer {

	private static final String CHOOSER_RESOURCE = "fcGUI";
	private static final String FC_BUTTONS = "FCButtons";

	public FileChooserScreen() {
		super();
		setUpResourceBundle(CHOOSER_RESOURCE);
		initialize();
	}

	@Override
	protected void initialize() {
		addButton();
		getRoot().getChildren().add(addToolbar(FC_BUTTONS));
	}

	private void addButton() {
		ComboBoxGame fileSelector = new ComboBoxGame(getResources().getString("Prompt"), getResources().getString("Directory"));
		fileSelector.addNodeObserver(this);
		HBox myBox = (HBox) fileSelector.createNode();
		myBox.setLayoutX(SCREEN_WIDTH/2 - 100);
		myBox.setLayoutY(SCREEN_HEIGHT/2);
		getRoot().getChildren().add(myBox);
		//TODO fix magic strings and constants!
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		// Object[] methodArgPair = {o, arg};
		// notifyObservers(Arrays.asList(methodArgPair));
		notifyObservers(arg);
	}

	//TODO should this alert happen here?? -michael wondering things
	public void alert(String type) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(getResources().getString(type));
		Optional<ButtonType> result = alert.showAndWait();
		if (type.equals("empty")) {
			if (result.get() == ButtonType.OK) {
				setChanged();
				Object[] methodArgPair = {"goToSplash", null};
				notifyObservers(Arrays.asList(methodArgPair));
			}
		}
		
	}

}