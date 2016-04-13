package gamedata.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import gamedata.view.FileChooserScreen;
import gameengine.controller.Game;
import gui.controller.ScreenController;
import gui.view.Screen;
import javafx.stage.Stage;

public class FileChooserController extends ScreenController {

	private ResourceBundle myResources;
	private FileChooserScreen myFC;
	private Game myGame;
	
	public FileChooserController(Stage myStage, FileChooserScreen fc, ResourceBundle myBundle) {
		super(myStage);
		this.myResources = myBundle;
		this.myFC = fc;
	}

	@Override
	public void init()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stage getStage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGame(Game game) {
		this.myGame = game;
		
	}

	@Override
	public void chooseGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Screen getScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void useGame() {
		myFC.use(myGame);
	}

	@Override
	public void switchGame() {
		// TODO Auto-generated method stub
		
	}
	
	

}
