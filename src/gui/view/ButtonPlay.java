package gui.view;

import gui.controller.IScreenController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;

import java.io.File;

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
				Group fileChooseGroup = new Group();
				Scene fileChooseScene = new Scene(fileChooseGroup, myControl.getScreen().getMyScene().getWidth(), myControl.getScreen().getMyScene().getHeight());
				ComboBoxGame fileSelector =  new ComboBoxGame("Choose Game", "gamefiles", myControl);
				fileChooseGroup.getChildren().add((HBox) fileSelector.createNode());
				myControl.getStage().setScene(fileChooseScene); 
			}
		});

	}

}