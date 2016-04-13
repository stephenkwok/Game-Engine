package gameplayer.controller;

import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import gameengine.controller.Game;
import gui.controller.IScreenController;
import gui.controller.ScreenController;
import gui.view.ComboBoxGame;
import gui.view.Screen;
import gameplayer.view.SplashScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SplashScreenController extends ScreenController {

	private ResourceBundle myResources;
	private SplashScreen mySplash;
	private Game myGame;

	public SplashScreenController(Stage myStage, SplashScreen mySplash, ResourceBundle myResources) {
		super(myStage);
		this.myResources = myResources;
		this.mySplash = mySplash;
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}


	public void useGame() {
		mySplash.play(myGame);
	}
	
	public void openHighScores() {
		try {
			mySplash.openHighScores();
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void edit()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		mySplash.edit();
	}

	@Override
	public void setGame(Game game) {
		this.myGame = game;
	}

	@Override
	public Screen getScreen() {
		return mySplash;
	}

	@Override
	public void chooseGame() {
		Group fileChooseGroup = new Group();
		Scene fileChooseScene = new Scene(fileChooseGroup, this.getScreen().getMyScene().getWidth(),
				this.getScreen().getMyScene().getHeight());
		ComboBoxGame fileSelector = new ComboBoxGame("Choose Game", "gamefiles", this);
		fileChooseGroup.getChildren().add((HBox) fileSelector.createNode());
		this.getStage().setScene(fileChooseScene);

	}


}