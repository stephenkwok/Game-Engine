package gameplayer.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import gui.view.MenuParent;

public class MenuSave extends MenuParent{
	
	private BaseScreenController myRealController;

	public MenuSave(IScreenController myController, String text) {
		super(myController, text);
		myRealController = (BaseScreenController) myController;
	}

}