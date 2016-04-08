package gameplayer.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import gui.view.MenuParent;

public class MenuHome extends MenuParent{
	
	private BaseScreenController myRealController;

	public MenuHome(IScreenController myController, String text) {
		super(myController, text);
		myRealController = (BaseScreenController) myController;
	}

}