package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;


public class MBar extends MenuBarParent{

	private BaseScreenController myRealController;
	
	public MBar(IScreenController myController) {
		super(myController);
		myRealController = (BaseScreenController) myController;
	}


}