package games;

import gamedata.controller.CreatorController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.ILevel;
import gameengine.controller.Level;
import gameengine.model.Actions.*;
import gameengine.model.Actor;
import gameengine.model.ActorState;
import gameengine.model.Attribute;
import gameengine.model.AttributeType;
import gameengine.model.Rule;
import gameengine.model.Triggers.*;
import gameplayer.controller.GameController;
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

        List<ILevel> levels = new ArrayList<>();

        Level level1 = new Level();
        level1.setMyScrollingDirection("Vertically");
        level1.setMyBackgroundImgName("doodlebackground.png");
        levels.add(level1);
        
        Actor player = new Actor();
        player.addState(ActorState.MAIN);
        player.setID(1);
        player.setImageViewName("doodle_right.png");
        player.addSpriteImage("doodle_left.png");
        level1.addActor(player);
       
        Random r = new Random();
        for(int i=0; i<6; i++){
            Actor greenplatform = new Actor();
            greenplatform.setImageViewName("green_platform.png");
            greenplatform.setID(2);
            greenplatform.setX(0 + (900) * r.nextDouble());
            greenplatform.setY(i*90);
            level1.addActor(greenplatform);
            
            player.addRule(new Rule(new BottomCollision(player,greenplatform),new VerticalBounceCollision(player)));
            
        }
        
        player.addRule(new Rule(new KeyTrigger(KeyCode.SPACE), new MoveUp(player)));
        player.addRule(new Rule(new KeyTrigger(KeyCode.RIGHT), new MoveRight(player)));
        player.addRule(new Rule(new KeyTrigger(KeyCode.LEFT), new MoveLeft(player)));
        player.addRule(new Rule(new TickTrigger(), new ApplyPhysics(player)));
        

        Group group = new Group();
        Scene scene = new Scene(group);

        Game model = new Game(info, levels);
        CreatorController c = new CreatorController(model);
        c.saveForEditing(new File("gamefiles/DoodleJump.xml"));
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
