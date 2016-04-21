package gamedata.view;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import gui.view.ComboBoxGame;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
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
		addToolBar();
	}

	private void addToolBar() {
		String[] sideButtons = getResources().getString(FC_BUTTONS).split(",");
		ToolBar myT = new ToolBar();
		myT.setOrientation(Orientation.HORIZONTAL);
		myT.setMinWidth(SCREEN_WIDTH);
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = getFactory().createNewGUIObject(sideButtons[i]);
			newElement.addNodeObserver(this);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(getResources().getString(sideButtons[i]+ "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
			myB.setFocusTraversable(false);
		}
		getRoot().getChildren().add(myT);
	}

	private void addButton() {
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
