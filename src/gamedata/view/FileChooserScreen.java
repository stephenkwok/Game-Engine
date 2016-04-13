package gamedata.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import gamedata.controller.FileChooserController;
import gameengine.controller.Game;
import gameplayer.controller.SplashScreenController;
import gameplayer.view.BaseScreen;
import gui.controller.IScreenController;
import gui.view.ComboBoxGame;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public abstract class FileChooserScreen extends Screen {

	private ResourceBundle myResources;
	private FileChooserController myController;
	private static final String GUI_RESOURCE = "fcGUI";
	private GUIFactory factory;
	private static final String FC_BUTTONS = "FCButtons";
	
	public FileChooserScreen(Stage myStage) {
		super(myStage);
		init();
	}

	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new FileChooserController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}

	@Override
	public Scene getScene()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		addButton();
		addToolBar();
		return getMyScene();
	}

	private void addToolBar() {
		String[] sideButtons = myResources.getString(FC_BUTTONS).split(",");
		ToolBar myT = new ToolBar();
		myT.setOrientation(Orientation.HORIZONTAL);
		myT.setMinWidth(SCREEN_WIDTH);
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = factory.createNewGUIObject(sideButtons[i]);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(myResources.getString(sideButtons[i]+ "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
			myB.setFocusTraversable(false);
		}
		getRoot().getChildren().add(myT);
	}

	private void addButton() {
		ComboBoxGame fileSelector = new ComboBoxGame("Choose Game", "gamefiles", myController);
		HBox myBox = (HBox) fileSelector.createNode();
		myBox.setLayoutX(SCREEN_WIDTH/2 - 100);
		myBox.setLayoutY(SCREEN_HEIGHT/2);
		getRoot().getChildren().add(myBox);
	}

	@Override
	public IScreenController setController() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public abstract void use(Game game);

}
