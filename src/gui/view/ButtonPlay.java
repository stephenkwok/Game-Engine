package gui.view;

import gui.controller.IScreenController;
import javafx.scene.control.Alert;

import java.io.File;

import gamedata.view.FileChooserScreen;
import gameplayer.controller.SplashScreenController;

public class ButtonPlay extends ButtonParent{

	private SplashScreenController myControl; 

	public ButtonPlay(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (SplashScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		button.setOnAction(e -> {
			//TODO Add a checker for null directory
			if ((new File("gamefiles")).listFiles().length == 1) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("You have to create a game to play first! Press edit!");
				alert.showAndWait();
			}
			else {
				FileChooserScreen myFC = new FileChooserScreen(myControl.getStage());
				try {
					myControl.getStage().setScene(myFC.getScene());
				} catch (Exception e1) {
					// DO NOT LEAVE THIS ISH!
					e1.printStackTrace();
				}
			}
		});

	}

}