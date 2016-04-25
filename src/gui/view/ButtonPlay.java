package gui.view;

import javafx.scene.control.Alert;
import java.io.File;
import java.util.Arrays;


public class ButtonPlay extends ButtonParent{

	public ButtonPlay(String buttonText, String imageName) {
		super(buttonText, imageName);
		setName(this.getClass().getSimpleName());
		setClick();
	}

	@Override
	protected void setButtonAction() {
		getButton().setOnMouseClicked(e -> {
			//TODO Add a checker for null directory
			if ((new File("gamefiles")).listFiles().length == 1) {
				
			}
			else{
				
			}
		});
	}

}