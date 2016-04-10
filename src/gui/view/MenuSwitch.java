package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;

public class MenuSwitch extends MenuParent{
	
	private BaseScreenController myRealController;

	public MenuSwitch(IScreenController myController, String text) {
		super(myController, text);
		myRealController = (BaseScreenController) myRealController;
	}

	@Override
	public void setOnButtonAction() {
		myItem.setOnAction(e -> myRealController.switchGame());
		
	}

}
