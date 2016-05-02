package authoringenvironment.view;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import authoringenvironment.controller.Controller;
import gamedata.controller.CreatorController;
import gamedata.controller.ParserController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.ActorState;
import gameengine.model.IPlayActor;
import gameplayer.controller.GameController;
import gameplayer.controller.PlayType;
import gameplayer.view.GameScreen;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LevelPreview {
	private static final String PREVIEW_FILE = "preview.xml";
	private File myPreviewFile;
	private Controller myController;
	private GameScreen myView;
	private Game myModel;
	private GameController myGameController;
	private Group myGroup;
	private Scene myScene;
	private CreatorController myCreatorController;
	private ParallelCamera myCamera; 
	private ParserController myParserController;
	private SubScene mySubScene;
	private Stage myStage;

	public LevelPreview(Controller controller) {
		myController = controller;
		myPreviewFile = new File(PREVIEW_FILE);
		myGroup = new Group();
		myScene = new Scene(myGroup);
		myModel = new Game(new GameInfo(), myController.getLevels());
	}

	public void previewGame(){
		addLevelsAndActors();   
		saveCurrentGame();
		loadForPlaying();
		initCamera();
		initGame();
		initGameView();
		myGameController.initialize(0);
		myPreviewFile.delete();
		myStage.setOnCloseRequest(e -> {
			myGameController.endGame(false);
		});
	}

	private void initGame() {
		myGameController = new GameController(myModel, PlayType.PREVIEW);
		myGameController.setGame(myModel);
		myGameController.setGameView(myView);
	}

	private void addLevelsAndActors() {
		for(Level level: myModel.getLevels()) {
			for (IPlayActor actor: level.getActors()) {
				if (actor.checkState(ActorState.MAIN)) {
					level.getMainCharacters().add(actor);
				}
			}
			if (level.getMainCharacters().size() == 0) {
				level.getActors().get(0).addState(ActorState.MAIN);
			}
		}
	}

	private void saveCurrentGame() {
		myCreatorController = new CreatorController(myModel);

		try {
			myCreatorController.saveForPreviewing(myPreviewFile);
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadForPlaying() {
		myParserController = new ParserController();
		myModel = myParserController.loadforPlaying(myPreviewFile);
	}

	private void initCamera() {
		myCamera = new ParallelCamera();
		myView = new GameScreen(myCamera);
	}

	private void initGameView() {
		mySubScene = myView.getScene();
		mySubScene.fillProperty().set(Color.BLUE);
		myGroup.getChildren().add(mySubScene);

		myStage = new Stage();
		myStage.setWidth(800);
		myStage.setHeight(600);

		mySubScene.setCamera(myCamera);
		myStage.setScene(myScene);
		myStage.show();
	}
}
