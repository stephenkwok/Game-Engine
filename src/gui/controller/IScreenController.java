package gui.controller;

import java.io.File;

import gameengine.controller.Game;
import gui.view.Screen;
import javafx.stage.Stage;

public interface IScreenController {
	
	public void init ();
	
	public Stage getStage ();

	public void useGame(Game game);
	
	public Screen getScreen();
}
