package gui.controller;

import java.io.File;

import javafx.stage.Stage;

public interface IScreenController {
	
	public void init ();
	
	public Stage getStage ();

	public void createGameFromFile(File file);
}
