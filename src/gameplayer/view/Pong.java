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
import gameengine.model.Actions.GlideDown;
import gameengine.model.Actions.GlideForward;
import gameengine.model.Actions.GlideTarget;
import gameengine.model.Actions.GlideUp;
import gameengine.model.Actions.HorizontalBounceCollision;
import gameengine.model.Actions.HorizontalHeadingSwitch;
import gameengine.model.Actions.HorizontalStaticCollision;
import gameengine.model.Actions.VerticalBounceCollision;
import gameengine.model.Actions.VerticalHeadingSwitch;
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

public class Pong extends Application {
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
        info.setName("Pong");

        List<Level> levels = new ArrayList<>();

        Level level1 = new Level();
        level1.setMyScrollingDirection("Horizontally");
        level1.setMyBackgroundImgName("blackscreen.png");
        levels.add(level1);
        
        
        Actor player1 = new Actor();
        player1.addState(ActorState.MAIN);
        player1.setID(1);
        player1.setImageViewName("whiterectangle.png");
        player1.setX(10);
        player1.setY(100);
        level1.addActor(player1);
        
        player1.addRule(new Rule(new KeyTrigger(KeyCode.RIGHT), new GlideDown(player1, 25.0)));
        player1.addRule(new Rule(new KeyTrigger(KeyCode.LEFT), new GlideUp(player1, 25.0)));

        
        Actor player2 = new Actor();
        player2.addState(ActorState.MAIN);
        player2.setID(2);
        player2.setImageViewName("whiterectangle.png");
        player2.setX(800-player2.getBounds().getWidth()-10);
        level1.addActor(player2);
        
//        player2.addRule(new Rule(new KeyTrigger(KeyCode.D), new GlideDown(player2, 25.0)));
//        player2.addRule(new Rule(new KeyTrigger(KeyCode.A), new GlideUp(player2, 25.0)));
//        player2.addRule(new Rule(new KeyTrigger(KeyCode.LEFT), new GlideLeft(player2, 2.0)));
        

       
        
        Actor leftSide = new Actor();
        leftSide.setID(3);
        leftSide.setImageViewName("gameside.png");
        leftSide.setX(0);
        level1.addActor(leftSide);
        
        Actor rightSide = new Actor();
        rightSide.setID(4);
        rightSide.setImageViewName("gameside.png");
        rightSide.setX(800);
        level1.addActor(rightSide);
        
        Actor ball  = new Actor();
        ball.setID(5);
        ball.setImageViewName("whitecircle.png");
        ball.setX(400);
        ball.setY(400);
        ball.setHeading(140);
        level1.addActor(ball);
        ball.addRule(new Rule(new TickTrigger(), new GlideForward(ball, 6.0)));
        
        player2.addRule(new Rule(new TickTrigger(), new GlideTarget(player2, 15.0, ball)));

        
        
        Actor boundary = new Actor();
        boundary.setID(8);
        boundary.setImageViewName("gameside.png");
        boundary.setX(700);
        level1.addActor(boundary);
        
        player2.addRule(new Rule(new SideCollision(player2, boundary), new HorizontalStaticCollision(player2)));
        
        
        
        Actor bottomEdge = new Actor();
        bottomEdge.setID(6);
        bottomEdge.setImageViewName("rect.png");
        bottomEdge.setY(500);
        level1.addActor(bottomEdge);
        
        Actor topEdge = new Actor();
        topEdge.setID(7);
        topEdge.setImageViewName("rect.png");
        topEdge.setY(0-topEdge.getBounds().getHeight());
        level1.addActor(topEdge);
        
        ball.addRule(new Rule(new TopCollision(ball, topEdge), new VerticalBounceCollision(ball)));
        ball.addRule(new Rule(new TopCollision(ball, topEdge), new VerticalHeadingSwitch(ball)));        
        ball.addRule(new Rule(new BottomCollision(ball, bottomEdge), new VerticalBounceCollision(ball)));
        ball.addRule(new Rule(new BottomCollision(ball, bottomEdge), new VerticalHeadingSwitch(ball)));
        
        ball.addRule(new Rule(new TopCollision(ball, player1), new VerticalHeadingSwitch(ball))); 
        ball.addRule(new Rule(new TopCollision(ball, player2), new VerticalHeadingSwitch(ball)));        
        ball.addRule(new Rule(new BottomCollision(ball, player1), new VerticalHeadingSwitch(ball))); 
        ball.addRule(new Rule(new BottomCollision(ball, player2), new VerticalHeadingSwitch(ball)));

        
        ball.addRule(new Rule(new SideCollision(ball, player1), new HorizontalBounceCollision(ball)));
        ball.addRule(new Rule(new SideCollision(ball, player2), new HorizontalBounceCollision(ball)));
        ball.addRule(new Rule(new SideCollision(ball, player1), new HorizontalHeadingSwitch(ball)));
        ball.addRule(new Rule(new SideCollision(ball, player2), new HorizontalHeadingSwitch(ball)));

       
        Group group = new Group();
        Scene scene = new Scene(group);

        Game model = new Game(info, levels);
        model.setHUDInfoFile("a.txt");
        CreatorController c = new CreatorController(model);
        c.saveForEditing(new File("gamefiles/Pong.xml"));
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