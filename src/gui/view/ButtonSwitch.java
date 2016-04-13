package gui.view;

import java.io.File;

import gamedata.view.FileChooserScreen;
import gameplayer.controller.BaseScreenController;
import gui.controller.IScreenController;
import javafx.scene.control.Alert;

public class ButtonSwitch extends ButtonParent {
	
	private BaseScreenController myController;

	public ButtonSwitch(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myController = (BaseScreenController) myController;
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
				FileChooserScreen myFC = new FileChooserScreen(myController.getStage());
				try {
					myController.getStage().setScene(myFC.getScene());
				} catch (Exception e1) {
					// DO NOT LEAVE THIS ISH!
					e1.printStackTrace();
				}
			}
		});

	}

}
