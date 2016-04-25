package gamedata.view;

import java.io.File;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import gui.view.ComboBoxGame;
import gui.view.Screen;
import javafx.scene.control.Alert;
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
		//TODO fix magic strings and constants!
		File directory = new File("gamefiles");
			ComboBoxGame fileSelector = new ComboBoxGame("Choose Game", "gamefiles");
			fileSelector.addNodeObserver(this);
			HBox myBox = (HBox) fileSelector.createNode();
			myBox.setLayoutX(SCREEN_WIDTH/2 - 100);
			myBox.setLayoutY(SCREEN_HEIGHT/2);
			getRoot().getChildren().add(myBox);
		
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		Object[] methodArgPair = {o, arg};
		notifyObservers(Arrays.asList(methodArgPair));
	}


}
