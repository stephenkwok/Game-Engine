package gui.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;

import gameengine.controller.Game;
import gui.view.Screen;
import javafx.stage.Stage;

public interface IScreenController {
	
	public void init () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public Stage getStage ();

	public void createGameFromFile(Game game);
	
	public Screen getScreen();
}
