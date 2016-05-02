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
	private File myPreviewFile;
	private Controller myController;
<<<<<<< HEAD
	
	public LevelPreview(Controller controller) {
		myController = controller;
	}
	public void previewGame(){
		myPreviewFile = new File("preview.xml");
		Game model;
		GameController controller;
		GameScreen view;
        
		Group group = new Group();
        Scene scene = new Scene(group);

        model = new Game(new GameInfo(), myController.getLevels());
        //TODO this is duplicated from controller save game.... also no check for if actors is empty
        for(Level level: model.getLevels()) {
=======
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
>>>>>>> a073355c41e95c2785e8021a9dfe261031755d2c
			for (IPlayActor actor: level.getActors()) {
				if (actor.checkState(ActorState.MAIN)) {
					level.getMainCharacters().add(actor);
				}
			}
			if (level.getMainCharacters().size() == 0) {
				level.getActors().get(0).addState(ActorState.MAIN);
			}
		}
<<<<<<< HEAD
        
        CreatorController creatorController = new CreatorController(model);
        try {
			creatorController.saveForPreviewing(myPreviewFile);
=======
	}

	private void saveCurrentGame() {
		myCreatorController = new CreatorController(myModel);

		try {
			myCreatorController.saveForPreviewing(myPreviewFile);
>>>>>>> a073355c41e95c2785e8021a9dfe261031755d2c
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
<<<<<<< HEAD
        ParserController parserController = new ParserController();
        model = parserController.loadforPlaying(myPreviewFile);
        
        ParallelCamera camera = new ParallelCamera();
        view = new GameScreen(camera);

        controller = new GameController(model, PlayType.PREVIEW);
        controller.setGame(model);
        controller.setGameView(view);

        SubScene sub = view.getScene();
        sub.fillProperty().set(Color.BLUE);
        group.getChildren().add(sub);

        Stage stage = new Stage();
        stage.setWidth(800);
        stage.setHeight(600);

        sub.setCamera(camera);
        stage.setScene(scene);
        stage.show();
        controller.initialize(0);
        
        //controller.getView().clearGame();
        
        myPreviewFile.delete();
        
        stage.setOnCloseRequest(e -> {
        	controller.endGame(false);
        });
        
        
=======
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
>>>>>>> a073355c41e95c2785e8021a9dfe261031755d2c
	}
}