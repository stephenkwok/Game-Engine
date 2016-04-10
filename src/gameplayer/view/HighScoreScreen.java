package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import gameplayer.controller.HighScoreScreenController;
import gui.controller.IScreenController;
import gui.view.GUIFactory;
import gui.view.Screen;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HighScoreScreen extends Screen{

	private ResourceBundle myResources;
	private static final String GUI_RESOURCE = "hsGUI";
	private HighScoreScreenController myController;
	private GUIFactory factory;
	
	public HighScoreScreen(Stage s) {
		super(s);
		initialize();
	}
	
	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new HighScoreScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}

	public void initialize(){
		Button b = new Button("Back");
		getRoot().getChildren().add(b);
		b.setOnAction(e -> returnToSplash());
	}
	
	public void returnToSplash(){
		SplashScreen mySplash = new SplashScreen(getStage());
		try {
			getStage().setScene(mySplash.getScene());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Scene getScene()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IScreenController setController() {
		// TODO Auto-generated method stub
		return null;
	}

}
