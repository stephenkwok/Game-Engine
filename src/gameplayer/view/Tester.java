package gameplayer.view;

import gameengine.controller.*;
import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import gameengine.model.Rule;
import gameengine.model.Actions.*;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.BottomCollision;
import gameengine.model.Triggers.ITrigger;
import gameengine.model.Triggers.KeyTrigger;
import gameengine.model.Triggers.SideCollision;
import gameengine.model.Triggers.TickTrigger;
import gameengine.model.Triggers.TopCollision;
import gameplayer.controller.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
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

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameInfo info = new GameInfo();
		info.setMyCurrentLevelNum(0);
		info.setName("Colette");

		// Map<String, Integer> options = new HashMap<>();
		// options.put("Points", 0);

		// info.setMyHUDOptions(options);

		IAuthoringActor actor1 = (IAuthoringActor) new Actor();
		actor1.setImageViewName("runningmario1.png");
		actor1.setName("A1");
		actor1.setID(1);

		actor1.addSpriteImage("runningmario2.png");
		actor1.addSpriteImage("runningmario3.png");

		IAuthoringActor actor2 = (IAuthoringActor) new Actor();
		actor2.setImageViewName("block.png");
		actor2.setX(300);
		actor2.setY(300);
		actor2.setName("A2");
		actor2.setID(2);

		IPlayActor actor4 = new Actor();
		((Actor) actor4).setName("enemy");
		((IAuthoringActor) actor4).setImageViewName("goomba.png");
		System.out.println(actor4.getAttribute(AttributeType.HEALTH));
		((Actor) actor4).setID(3);
		actor4.setX(315);
		BottomCollision enemyTrigger = new BottomCollision((IPlayActor) actor4, (IPlayActor) actor2);
		Action enemyAction = new VerticalBounceCollision((IPlayActor) actor4);
		Rule enemyRule = new Rule(enemyTrigger, enemyAction);
		((Actor) actor4).addRule(enemyRule);

		// main character killed if it hits enemy from side or is bounced on by
		// enemy
		SideCollision kill = new SideCollision(actor4, (IPlayActor) actor1);
		BottomCollision kill2 = new BottomCollision(actor4, (IPlayActor) actor1);
		Action killAction = new Destroy((Actor) actor1);
		Action killAction2 = new Destroy((Actor) actor1);
		Rule killRule = new Rule(kill, killAction);
		Rule killRule2 = new Rule(kill2, killAction2);
		((Actor) actor4).addRule(killRule);
		((Actor) actor4).addRule(killRule2);

		// main character kills enemy if it hits it from above
		TopCollision kill3 = new TopCollision(actor4, (IPlayActor) actor1);
		Action killAction3 = new Destroy((Actor) actor4);
		Rule killRule3 = new Rule(kill3, killAction3);
		((Actor) actor4).addRule(killRule3);

		TickTrigger tick = new TickTrigger();
		Action tick1 = new ApplyPhysics((IPlayActor) actor1);
		Action tick2 = new ApplyPhysics((IPlayActor) actor2);
		Action tick4 = new ApplyPhysics((IPlayActor) actor4);
		Rule rule7 = new Rule(tick, tick1);
		Rule rule8 = new Rule(tick, tick2);
		Rule ruleEnemy = new Rule(tick, tick4);
		actor1.addRule(rule7);
		actor2.addRule(rule8);
		((Actor) actor4).addRule(ruleEnemy);

		IPlayActor actor3 = new Actor();
		((IAuthoringActor) actor3).setImageViewName("flagpole.png");
		actor3.setY(100);
		actor3.setX(800);

		KeyTrigger triggerDown = new KeyTrigger(KeyCode.DOWN);
		Action moveForwards = new MoveForward((IPlayActor) actor1);
		Action glideForwards = new GlideForward((IPlayActor) actor1);
		Rule movingForwards = new Rule(triggerDown, glideForwards);
		actor1.addRule(movingForwards);

		KeyTrigger trigger1 = new KeyTrigger(KeyCode.RIGHT);
		KeyTrigger trigger2 = new KeyTrigger(KeyCode.LEFT);
		SideCollision trigger3 = new SideCollision((IPlayActor) actor1, (IPlayActor) actor2);
		KeyTrigger trigger4 = new KeyTrigger(KeyCode.SPACE);
		BottomCollision trigger5 = new BottomCollision((IPlayActor) actor1, (IPlayActor) actor2);
		SideCollision trigger6 = new SideCollision((IPlayActor) actor1, actor3);
		KeyTrigger trigger9 = new KeyTrigger(KeyCode.Z);
		Action action9 = new ChangeAttribute((IPlayActor) actor1, AttributeType.POINTS, 1);
		Rule rule9 = new Rule(trigger9, action9);
		actor1.addRule(rule9);

		KeyTrigger triggerSpawn = new KeyTrigger(KeyCode.S);

		Action action1 = new MoveRight((IPlayActor) actor1);
		Action action2 = new MoveLeft((IPlayActor) actor1);
		Action action3 = new HorizontalStaticCollision((IPlayActor) actor1);
		Action action4 = new MoveUp((IPlayActor) actor1);
		Action action5 = new VerticalBounceCollision((IPlayActor) actor1);
		Action action6 = new LoseGame((Actor) actor1);
		Action actionSpawn = new Spawn((IPlayActor) actor1);

		Rule rule = new Rule(trigger1, action1);
		Rule rule2 = new Rule(trigger2, action2);
		Rule rule3 = new Rule(trigger3, action3);
		Rule rule4 = new Rule(trigger4, action4);
		Rule rule5 = new Rule(trigger5, action5);
		Rule rule6 = new Rule(trigger6, action6);
		Rule ruleSpawn = new Rule(triggerSpawn, actionSpawn);

		actor1.addRule(rule);
		actor1.addRule(rule2);
		actor1.addRule(rule3);
		actor1.addRule(rule4);
		actor1.addRule(rule5);
		actor1.addRule(rule6);
		actor1.addRule(ruleSpawn);

		TickTrigger intTick = new TickTrigger(5);
		Action animate = new NextImage((IPlayActor) actor1);
		actor1.addRule(new Rule(intTick, animate));

		actor1.addState(ActorState.MAIN);
		Attribute points = new Attribute(AttributeType.POINTS, 0, (IGameElement) actor1);
		actor1.addAttribute(points);

		ITrigger attreached = new AttributeReached(AttributeType.POINTS, (IGameElement) actor1, 5);
		Action wingame = new WinGame((IPlayActor) actor1);

		actor1.addRule(new Rule(attreached, wingame));

		List<Level> levels = new ArrayList<Level>();
		Level level1 = new Level();
		level1.setMyBackgroundImgName("vgnwpGb.png");
		levels.add(level1);
		level1.addActor(actor1);
		level1.addActor(actor2);
		level1.addActor((IAuthoringActor) actor3);
		level1.addActor((IAuthoringActor) actor4);

		for (int i = 0; i <= 17; i++) {
			Actor floor = new Actor();
			floor.setName("floor");
			floor.setID(5);
			floor.setImageViewName("square.png");
			floor.setX(i * 50 + i);
			floor.setY(500 - floor.getBounds().getHeight());
			BottomCollision b = new BottomCollision((IPlayActor) actor1, floor);
			BottomCollision b2 = new BottomCollision((IPlayActor) actor2, floor);
			BottomCollision b3 = new BottomCollision(actor3, floor);

			Action baction = new VerticalStaticCollision((IPlayActor) actor1);
			Action baction2 = new VerticalStaticCollision((IPlayActor) actor2);
			Action baction3 = new VerticalStaticCollision(actor3);

			Rule brule = new Rule(b, baction);
			Rule brule2 = new Rule(b2, baction2);
			Rule brule3 = new Rule(b3, baction3);

			actor1.addRule(brule);
			actor2.addRule(brule2);
			((IAuthoringActor) actor3).addRule(brule3);

			level1.addActor((IAuthoringActor) floor);
		}

		Group group = new Group();
		Scene scene = new Scene(group);

		Game model = new Game(info, levels);
		CreatorController c = new CreatorController(model);
		c.saveForEditing(new File("gamefiles/test2.xml"));
		ParallelCamera camera = new ParallelCamera();
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

		sub.setCamera(camera);
		stage.setScene(scene);
		stage.show();
		controller.initialize(0);

	}

}