package gui.controller;

import java.lang.reflect.InvocationTargetException;

import gameengine.controller.Game;
import gui.view.Screen;
import javafx.stage.Stage;

public interface IScreenController {
	
	
	public void init () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	@Deprecated
	public Stage getStage ();

	@Deprecated
	public void setGame(Game game);
	
	public void chooseGame();
	
	@Deprecated
	public Screen getScreen();
	
	@Deprecated
	public void useGame();

	@Deprecated
	public void goToSplash();
	
	@Deprecated
	public void switchGame();
}
