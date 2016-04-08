package gameplayer.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import gui.view.PaneParent;

public class PaneHUD extends PaneParent{
	
	private BaseScreenController myRealController;

	public PaneHUD(IScreenController myController) {
		super(myController);
		myRealController = (BaseScreenController) myRealController;
	}

}