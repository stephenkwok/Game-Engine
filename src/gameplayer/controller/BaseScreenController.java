package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import gamedata.controller.CreatorController;
import gamedata.controller.ParserController;
import gameengine.controller.Game;
import gameplayer.view.BaseScreen;
import gameplayer.view.SplashScreen;
import gui.controller.ScreenController;
import gui.view.ComboBoxGame;
import gui.view.Screen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BaseScreenController extends ScreenController{

	private ResourceBundle myResources;
	private BaseScreen myScreen;
	private GameController myGameController;
	
	public BaseScreenController(Stage myStage, BaseScreen myBase, ResourceBundle myResources) {
		super(myStage);
		this.myResources = myResources;
		this.myScreen = myBase;
		this.setMyGameController(new GameController());
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	public void toggleSound() {
		System.out.println("toggle sound");
	}
	
	public void toggleMusic(){
		System.out.println("toggle music");
	}

	
	public void goToSplash(){
		SplashScreen mySplash = new SplashScreen(getStage());
		try {
			getStage().setScene(mySplash.getScene());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void saveGame(){
		this.getMyGameController().togglePause();
		try {
			CreatorController c = new CreatorController(getMyGameController().getGame(), this.myScreen);
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showSaveDialog(new Stage());
			c.saveForPlaying(file);
		} catch (ParserConfigurationException e) {
			myScreen.showError(e.getMessage());
		}
		
	}
	
	public void switchGame(){
		this.getMyGameController().togglePause();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, myResources.getString("SwitchConfirmation"), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            this.saveGame();
            this.chooseGame();
        }
        else if (result.get() == ButtonType.NO) {
        	this.chooseGame();
        }
        else {
        	this.getMyGameController().toggleUnPause();
        }
	}

	@Override
	public void setGame(Game game) {
		myGameController.setGame(game);
	}

	@Override
	public Screen getScreen() {
		return myScreen;
	}

	public GameController getMyGameController() {
		return myGameController;
	}

	public void setMyGameController(GameController myGameController) {
		this.myGameController = myGameController;
	}
	
	public void restartGame(){
		myGameController.getView().clearGame();
		ParserController parserController = new ParserController(myScreen);
		Game initialGame = parserController.loadforPlaying(new File(myGameController.getGame().getInitialGameFile()));
		myGameController.setGame(initialGame);
		myGameController.initialize(0);
	}

	@Override
	public void chooseGame() {
		Group fileChooseGroup = new Group();
		Scene fileChooseScene = new Scene(fileChooseGroup, this.getScreen().getMyScene().getWidth(), this.getScreen().getMyScene().getHeight());
		ComboBoxGame fileSelector =  new ComboBoxGame("Choose Game", "gamefiles", this);
		fileChooseGroup.getChildren().add((HBox) fileSelector.createNode());
		this.getStage().setScene(fileChooseScene);
	}

	@Override
	public void useGame() {
		BaseScreen myB = new BaseScreen(getStage(), myGameController.getGame());
		getStage().setScene(myB.getMyScene());
		
	}

}