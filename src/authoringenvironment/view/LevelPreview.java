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

	public LevelPreview(Controller controller) {
		myController = controller;
	}
	public void previewGame(){
		myPreviewFile = new File(PREVIEW_FILE);
		Game model;
		GameController controller;
		GameScreen view;

		Group group = new Group();
		Scene scene = new Scene(group);
		model = new Game(new GameInfo(), myController.getLevels());

		addLevelsAndActors(model);
		saveCurrentGame(model);
		loadToPlay(model);
		Stage stage = initCamera(scene, group);
		controller = initController(model, myView, stage);

		myPreviewFile.delete();

		stage.setOnCloseRequest(e -> {
			controller.endGame(false);
		});


	}

	private void addLevelsAndActors(Game model) {
		//TODO this is duplicated from controller save game.... also no check for if actors is empty
		for(Level level: model.getLevels()) {
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
	
	private void saveCurrentGame(Game model) {
		CreatorController creatorController = new CreatorController(model);
		try {
			creatorController.saveForPreviewing(myPreviewFile);
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadToPlay(Game model) {
		ParserController parserController = new ParserController();
		model = parserController.loadforPlaying(myPreviewFile);
	}
	
	private Stage initCamera(Scene scene, Group group) {
		ParallelCamera camera = new ParallelCamera();
		myView = new GameScreen(camera);

		SubScene sub = myView.getScene();
		sub.fillProperty().set(Color.BLUE);
		group.getChildren().add(sub);

		Stage stage = new Stage();
		stage.setWidth(800);
		stage.setHeight(600);

		sub.setCamera(camera);
		stage.setScene(scene);
		stage.show();
		
		return stage;
	}
	
	private GameController initController(Game model, GameScreen view, Stage stage) {
		GameController controller = new GameController(model, PlayType.PREVIEW);
		controller.setGame(model);
		controller.setGameView(view);
		controller.initialize(0);
		return controller;
	}
}
