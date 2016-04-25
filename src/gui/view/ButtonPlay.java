package gui.view;

import javafx.scene.control.Alert;
import java.io.File;


public class ButtonPlay extends ButtonParent{

	public ButtonPlay(String buttonText, String imageName) {
		super(buttonText, imageName);
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnAction(e -> {
			//TODO Add a checker for null directory
			//strings should come from a resource bundle
			//why two checks??
				setChanged();
				notifyObservers("ButtonPlay");
		});

	}

}