package gameplayer.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import gui.view.MenuParent;

public class MenuSwitch extends MenuParent{
	
	private BaseScreenController myRealController;

	public MenuSwitch(IScreenController myController, String text) {
		super(myController, text);
		myRealController = (BaseScreenController) myRealController;
	}

}
