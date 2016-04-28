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
import gameengine.model.Rule;
import gameengine.model.Triggers.*;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author blakekaplan
 */
public class TowerDefense extends Application {
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
        info.setName("Space");

        List<Level> levels = new ArrayList<>();

        Level level1 = new Level();
        level1.setMyBackgroundImgName("space.png");
        levels.add(level1);
        
        
        Actor floor = new Actor();
        floor.setY(100);
        floor.setID(1);
        floor.setImageViewName("rect.png");
        
        Actor player = new Actor();
        player.setX(0);
        player.setY(0);
        player.setID(2);
        floor.setImageViewName("spaceship.png");
        player.addRule(new Rule(new KeyTrigger(KeyCode.RIGHT),new MoveRight(player)));
        player.addRule(new Rule(new KeyTrigger(KeyCode.LEFT),new MoveLeft(player)));
        player.addRule(new Rule(new TickTrigger(), new ApplyPhysics(player)));

        
        Actor projectile = new Actor();
        projectile.setID(3);
        projectile.setImageViewName("fireball.png");
        projectile.addRule(new Rule(new TickTrigger(), new GlideUp(projectile,3.0)));
        
        player.addRule(new Rule(new KeyTrigger(KeyCode.SPACE), new Spawn(player,projectile)));


        
        Actor enemy = new Actor();
        enemy.setID(4);
        enemy.setImageViewName("alien.png");
        enemy.addRule(new Rule(new TickTrigger(), new GlideDown(enemy,1.5)));
        enemy.addRule(new Rule(new BottomCollision(enemy, projectile), new Destroy(enemy)));
        //enemy.addRule(new Rule(new BottomCollision(enemy, floor), new LoseGame(player)));
        
        player.addRule(new Rule(new TopCollision(player, enemy), new Destroy(player)));



//        for(int count = 0 ; count < 6; count++){
//        	Actor enemySpawner  = new Actor();
//            enemySpawner.setID(20);
//            enemySpawner.setX(400);
//            enemySpawner.setY(count*200+0);
//            enemySpawner.setImageViewName("salad2.png");
//            enemySpawner.addRule(new Rule(new TickTrigger(150),  new Spawn(enemySpawner,enemy)));
//            level1.addActor(enemySpawner);
//        }
        

        for(int count = 0; count < 200; count++){
        	Actor fl = new Actor();
        	fl.setID(15);
        	fl.setY(500);
        	fl.setX(count*10);
        	fl.setImageViewName("Squirrel.png");
        	level1.addActor(fl);
            player.addRule(new Rule(new BottomCollision(player,fl), new VerticalStaticCollision(player))); 

        }


        level1.addRule(new Rule(new TickTrigger(75), new CreateActor(level1, enemy, 0, 800, 0.0, 0.0)));

        player.addState(ActorState.MAIN);


        level1.addActor(player);
        //level1.addActor(floor);

        Group group = new Group();
        Scene scene = new Scene(group);

        Game model = new Game(info, levels);
        model.setHUDInfoFile("a.txt");
        CreatorController c = new CreatorController(model);
        c.saveForEditing(new File("gamefiles/FlappyBird.xml"));
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