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
			if ((new File("gamefiles")).listFiles().length == 1) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("You have to create a game to play first! Press edit!");
				alert.showAndWait();
			}
			else {
				setChanged();
				notifyObservers("ButtonPlay");
			}
		});

	}

}