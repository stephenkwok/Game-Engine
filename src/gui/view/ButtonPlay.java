package gui.view;

import gui.controller.IScreenController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import gameplayer.controller.SplashScreenController;

public class ButtonPlay extends ButtonParent{

	private SplashScreenController myControl; 
	
	public ButtonPlay(IScreenController myController, String buttonText, String imageName) {
		super(myController, buttonText, imageName);
		this.myControl = (SplashScreenController) myController;
	}

	@Override
	protected void setButtonAction() {
		//button.setOnAction(e ->  myControl.play());
		
		button.setOnAction(e -> {
			Group fileChooseGroup = new Group();
			Scene fileChooseScene = new Scene(fileChooseGroup);
			ComboBoxGame fileSelector =  new ComboBoxGame("SUP", "gamefiles", myController);
			fileChooseGroup.getChildren().add((HBox) fileSelector.createNode());
			myControl.getStage().setScene(fileChooseScene);
		});

	}

}