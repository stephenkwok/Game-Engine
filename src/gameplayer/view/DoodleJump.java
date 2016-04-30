package gameplayer.view;


import gamedata.controller.CreatorController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actions.*;
import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.PhysicsEngine;
import gameengine.model.Actions.ShiftScene;
import gameengine.model.Rule;
import gameengine.model.Triggers.*;
import gameplayer.controller.GameController;
import gameplayer.controller.PlayType;
import gameplayer.view.GameScreen;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import authoringenvironment.model.ActorCopier;

public class DoodleJump extends Application {
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
        info.setName("Doodle Jump");

        List<Level> levels = new ArrayList<>();

        Level level1 = new Level();
        level1.setMyScrollingDirection("Vertically");
        level1.setMyBackgroundImgName("doodle_background.png");
        levels.add(level1);
        
        Actor player = new Actor();
        player.addState(ActorState.MAIN);
        player.setID(1);
        player.setImageViewName("doodle_right.png");
        player.addSpriteImage("doodle_left.png");
        level1.addActor(player);
       
       
        Actor greenplatform = new Actor();
        greenplatform.setImageViewName("green_platform.png");
        greenplatform.setID(2);
        
        //player.addRule(new Rule(new TopCollision(player,greenplatform),new GlideUp(player, greenplatform.getBounds().getHeight()*-.4)));
        player.addRule(new Rule(new BottomCollision(player,greenplatform),new VerticalBounceCollision(player)));
        player.addRule(new Rule(new BottomCollision(player,greenplatform, true),new ShiftScene(level1,"Down",50.0)));
        player.addRule(new Rule(new BottomCollision(player,greenplatform, true),new CreateActor(player,greenplatform,200.0,700.0,0.0,0.0)));
        
        ActorCopier copier = new ActorCopier(greenplatform);
        
        Random r = new Random();
        for(int i=1; i<14; i++){
            Actor greenplatform1 = copier.makeCopy();
            greenplatform1.setPhysicsEngine(new PhysicsEngine());
            greenplatform1.setX(100 + (700) * r.nextDouble());
            greenplatform1.setY(i*40);

            
            level1.addActor(greenplatform1);
            
           
            
        }
        
        
        player.addRule(new Rule(new KeyTrigger(KeyCode.SPACE), new MoveUp(player)));
        player.addRule(new Rule(new KeyTrigger(KeyCode.RIGHT), new MoveRight(player)));
        player.addRule(new Rule(new KeyTrigger(KeyCode.LEFT), new MoveLeft(player)));
        player.addRule(new Rule(new TickTrigger(), new ApplyPhysics(player)));
        

        Group group = new Group();
        Scene scene = new Scene(group);

        Game model = new Game(info, levels);
        model.setHUDInfoFile("a.txt");
        CreatorController c = new CreatorController(model);
        c.saveForEditing(new File("gamefiles/DoodleJump.xml"));
        ParallelCamera camera = new ParallelCamera();
        GameScreen view = new GameScreen(camera);

        GameController controller = new GameController(model, PlayType.PREVIEW);
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