package gui.view;

import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;

public class MenuHome extends MenuParent{
	
	private BaseScreenController myRealController;

	public MenuHome(IScreenController myController, String text) {
		super(myController, text);
		myRealController = (BaseScreenController) myController;
		
	}

	@Override
	public void setOnButtonAction() {
		this.myItem.setOnMenuValidation(e -> myRealController.goToSplash());
	}
	

}