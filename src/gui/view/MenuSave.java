package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;

public class MenuSave extends MenuParent{
	
	private BaseScreenController myRealController;

	public MenuSave(IScreenController myController, String text) {
		super(myController, text);
		myRealController = (BaseScreenController) myController;
	}

}