package gameplayer.view;

import java.util.ArrayList;
import java.util.List;

import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.MoveLeft;
import gameengine.model.Triggers.KeyTrigger;
import gameplayer.controller.GameController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameDisplay {

	Pane myPane;
	
	public GameDisplay(Pane myP) {
		myPane = myP;
		init();
	}

	public void init(){
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

		controller.begin();
		myPane.getChildren().add(view.myGroup.getChildren().get(0));
	}
	
}
