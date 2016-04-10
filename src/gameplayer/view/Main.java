package gameplayer.view;

import gameengine.controller.*;
import gameengine.model.Actor;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.MoveLeft;
import gameengine.model.Triggers.KeyTrigger;
import gameplayer.controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class Main extends Application {
	
	public Main() {
		// TODO Auto-generated constructor stub
	}
	

	
	public static void main(String args[]){
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameInfo info = new GameInfo();
		info.setCurrentLevelNum(0);
		
		Actor actor1 = new Actor();
		Image actor1img = new Image(getClass().getClassLoader().getResourceAsStream("newactor.png"));
		actor1.setImageView(new ImageView(actor1img));
		KeyTrigger trigger = new KeyTrigger(KeyCode.SPACE);
		List<Object> args = new ArrayList<Object>();
		args.add(50.0);
		Action action = new MoveLeft(actor1,args);
		Rule rule = new Rule(trigger,action);
		actor1.addRule(rule);
		
		List<Level> levels = new ArrayList<Level>();
		Level level1 = new Level();
		level1.addActor(actor1);
		levels.add(level1);
		Game model = new Game("file",info,levels);
		GameScreen view = new GameScreen();
		GameController controller = new GameController();
		controller.setGame(model);
		controller.setGameView(view);
		
		Stage stage = new Stage();
		stage.setScene(view.getScene());
		stage.show();
		controller.begin();
		
//		Stage stage = new Stage();
//		Group root = new Group();
//		Image actor1img = new Image(getClass().getClassLoader().getResourceAsStream("newactor.png"));
//		ImageView imgview = new ImageView(actor1img);
//		root.getChildren().add(imgview);
//		Scene scene = new Scene(root,800,800);
//		PerspectiveCamera camera = new PerspectiveCamera();
//
//		scene.setCamera(camera);
//		stage.setScene(scene);
//		stage.show();
//		
//        KeyFrame frame = new KeyFrame(Duration.seconds(.01),
//                e -> {
//                	camera.setTranslateX(camera.getTranslateX()-5);
//                	System.out.println(imgview.getX());
//                });
//		Timeline animation = new Timeline();
//		animation.setCycleCount(Timeline.INDEFINITE);
//		animation.getKeyFrames().add(frame);
//		animation.play();
		
	}

}
