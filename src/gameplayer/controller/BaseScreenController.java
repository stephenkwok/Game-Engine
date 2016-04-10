package gameplayer.controller;

import java.io.File;
import java.util.ResourceBundle;

import gameplayer.view.BaseScreen;
import gui.controller.IScreenController;
import javafx.stage.Stage;

public class BaseScreenController implements IScreenController{

	private Stage myStage;
	private ResourceBundle myResources;
	private BaseScreen myScreen;
	
	public BaseScreenController(Stage myStage, BaseScreen myBase, ResourceBundle myResources) {
		this.myStage = myStage;
		this.myResources = myResources;
		this.myScreen = myBase;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stage getStage() {
		return myStage;
	}

	public void toggleSound() {
		System.out.println("toggle sound");
	}
	
	public void toggleMusic(){
		System.out.println("toggle music");
	}

	public void togglePause() {
		System.out.println("pause the game");
	}

	public void toggleUnPause() {
		System.out.println("un pause game");
	}

	@Override
	public void createGameFromFile(File file) {
		// TODO Auto-generated method stub
		
	}

}