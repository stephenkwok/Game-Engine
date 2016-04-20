package gameplayer.view;

import gameengine.controller.*;
import gameengine.model.Actor;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.*;
import gameengine.model.Triggers.BottomCollision;
import gameengine.model.Triggers.KeyTrigger;
import gameengine.model.Triggers.SideCollision;
import gameengine.model.Triggers.TickTrigger;
import gameengine.model.Triggers.TopCollision;
import gameplayer.controller.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
import gameengine.model.IPlayActor;
import authoringenvironment.model.IAuthoringActor;
import gamedata.controller.CreatorController;

public class Tester extends Application {
	
	public Tester() {
		// TODO Auto-generated constructor stub
	}
		
	public static void main(String args[]){
		launch(args);		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameInfo info = new GameInfo();
		info.setMyCurrentLevelNum(0);
		info.setName("Colette");
		
//		Map<String, Integer> options = new HashMap<>();
//		options.put("Points", 0);
		

		//info.setMyHUDOptions(options);

		
		IAuthoringActor actor1 = (IAuthoringActor) new Actor();
		actor1.setImageViewName("redball.png");
		actor1.setName("A1");
		
		
		IAuthoringActor actor2 = (IAuthoringActor) new Actor();
		actor2.setImageViewName("block.png");
		actor2.setX(300);
		actor2.setName("A2");
		
		TickTrigger tick = new TickTrigger();
		Action tick1 = new ApplyPhysics((IPlayActor)actor1);
		Action tick2 = new ApplyPhysics((IPlayActor)actor2);
		Rule rule7 = new Rule(tick,tick1);
		Rule rule8 = new Rule(tick,tick2);
		actor1.addRule(rule7);
		actor2.addRule(rule8);
		
		
		IPlayActor actor3 = new Actor();
		((IAuthoringActor)actor3).setImageViewName("flagpole.png");
		actor3.setY(100);
		actor3.setX(800);
		
		KeyTrigger trigger1 = new KeyTrigger(KeyCode.RIGHT);
		KeyTrigger trigger2 = new KeyTrigger(KeyCode.LEFT);
		SideCollision trigger3 = new SideCollision((IPlayActor)actor1,(IPlayActor)actor2);
		KeyTrigger trigger4 = new KeyTrigger(KeyCode.SPACE);
		BottomCollision trigger5 = new BottomCollision((IPlayActor)actor1,(IPlayActor)actor2);
		SideCollision trigger6 = new SideCollision((IPlayActor)actor1,actor3);
		KeyTrigger trigger9 = new KeyTrigger(KeyCode.Z);
		Action action9 = new ChangeAttribute((IPlayActor)actor1,AttributeType.POINTS,1);
		Rule rule9 = new Rule(trigger9,action9);
		actor1.addRule(rule9);
		Action action1 = new MoveRight((IPlayActor)actor1);
		Action action2 = new MoveLeft((IPlayActor)actor1);
		Action action3 = new HorizontalStaticCollision((IPlayActor)actor1);
		Action action4 = new MoveUp((IPlayActor)actor1);
		Action action5 = new VerticalBounceCollision((IPlayActor)actor1);
		Action action6 = new HorizontalStaticCollision((IPlayActor)actor1);
		Rule rule = new Rule(trigger1,action1);
		Rule rule2 = new Rule(trigger2, action2);
		Rule rule3 = new Rule(trigger3,action3);
		Rule rule4 = new Rule(trigger4,action4);
		Rule rule5 = new Rule(trigger5,action5);
		Rule rule6 = new Rule(trigger6,action6);

		actor1.addRule(rule);
		actor1.addRule(rule2);
		actor1.addRule(rule3);
		actor1.addRule(rule4);
		actor1.addRule(rule5);
		actor1.addRule(rule6);
		
//		actor1.setMain(true);
//		Attribute points = new Attribute(AttributeType.POINTS,0,10,action6);
//		actor1.addAttribute(points);
		
		List<Level> levels = new ArrayList<Level>();
		Level level1 = new Level();
		levels.add(level1);
		level1.addActor(actor1);
		level1.addActor(actor2);
		level1.addActor((IAuthoringActor)actor3);
		
		for(int i=0; i<=17; i++){
			Actor floor = new Actor();
			floor.setName("floor");
			floor.setImageViewName("square.png");
			floor.setX(i*50+i);
			floor.setY(500-floor.getBounds().getHeight());
			BottomCollision b = new BottomCollision((IPlayActor)actor1, floor);
			BottomCollision b2 = new BottomCollision((IPlayActor)actor2, floor);
			BottomCollision b3 = new BottomCollision(actor3, floor);
			
			Action baction = new VerticalStaticCollision((IPlayActor)actor1);
			Action baction2 = new VerticalStaticCollision((IPlayActor)actor2);
			Action baction3 = new VerticalStaticCollision(actor3);
			
			Rule brule = new Rule(b, baction);
			Rule brule2 = new Rule(b2, baction2);
			Rule brule3 = new Rule(b3, baction3);
			
			actor1.addRule(brule);
			actor2.addRule(brule2);
			((IAuthoringActor)actor3).addRule(brule3);
			
			level1.addActor((IAuthoringActor)floor);
		}
		
		Group group = new Group();
		Scene scene = new Scene(group);
		
		Game model = new Game(info,levels);
		CreatorController c = new CreatorController(model);
		c.saveForEditing(new File("gamefiles/test2.xml"));
		PerspectiveCamera camera = new PerspectiveCamera();
		GameScreen view = new GameScreen(camera);

		GameController controller = new GameController(model);
		controller.setGame(model);
		controller.setGameView(view);

		SubScene sub = view.getScene();
		sub.fillProperty().set(Color.BLUE);
		group.getChildren().add(sub);	
		
		Stage stage = new Stage();
		stage.setWidth(800);
		stage.setHeight(600);

//		CreatorController c = new CreatorController(model);
//		System.out.println(c);
//		File myF = new File("gamefiles/testhud.xml");
//		System.out.println(myF);
//		c.saveForEditing(myF);
		
		sub.setCamera(camera);
		stage.setScene(scene);
		stage.show();
		controller.initialize(0);

	}

}