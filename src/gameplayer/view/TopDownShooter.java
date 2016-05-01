package gameplayer.view;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gamedata.controller.CreatorController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.Rule;
import gameengine.model.Actions.ApplyPhysics;
import gameengine.model.Actions.CreateActor;
import gameengine.model.Actions.Destroy;
import gameengine.model.Actions.GlideDown;
import gameengine.model.Actions.GlideForward;
import gameengine.model.Actions.GlideTarget;
import gameengine.model.Actions.GlideUp;
import gameengine.model.Actions.HorizontalBounceCollision;
import gameengine.model.Actions.HorizontalHeadingSwitch;
import gameengine.model.Actions.HorizontalStaticCollision;
import gameengine.model.Actions.LoseGame;
import gameengine.model.Actions.MoveLeft;
import gameengine.model.Actions.MoveRight;
import gameengine.model.Actions.Spawn;
import gameengine.model.Actions.VerticalBounceCollision;
import gameengine.model.Actions.VerticalHeadingSwitch;
import gameengine.model.Actions.VerticalStaticCollision;
import gameengine.model.Triggers.BottomCollision;
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

public class TopDownShooter extends Application {
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameInfo info = new GameInfo();
        info.setMyCurrentLevelNum(0);
        info.setName("TDS");

        List<Level> levels = new ArrayList<>();

        Level level1 = new Level();
        level1.setMyScrollingDirection("Vertically");
        level1.setMyBackgroundImgName("space.png");
        levels.add(level1);
        
        
        Actor player1 = new Actor();
        player1.addState(ActorState.MAIN);
        player1.setID(1);
        player1.setImageViewName("spaceship.png");
        player1.setX(10);
        level1.addActor(player1);
        
        player1.addRule(new Rule(new TickTrigger(), new ApplyPhysics(player1)));
        player1.addRule(new Rule(new KeyTrigger(KeyCode.RIGHT), new MoveRight(player1)));
        player1.addRule(new Rule(new KeyTrigger(KeyCode.LEFT), new MoveLeft(player1)));

        
        Actor floor  = new Actor();
        floor.setID(2);
        floor.setImageViewName("orange.png");
        floor.setY(500);
        level1.addActor(floor);
        
        player1.addRule(new Rule(new BottomCollision(player1,floor), new VerticalStaticCollision(player1)));

        Actor enemy = new Actor();
        enemy.setID(3);
        enemy.setImageViewName("goomba.png");
        enemy.addRule(new Rule(new TickTrigger(), new GlideDown(enemy,1.00)));
        
        Actor bullet = new Actor();
        bullet.setID(4);
        bullet.setImageViewName("fireball.png");
        bullet.addRule(new Rule(new TickTrigger(), new GlideUp(bullet, 2.5)));
        
        
        player1.addRule(new Rule(new KeyTrigger(KeyCode.SPACE), new Spawn(player1,bullet, 45)));
        
        bullet.addRule(new Rule(new TopCollision(bullet,enemy), new Destroy(bullet)));
        enemy.addRule(new Rule(new BottomCollision(enemy,bullet), new Destroy(enemy)));
        
        floor.addRule(new Rule(new TopCollision(floor, enemy), new LoseGame(level1)));


        
        level1.addRule(new Rule(new TickTrigger(75), new CreateActor(level1, enemy, 0.0, 600.0, 0.0, 0.0)));

       
        Group group = new Group();
        Scene scene = new Scene(group);

        Game model = new Game(info, levels);
        model.setHUDInfoFile("a.txt");
        CreatorController c = new CreatorController(model);
        c.saveForEditing(new File("gamefiles/TDS"
        		+ ".xml"));
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

    public static void main(String[] args){
        launch(args);
    }
}