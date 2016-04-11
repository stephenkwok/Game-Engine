package gui.view;

import java.lang.reflect.InvocationTargetException;

import gameplayer.controller.SplashScreenController;
import gui.controller.IScreenController;

public class ButtonEdit extends ButtonParent{

	private SplashScreenController myControl;

	public ButtonEdit(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (SplashScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> { try { myControl.edit();
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}});

	}

}
