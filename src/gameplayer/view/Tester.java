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
import gameengine.model.PhysicsEngine;
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

        IAuthoringActor actor1 = (IAuthoringActor) new Actor();
        actor1.setImageViewName("runningmario1.png");
        actor1.setName("A1");
        actor1.setID(1);

        actor1.addSpriteImage("runningmario2.png");
        actor1.addSpriteImage("runningmario3.png");
        
        IAuthoringActor actor12 = (IAuthoringActor) new Actor();
        actor12.setImageViewName("runningmario1.png");
        actor12.setName("A1");
        actor12.setID(4);
        actor12.addState(ActorState.MAIN);
        actor12.addAttribute(new Attribute(AttributeType.POINTS, 0, (IPlayActor) actor12));

        actor1.addSpriteImage("runningmario2.png");
        actor1.addSpriteImage("runningmario3.png");
        
        

        IAuthoringActor actor2 = (IAuthoringActor) new Actor();
        actor2.setImageViewName("block.png");
        actor2.setX(300);
        actor2.setY(300);
        actor2.setName("A2");
        actor2.setID(2);
        
        
        IAuthoringActor blocky = (IAuthoringActor) new Actor();
        blocky.setImageViewName("block.png");
        blocky.setX(650);
        blocky.setY(300);
        blocky.setName("blocky");
        blocky.setID(2);
        
        IAuthoringActor spawnedActor = (IAuthoringActor) new Actor();
        spawnedActor.setImageViewName("redball.png");
        spawnedActor.setName("bullet");
        spawnedActor.setID(90);
        spawnedActor.getImageView().resize(10, 10);

        IPlayActor actor4 = new Actor();
        ((Actor) actor4).setName("enemy");
        ((IAuthoringActor)actor4).setImageViewName("goomba.png");
        ((Actor) actor4).setID(3);
        actor4.setX(315);
        BottomCollision enemyTrigger = new BottomCollision((Actor)actor4, (Actor)actor2);
        Action enemyAction = new VerticalBounceCollision((Actor) actor4);
        Rule enemyRule = new Rule(enemyTrigger, enemyAction);
        ((Actor) actor4).addRule(enemyRule);

        //
        IPlayActor enemy2 = new Actor();
        ((IAuthoringActor)enemy2).setImageViewName("goomba.png");
        ((Actor) enemy2).setName("enemy2");
        enemy2.setX(400);
        System.out.println(enemy2.getHeading());
        TickTrigger trigger = new TickTrigger();
        Action moveForward = new GlideForward((Actor)enemy2, 1.0);
        Rule movingForward = new Rule(trigger, moveForward);
        enemy2.addRule(movingForward);
        

        SideCollision triggerenemy = new SideCollision((Actor)enemy2,(Actor)blocky);
        Action actionenemy2 = new ReverseHeading((Actor)enemy2);
        Rule ruleenemy = new Rule(triggerenemy,actionenemy2);
        enemy2.addRule(ruleenemy);

        //moves it left
//        Action actionCollide = new MoveLeft((Actor)enemy2);
//        Rule ruleCollide = new Rule(triggerenemy,actionCollide);
//        enemy2.addRule(ruleCollide);
        
        //main character killed if it hits enemy from side or is bounced on by enemy
        SideCollision kill = new SideCollision((Actor)actor4, (Actor) actor1);
        BottomCollision kill2 = new BottomCollision((Actor)actor4, (Actor) actor1);
        Action killAction = new Destroy((Actor) actor1);
        Action killAction2 = new Destroy((Actor) actor1);
        Rule killRule = new Rule(kill, killAction);
        Rule killRule2 = new Rule(kill2, killAction2);
        ((Actor) actor4).addRule(killRule);
        ((Actor) actor4).addRule(killRule2);

        //main character kills enemy if it hits it from above
        TopCollision kill3 = new TopCollision((Actor)actor4, (Actor)actor1);
        Action killAction3 = new Destroy((Actor) actor4);
        Rule killRule3 = new Rule(kill3, killAction3);
        ((Actor) actor4).addRule(killRule3);
        
        SideCollision bulletKill = new SideCollision((Actor)actor4,(Actor)spawnedActor);
        Rule bulletKillRule  = new Rule(bulletKill, killAction3);
        actor4.addRule(bulletKillRule);
        

        TickTrigger tick = new TickTrigger();
        Action tick1 = new ApplyPhysics((Actor)actor1);
        Action tick2 = new ApplyPhysics((Actor)actor2);
        Action tick3 = new ApplyPhysics((Actor)blocky);
        Action tick4 = new ApplyPhysics((Actor) actor4);
        Action tick5 = new ApplyPhysics((Actor) enemy2);
        Rule rule7 = new Rule(tick,tick1);
        Rule rule8 = new Rule(tick,tick2);
        Rule blockrule = new Rule (tick, tick3);
        Rule ruleBlock = new Rule(tick, tick4);
        Rule ruleEnemy = new Rule(tick, tick5);

        actor1.addRule(rule7);
        actor2.addRule(rule8);
        blocky.addRule(blockrule);
        ((Actor) actor4).addRule(ruleBlock);
        enemy2.addRule(ruleEnemy);

        IPlayActor actor3 = new Actor();
        ((IAuthoringActor)actor3).setImageViewName("flagpole.png");
        actor3.setY(100);
        actor3.setX(800);




        KeyTrigger triggerDown = new KeyTrigger(KeyCode.DOWN);
       // TickTrigger trigger = new TickTrigger();
        Action moveForwards = new GlideForward((Actor)actor1, 1.0);
        Rule movingForwards = new Rule(triggerDown, moveForwards);
        actor1.addRule(movingForwards);

        KeyTrigger trigger1 = new KeyTrigger(KeyCode.RIGHT);
        KeyTrigger trigger2 = new KeyTrigger(KeyCode.LEFT);
        SideCollision trigger3 = new SideCollision((Actor)actor1,(Actor)actor2);
        KeyTrigger trigger4 = new KeyTrigger(KeyCode.SPACE);
        BottomCollision trigger5 = new BottomCollision((Actor)actor1,(Actor)actor2);
        SideCollision trigger6 = new SideCollision((Actor)actor1,(Actor)actor3);
        KeyTrigger trigger9 = new KeyTrigger(KeyCode.Z);
        Action action9 = new ChangeAttribute((IPlayActor)actor1,AttributeType.POINTS,1);
        Rule rule9 = new Rule(trigger9,action9);
        actor1.addRule(rule9);

        KeyTrigger triggerSpawn = new KeyTrigger(KeyCode.S);
        TickTrigger bulletTick = new TickTrigger();
        Action bulletAction = new GlideForward((Actor)spawnedActor,2.5);
        Rule bulletRule = new Rule(bulletTick,bulletAction);
        spawnedActor.addRule(bulletRule);
        PhysicsEngine newPhysicsEngine = new PhysicsEngine();
        ((Actor) spawnedActor).setPhysicsEngine(newPhysicsEngine);
        
        
        
        Action action1 = new MoveRight((Actor)actor1);
        Action action2 = new MoveLeft((Actor)actor1);
        Action action3 = new HorizontalStaticCollision((Actor)actor1);
        Action action4 = new MoveUp((Actor)actor1);
        Action action5 = new VerticalBounceCollision((Actor)actor1);
        Action action6 = new WinGame((Actor)actor1);
        Action actionSpawn = new Spawn((Actor) actor1, (Actor) spawnedActor);
        Action actionNextLevel = new NextLevel((IPlayActor) actor1);
        
        Rule rule = new Rule(trigger1,action1);
        Rule rule2 = new Rule(trigger2, action2);
        Rule rule3 = new Rule(trigger3,action3);
        Rule rule4 = new Rule(trigger4,action4);
        Rule rule5 = new Rule(trigger5,action5);
        Rule rule6 = new Rule(trigger6,action6);
        Rule ruleSpawn = new Rule(triggerSpawn, actionSpawn);
        Rule ruleNextLevel = new Rule(trigger6, actionNextLevel);

        actor1.addRule(rule);
        actor1.addRule(rule2);
        actor1.addRule(rule3);
        actor1.addRule(rule4);
        actor1.addRule(rule5);
        //actor1.addRule(rule6);
        actor1.addRule(ruleSpawn);
        actor1.addRule(ruleNextLevel);


        TickTrigger intTick = new TickTrigger(5);
        Action animate = new NextImage((Actor)actor1);
        actor1.addRule(new Rule(intTick, animate));

        actor1.addState(ActorState.MAIN);
        actor1.addAttribute(new Attribute(AttributeType.POINTS, 0, (IPlayActor) actor1));
        
		ITrigger attreached = new AttributeReached((IGameElement)actor1, AttributeType.POINTS,5);
		Action wingame = new WinGame((IPlayActor) actor1);
		
		actor1.addRule(new Rule(attreached,wingame));
		
		BottomCollision pls = new BottomCollision((Actor)actor1, (Actor)actor4); 
		Action hello = new VerticalBounceCollision((Actor)actor1);
		Rule lesads = new Rule(pls ,hello);
		actor1.addRule(lesads);
		
		

        List<Level> levels = new ArrayList<Level>();
        Level level1 = new Level();
        //level1.setMyBackgroundImgName("mariobackground.png");
        levels.add(level1);
        level1.addActor(actor1);
        level1.addActor(actor12);

        level1.getMainCharacters().add((IPlayActor) actor1);
        level1.addActor(actor2);
        level1.addActor(blocky);
        level1.addActor((IAuthoringActor) enemy2);
        level1.addActor((IAuthoringActor)actor3);
        level1.addActor((IAuthoringActor) actor4);
        
        
        Level level2 = new Level();
        level2.setMyBackgroundImgName("vgnwpGb.png");
        level2.getMainCharacters().add((IPlayActor) actor1);
        levels.add(level2);
        level2.addActor(actor1);
        level2.addActor(actor12);

        
        
        level1.setSoundtrack("Jordan.mp3");
        level2.setSoundtrack("Robot.mp3");

        Level level3 = new Level();
        level3.setMyBackgroundImgName("vgnwpGb.png");
        levels.add(level3);
        level3.addActor(actor1);
        
        int yposition = 200;
        int xposition = 150;
        for(int i=1; i<=7; i++){
            Actor block = new Actor();
            block.setName("salad");
            block.setID(101);
            block.setImageViewName("salad2.png");
            block.setX(i*50+xposition);
            block.setY(i*50+yposition);
            BottomCollision b = new BottomCollision((Actor)actor1, block);
            Action baction = new VerticalBounceCollision((Actor)actor1);
            Rule brule = new Rule(b, baction);

            TopCollision b2 = new TopCollision((Actor)actor1, block);
            Action baction2 = new VerticalBounceCollision((Actor)actor1);
            Rule brule2 = new Rule(b2, baction2);

            actor1.addRule(brule);
            actor1.addRule(brule2);
            level2.addActor((IAuthoringActor) block);
            yposition-=50;
        }
        
        int yposition2 = 100;
        int xposition2 = 600;
        for(int j=1; j<=5; j++){
            Actor block2 = new Actor();
            block2.setName("salad");
            block2.setID(102);
            block2.setImageViewName("salad2.png");
            block2.setX(j*50+xposition2);
            block2.setY(j*50+yposition2);
            BottomCollision b3 = new BottomCollision((Actor)actor1, block2);
            Action baction3 = new VerticalBounceCollision((Actor)actor1);
            Rule brule3 = new Rule(b3, baction3);

            TopCollision b4 = new TopCollision((Actor)actor1, block2);
            Action baction4 = new VerticalBounceCollision((Actor)actor1);
            Rule brule4 = new Rule(b4, baction4);

            Rule ruleNextLevel2 = new Rule(b4, actionNextLevel);
            actor1.addRule(ruleNextLevel2);
            actor1.addRule(brule3);
            actor1.addRule(brule4);
            level2.addActor((IAuthoringActor) block2);
            yposition2-=50;
        }
        

        
//        IAuthoringActor block1 = (IAuthoringActor) new Actor();
//        block1.setImageViewName("block.png");
//        block1.setX(200);
//        block1.setY(200);
//        block1.setName("block1");
//        block1.setID(101);
//        level2.addActor(block1); 
//        
//        IAuthoringActor block2 = (IAuthoringActor) new Actor();
//        block2.setImageViewName("block.png");
//        block2.setX(300);
//        block2.setY(300);
//        block2.setName("block2");
//        block2.setID(102);
//        level2.addActor(block2); 
//        
//        IAuthoringActor block3 = (IAuthoringActor) new Actor();
//        block3.setImageViewName("block.png");
//        block3.setX(400);
//        block3.setY(200);
//        block3.setName("block3");
//        block3.setID(103);
//        level2.addActor(block3); 
        
        /**
         * testing create actors
         */
        IAuthoringActor a = (IAuthoringActor) new Actor();
        a.setID(10);
        a.setImageViewName("pipes.png");
        TickTrigger translatetick = new TickTrigger(45);
        Action translateaction = new MoveRight((Actor)a);
        a.addRule(new Rule(translatetick,translateaction));
        TickTrigger newtick = new TickTrigger(220);
        Action newaction = new CreateActor((Actor)actor1,(Actor)a,0.0,0.0);
       // actor1.addRule(new Rule(newtick,newaction));
        
        
        
        for(int i=0; i<=25; i++){
            Actor floor = new Actor();
            floor.setName("floor");
            floor.setID(5);
            floor.setImageViewName("square.png");
            //floor.addState(ActorState.INVISIBLE);
            floor.setX(i*50+i);
            floor.setY(500-floor.getBounds().getHeight());
            BottomCollision b = new BottomCollision((Actor)actor1, floor);
            BottomCollision b2 = new BottomCollision((Actor)actor2, floor);
            BottomCollision b3 = new BottomCollision((Actor)actor3, floor);
            BottomCollision b4 = new BottomCollision((Actor)blocky, floor);
            BottomCollision b5 = new BottomCollision((Actor)enemy2, floor);
            
            Action baction = new VerticalStaticCollision((Actor)actor1);
            Action baction2 = new VerticalStaticCollision((Actor)actor2);
            Action baction3 = new VerticalStaticCollision((Actor)actor3);
            Action baction4 = new VerticalStaticCollision((Actor)blocky);
            Action baction5 = new VerticalStaticCollision((Actor)enemy2);

            Rule brule = new Rule(b, baction);
            Rule brule2 = new Rule(b2, baction2);
            Rule brule3 = new Rule(b3, baction3);
            Rule brule4 = new Rule (b4, baction4);
            Rule brule5 = new Rule (b5, baction5);

            actor1.addRule(brule);
            actor2.addRule(brule2);
            ((IAuthoringActor)actor3).addRule(brule3);
            blocky.addRule(brule4);
            enemy2.addRule(brule5);

            level1.addActor((IAuthoringActor)floor);
            level2.addActor((IAuthoringActor) floor);
            level3.addActor((IAuthoringActor)floor);
        }

        Group group = new Group();
        Scene scene = new Scene(group);

        Game model = new Game(info,levels);
        model.setHUDInfoFile("a.txt");
        
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

//package gameplayer.view;
//
//import gameengine.controller.*;
//import gameengine.model.Actor;
//import gameengine.model.ActorState;
//import gameengine.model.Attribute;
//import gameengine.model.AttributeType;
//import gameengine.model.IGameElement;
//import gameengine.model.Rule;
//import gameengine.model.Actions.*;
//import gameengine.model.Triggers.AttributeReached;
//import gameengine.model.Triggers.BottomCollision;
//import gameengine.model.Triggers.ITrigger;
//import gameengine.model.Triggers.KeyTrigger;
//import gameengine.model.Triggers.SideCollision;
//import gameengine.model.Triggers.TickTrigger;
//import gameengine.model.Triggers.TopCollision;
//import gameplayer.controller.GameController;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.ParallelCamera;
//import javafx.scene.Scene;
//import javafx.scene.SubScene;
//import javafx.scene.input.KeyCode;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//import java.io.File;
//import java.util.*;
//import gameengine.model.IPlayActor;
//import gameengine.model.PhysicsEngine;
//import authoringenvironment.model.IAuthoringActor;
//import gamedata.controller.CreatorController;
//
//public class Tester extends Application {
//
//    public Tester() {
//        // TODO Auto-generated constructor stub
//    }
//
//    public static void main(String args[]){
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        GameInfo info = new GameInfo();
//        info.setMyCurrentLevelNum(0);
//        info.setName("Colette");
//
//        IAuthoringActor actor1 = (IAuthoringActor) new Actor();
//        actor1.setImageViewName("runningmario1.png");
//        actor1.setName("A1");
//        actor1.setID(1);
//
//        actor1.addSpriteImage("runningmario2.png");
//        actor1.addSpriteImage("runningmario3.png");
//
//
//        IAuthoringActor actor2 = (IAuthoringActor) new Actor();
//        actor2.setImageViewName("block.png");
//        actor2.setX(300);
//        actor2.setY(300);
//        actor2.setName("A2");
//        actor2.setID(2);
//        
//        
//        IAuthoringActor blocky = (IAuthoringActor) new Actor();
//        blocky.setImageViewName("block.png");
//        blocky.setX(650);
//        blocky.setY(300);
//        blocky.setName("blocky");
//        blocky.setID(2);
//        
//        IAuthoringActor spawnedActor = (IAuthoringActor) new Actor();
//        spawnedActor.setImageViewName("redball.png");
//        spawnedActor.setName("bullet");
//        spawnedActor.setID(90);
//        spawnedActor.getImageView().resize(10, 10);
//
//        IPlayActor actor4 = new Actor();
//        ((Actor) actor4).setName("enemy");
//        ((IAuthoringActor)actor4).setImageViewName("goomba.png");
//        ((Actor) actor4).setID(3);
//        actor4.setX(315);
//        BottomCollision enemyTrigger = new BottomCollision((Actor)actor4, (Actor)actor2);
//        Action enemyAction = new VerticalBounceCollision((Actor) actor4);
//        Rule enemyRule = new Rule(enemyTrigger, enemyAction);
//        ((Actor) actor4).addRule(enemyRule);
//
//        //
//        IPlayActor enemy2 = new Actor();
//        ((IAuthoringActor)enemy2).setImageViewName("goomba.png");
//        ((Actor) enemy2).setName("enemy2");
//        enemy2.setX(400);
//        System.out.println(enemy2.getHeading());
//        TickTrigger trigger = new TickTrigger();
//        Action moveForward = new GlideForward((Actor)enemy2, 2.0);
//        Rule movingForward = new Rule(trigger, moveForward);
//        enemy2.addRule(movingForward);
//        
//        //movng back and forth
//        SideCollision triggerenemy = new SideCollision((Actor)enemy2,(Actor)blocky);
//        Action actionenemy2 = new ReverseHeading((Actor)enemy2);
//        Rule ruleenemy = new Rule(triggerenemy,actionenemy2);
//        enemy2.addRule(ruleenemy);
//
//        //killing
//        TopCollision kill4 = new TopCollision((Actor)enemy2, (Actor)actor1);
//        Action killAction4 = new Destroy((Actor) enemy2);
//        Rule killRule4 = new Rule(kill4, killAction4);
//        ((Actor) enemy2).addRule(killRule4);
//        
//        
//        //moves it left
////        Action actionCollide = new MoveLeft((Actor)enemy2);
////        Rule ruleCollide = new Rule(triggerenemy,actionCollide);
////        enemy2.addRule(ruleCollide);
//        
//        //main character killed if it hits enemy from side or is bounced on by enemy
//        SideCollision kill = new SideCollision((Actor)actor4, (Actor) actor1);
//        BottomCollision kill2 = new BottomCollision((Actor)actor4, (Actor) actor1);
//        Action killAction = new Destroy((Actor) actor1);
//        Action killAction2 = new Destroy((Actor) actor1);
//        Rule killRule = new Rule(kill, killAction);
//        Rule killRule2 = new Rule(kill2, killAction2);
//        ((Actor) actor4).addRule(killRule);
//        ((Actor) actor4).addRule(killRule2);
//
//        //main character kills enemy if it hits it from above
//        TopCollision kill3 = new TopCollision((Actor)actor4, (Actor)actor1);
//        Action killAction3 = new Destroy((Actor) actor4);
//        Rule killRule3 = new Rule(kill3, killAction3);
//        ((Actor) actor4).addRule(killRule3);
//        
//        SideCollision bulletKill = new SideCollision((Actor)actor4,(Actor)spawnedActor);
//        Rule bulletKillRule  = new Rule(bulletKill, killAction3);
//        actor4.addRule(bulletKillRule);
//        
//
//        TickTrigger tick = new TickTrigger();
//        Action tick1 = new ApplyPhysics((Actor)actor1);
//        Action tick2 = new ApplyPhysics((Actor)actor2);
//        Action tick3 = new ApplyPhysics((Actor)blocky);
//        Action tick4 = new ApplyPhysics((Actor) actor4);
//        Action tick5 = new ApplyPhysics((Actor) enemy2);
//        Rule rule7 = new Rule(tick,tick1);
//        Rule rule8 = new Rule(tick,tick2);
//        Rule blockrule = new Rule (tick, tick3);
//        Rule ruleBlock = new Rule(tick, tick4);
//        Rule ruleEnemy = new Rule(tick, tick5);
//
//        actor1.addRule(rule7);
//        actor2.addRule(rule8);
//        blocky.addRule(blockrule);
//        ((Actor) actor4).addRule(ruleBlock);
//        enemy2.addRule(ruleEnemy);
//
//        IPlayActor actor3 = new Actor();
//        ((IAuthoringActor)actor3).setImageViewName("flagpole.png");
//        actor3.setY(100);
//        actor3.setX(800);
//
//
//
//
//        KeyTrigger triggerDown = new KeyTrigger(KeyCode.DOWN);
//       // TickTrigger trigger = new TickTrigger();
//        Action moveForwards = new GlideForward((Actor)actor1, 1.0);
//        Rule movingForwards = new Rule(triggerDown, moveForwards);
//        actor1.addRule(movingForwards);
//
//        KeyTrigger trigger1 = new KeyTrigger(KeyCode.RIGHT);
//        KeyTrigger trigger2 = new KeyTrigger(KeyCode.LEFT);
//        SideCollision trigger3 = new SideCollision((Actor)actor1,(Actor)actor2);
//        KeyTrigger trigger4 = new KeyTrigger(KeyCode.SPACE);
//        BottomCollision trigger5 = new BottomCollision((Actor)actor1,(Actor)actor2);
//        SideCollision trigger6 = new SideCollision((Actor)actor1,(Actor)actor3);
//        KeyTrigger trigger9 = new KeyTrigger(KeyCode.Z);
//        Action action9 = new ChangeAttribute((IPlayActor)actor1,AttributeType.POINTS,1);
//        Rule rule9 = new Rule(trigger9,action9);
//        actor1.addRule(rule9);
//
//        KeyTrigger triggerSpawn = new KeyTrigger(KeyCode.S);
//        TickTrigger bulletTick = new TickTrigger();
//        Action bulletAction = new GlideForward((Actor)spawnedActor,2.5);
//        Rule bulletRule = new Rule(bulletTick,bulletAction);
//        spawnedActor.addRule(bulletRule);
//        PhysicsEngine newPhysicsEngine = new PhysicsEngine();
//        ((Actor) spawnedActor).setPhysicsEngine(newPhysicsEngine);
//        
//        
//        
//        Action action1 = new MoveRight((Actor)actor1);
//        Action action2 = new MoveLeft((Actor)actor1);
//        Action action3 = new HorizontalStaticCollision((Actor)actor1);
//        Action action4 = new MoveUp((Actor)actor1);
//        Action action5 = new VerticalBounceCollision((Actor)actor1);
//        Action action6 = new WinGame((Actor)actor1);
//        Action actionSpawn = new Spawn((Actor) actor1, (Actor) spawnedActor);
//        Action actionNextLevel = new NextLevel((IPlayActor) actor1);
//        
//        Rule rule = new Rule(trigger1,action1);
//        Rule rule2 = new Rule(trigger2, action2);
//        Rule rule3 = new Rule(trigger3,action3);
//        Rule rule4 = new Rule(trigger4,action4);
//        Rule rule5 = new Rule(trigger5,action5);
//        Rule rule6 = new Rule(trigger6,action6);
//        Rule ruleSpawn = new Rule(triggerSpawn, actionSpawn);
//        Rule ruleNextLevel = new Rule(trigger6, actionNextLevel);
//
//        actor1.addRule(rule);
//        actor1.addRule(rule2);
//        actor1.addRule(rule3);
//        actor1.addRule(rule4);
//        actor1.addRule(rule5);
//        //actor1.addRule(rule6);
//        actor1.addRule(ruleSpawn);
//        actor1.addRule(ruleNextLevel);
//
//
//        TickTrigger intTick = new TickTrigger(5);
//        Action animate = new NextImage((Actor)actor1);
//        actor1.addRule(new Rule(intTick, animate));
//
//        actor1.addState(ActorState.MAIN);
//        actor1.addAttribute(new Attribute(AttributeType.POINTS, 0, (IPlayActor) actor1));
//        
//		ITrigger attreached = new AttributeReached((IGameElement)actor1, AttributeType.POINTS,5);
//		Action wingame = new WinGame((IPlayActor) actor1);
//		
//		actor1.addRule(new Rule(attreached,wingame));
//		
//		BottomCollision pls = new BottomCollision((Actor)actor1, (Actor)actor4); 
//		Action hello = new VerticalBounceCollision((Actor)actor1);
//		Rule lesads = new Rule(pls ,hello);
//		actor1.addRule(lesads);
//		
//		
//
//        List<Level> levels = new ArrayList<Level>();
//        Level level1 = new Level();
//        level1.setMyBackgroundImgName("mariobackground.png");
//        levels.add(level1);
//        level1.addActor(actor1);
//        level1.addActor(actor2);
//        level1.addActor(blocky);
//        level1.addActor((IAuthoringActor) enemy2);
//        level1.addActor((IAuthoringActor)actor3);
//        level1.addActor((IAuthoringActor) actor4);
//        
//        
//
//        Level level2 = new Level();
//        level2.setMyBackgroundImgName("vgnwpGb.png");
//        levels.add(level2);
//        level2.addActor(actor1);
//
//        int yposition = 200;
//        int xposition = 150;
//        for(int i=1; i<=7; i++){
//            Actor block = new Actor();
//            block.setName("salad");
//            block.setID(101);
//            block.setImageViewName("salad2.png");
//            block.setX(i*50+xposition);
//            block.setY(i*50+yposition);
//            BottomCollision b = new BottomCollision((Actor)actor1, block);
//            Action baction = new VerticalBounceCollision((Actor)actor1);
//            Rule brule = new Rule(b, baction);
//
//            TopCollision b2 = new TopCollision((Actor)actor1, block);
//            Action baction2 = new VerticalBounceCollision((Actor)actor1);
//            Rule brule2 = new Rule(b2, baction2);
//
//            actor1.addRule(brule);
//            actor1.addRule(brule2);
//            level2.addActor((IAuthoringActor) block);
//            yposition-=50;
//        }
//        
//        int yposition2 = 100;
//        int xposition2 = 800;
//        for(int j=1; j<=1; j++){
//            Actor block2 = new Actor();
//            block2.setName("salad");
//            block2.setID(102);
//            block2.setImageViewName("Next_Level_Jan2.png");
//            block2.setX(j*50+xposition2);
//            block2.setY(j*50+yposition2);
//            BottomCollision b3 = new BottomCollision((Actor)actor1, block2);
//            Action baction3 = new VerticalBounceCollision((Actor)actor1);
//            Rule brule3 = new Rule(b3, baction3);
//
//
//            TopCollision b4 = new TopCollision((Actor)actor1, block2);
//            Action baction4 = new VerticalBounceCollision((Actor)actor1);
//            Rule brule4 = new Rule(b4, baction4);
////
////
//////          Level level3 = new Level();
//////          level3.setMyBackgroundImgName("default_background.png");
//////          levels.add(level3);
//////          level3.addActor(actor1);
//          
//            Action actionNextLevel2 = new NextLevel((IPlayActor) actor1);
//            Rule ruleNextLevel2 = new Rule(b4, actionNextLevel2);
//            //ruleNextLevel2.setID(1);
//            actor1.addRule(ruleNextLevel2);
//            actor1.addRule(brule3);
//            actor1.addRule(brule4);
//            level2.addActor((IAuthoringActor) block2);
//            yposition2-=50;
//        }
//        
//
//        
////        IAuthoringActor block1 = (IAuthoringActor) new Actor();
////        block1.setImageViewName("block.png");
////        block1.setX(200);
////        block1.setY(200);
////        block1.setName("block1");
////        block1.setID(101);
////        level2.addActor(block1); 
////        
////        IAuthoringActor block2 = (IAuthoringActor) new Actor();
////        block2.setImageViewName("block.png");
////        block2.setX(300);
////        block2.setY(300);
////        block2.setName("block2");
////        block2.setID(102);
////        level2.addActor(block2); 
////        
////        IAuthoringActor block3 = (IAuthoringActor) new Actor();
////        block3.setImageViewName("block.png");
////        block3.setX(400);
////        block3.setY(200);
////        block3.setName("block3");
////        block3.setID(103);
////        level2.addActor(block3); 
//        
//        /**
//         * testing create actors
//         */
//        IAuthoringActor a = (IAuthoringActor) new Actor();
//        a.setID(10);
//        a.setImageViewName("pipes.png");
//        TickTrigger translatetick = new TickTrigger(45);
//        Action translateaction = new MoveRight((Actor)a);
//        a.addRule(new Rule(translatetick,translateaction));
//        TickTrigger newtick = new TickTrigger(220);
//        Action newaction = new CreateActor((Actor)actor1,(Actor)a,0.0,0.0);
//       // actor1.addRule(new Rule(newtick,newaction));
//        
//        
//        
//        for(int i=0; i<=25; i++){
//            Actor floor = new Actor();
//            floor.setName("floor");
//            floor.setID(5);
//            floor.setImageViewName("square.png");
////            floor.addState(ActorState.INVISIBLE);
//            floor.setX(i*50+i);
//            floor.setY(500-floor.getBounds().getHeight());
//            BottomCollision b = new BottomCollision((Actor)actor1, floor);
//            BottomCollision b2 = new BottomCollision((Actor)actor2, floor);
//            BottomCollision b3 = new BottomCollision((Actor)actor3, floor);
//            BottomCollision b4 = new BottomCollision((Actor)blocky, floor);
//            BottomCollision b5 = new BottomCollision((Actor)enemy2, floor);
//            
//            Action baction = new VerticalStaticCollision((Actor)actor1);
//            Action baction2 = new VerticalStaticCollision((Actor)actor2);
//            Action baction3 = new VerticalStaticCollision((Actor)actor3);
//            Action baction4 = new VerticalStaticCollision((Actor)blocky);
//            Action baction5 = new VerticalStaticCollision((Actor)enemy2);
//
//            Rule brule = new Rule(b, baction);
//            Rule brule2 = new Rule(b2, baction2);
//            Rule brule3 = new Rule(b3, baction3);
//            Rule brule4 = new Rule (b4, baction4);
//            Rule brule5 = new Rule (b5, baction5);
//
//            actor1.addRule(brule);
//            actor2.addRule(brule2);
//            ((IAuthoringActor)actor3).addRule(brule3);
//            blocky.addRule(brule4);
//            enemy2.addRule(brule5);
//
//            level1.addActor((IAuthoringActor)floor);
//            level2.addActor((IAuthoringActor) floor);
//            //level3.addActor((IAuthoringActor)floor);
//        }
//
//        Group group = new Group();
//        Scene scene = new Scene(group);
//        Game model = new Game(info, levels);
//        model.setHUDInfoFile("a.txt");
//        
//        CreatorController c = new CreatorController(model);
//        c.saveForEditing(new File("gamefiles/test2.xml"));
//        ParallelCamera camera = new ParallelCamera();
//        GameScreen view = new GameScreen(camera);
//
//        GameController controller = new GameController(model);
//        controller.setGame(model);
//        controller.setGameView(view);
//
//        SubScene sub = view.getScene();
//        sub.fillProperty().set(Color.BLUE);
//        group.getChildren().add(sub);
//
//        Stage stage = new Stage();
//        stage.setWidth(800);
//        stage.setHeight(600);
//
//        sub.setCamera(camera);
//        stage.setScene(scene);
//        stage.show();
//        controller.initialize(0);
//        
//        
//    }
//
//}