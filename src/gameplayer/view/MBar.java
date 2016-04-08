package gameplayer.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import gui.view.MenuBarParent;


public class MBar extends MenuBarParent{

	private BaseScreenController myRealController;
	
	public MBar(IScreenController myController) {
		super(myController);
		myRealController = (BaseScreenController) myController;
	}


}