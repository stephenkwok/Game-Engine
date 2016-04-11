package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;


public class MenuBarGame extends MenuBarParent{

	private BaseScreenController myRealController;
	
	public MenuBarGame(IScreenController myController) {
		super(myController);
		myRealController = (BaseScreenController) myController;
	}


}