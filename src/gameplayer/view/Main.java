package gameplayer.view;

import gameengine.controller.*;
import gameengine.model.Actor;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.MoveLeft;
import gameengine.model.Triggers.KeyTrigger;
import gameplayer.controller.GameController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

import gamedata.controller.CreatorController;

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
		Game model = new Game(info,levels);
		CreatorController c = new CreatorController(model);
		c.saveForEditing(new File("gamefiles/test.xml"));
		GameScreen view = new GameScreen();
		GameController controller = new GameController();
		controller.setGame(model);
		controller.setGameView(view);
		
		Stage stage = new Stage();
		stage.setScene(view.getScene());
		stage.show();
		controller.begin();
		
	}

}
