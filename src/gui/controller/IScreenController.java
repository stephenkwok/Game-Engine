package gui.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;

import gameengine.controller.Game;
import gui.view.Screen;
import javafx.stage.Stage;

public interface IScreenController {
	
	public void init () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public Stage getStage ();

	public void setGame(Game game);
	
	public void chooseGame();
	
	public Screen getScreen();

	public void useGame();
}
