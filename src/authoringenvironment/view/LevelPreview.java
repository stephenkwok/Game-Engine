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

	/**
	 * Constructor for a LevelPreview
	 * @param controller: authoring environment controller.
	 */
	public LevelPreview(Controller controller) {
		myController = controller;
		myPreviewFile = new File(PREVIEW_FILE);
		myGroup = new Group();
		myScene = new Scene(myGroup);
		myModel = new Game(new GameInfo(), myController.getLevels());
	}

	/**
	 * Open a new stage to preview a game.
	 */
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

	/**
	 * Add levels and actors from the current game to be playable.
	 */
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
	

	/**
	 * Save the current game to a preview.xml file
	 */
	private void saveCurrentGame() {
		myCreatorController = new CreatorController(myModel);

		try {
			myCreatorController.saveForPreviewing(myPreviewFile);
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Load the previewed game so as not to override the one the user is currently making in the authoring environment.
	 */
	private void loadForPlaying() {
		myParserController = new ParserController();
		myModel = myParserController.loadforPlaying(myPreviewFile);
	}

	/**
	 * Initialize the game camera.
	 */
	private void initCamera() {
		myCamera = new ParallelCamera();
		myView = new GameScreen(myCamera);
	}
	
	/**
	 * Initialize the game controller.
	 */
	private void initGame() {
		myGameController = new GameController(myModel, PlayType.PREVIEW);
		myGameController.setGame(myModel);
		myGameController.setGameView(myView);
	}
	
	/**
	 * Initialize the stage and scene.
	 */
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
