package gui.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.stage.Stage;

public interface IScreenController {
	
	public void init () throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public Stage getStage ();
}
