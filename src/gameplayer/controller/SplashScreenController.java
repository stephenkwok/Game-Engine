package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import gamedata.controller.ChooserType;
import gamedata.controller.FileChooserController;
import gameplayer.view.ISplashScreen;
import gameplayer.view.SplashScreen;
import javafx.stage.Stage;
import authoringenvironment.controller.Controller;

public class SplashScreenController implements Observer {

	private static final String SPLASH_CONTROLLER_RESOURCE = "splashActions";

	private ResourceBundle myResources;
	private ISplashScreen myScreen;
	private Stage myStage;

	public SplashScreenController(Stage stage) {
		this.myStage = stage;
		setUpScreen();
		this.myStage.setScene(myScreen.getScene());
		this.myResources = ResourceBundle.getBundle(SPLASH_CONTROLLER_RESOURCE);
	}

	private void setUpScreen() {
		this.myScreen = new SplashScreen();
		((Observable) this.myScreen).addObserver(this);
	}

	private void play() {
		FileChooserController fileChooserController = new FileChooserController(myStage, ChooserType.PLAY);
	}

	private void openHighScores() {
		FileChooserController fileChooserController = new FileChooserController(myStage, ChooserType.SCORES);
	}


	private void edit() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Controller GUIMainController = new Controller(myStage);
	}

	@Override
	public void update(Observable o, Object arg) {
		List<Object> myList = (List<Object>) arg;
		String methodName = (String) myList.get(0);
		try {
			if(myResources.getString(methodName).equals("null")){
				this.getClass().getDeclaredMethod(methodName).invoke(this);
			}
			else{
				Class<?> myClass = Class.forName(myResources.getString(methodName));
				Object arg2 = myClass.cast(myList.get(1));
				this.getClass().getDeclaredMethod(methodName).invoke(this, arg2);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			this.myScreen.showError(e.getMessage());
		}
	}

}