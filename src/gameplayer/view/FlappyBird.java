package gameplayer.view;

import gamedata.controller.CreatorController;
import gameengine.controller.Game;
import gameengine.controller.GameInfo;
import gameengine.controller.Level;
import gameengine.model.Actions.CreateActor;
import gameengine.model.Actions.MoveLeft;
import gameengine.model.Actor;
import gameengine.model.Rule;
import gameengine.model.Triggers.TickTrigger;
import gameplayer.controller.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author blakekaplan
 */
public class FlappyBird extends Application {
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
        info.setName("Flappy Bird");

        List<Level> levels = new ArrayList<>();

        Level level1 = new Level();
        level1.setMyBackgroundImgName("flappybackground.png");
        levels.add(level1);

        Actor pipeTop = new Actor();
        pipeTop.setImageViewName("toppipe.png");
        pipeTop.addRule(new Rule(new TickTrigger(10), new MoveLeft(pipeTop)));

        Actor pipeBottom = new Actor();
        pipeBottom.setImageViewName("bottompipe.png");
        pipeBottom.addRule(new Rule(new TickTrigger(10), new MoveLeft(pipeBottom)));

        Actor invisibleLine = new Actor();
        invisibleLine.setImageViewName("redball.png");
        invisibleLine.addRule(new Rule(new TickTrigger(10), new MoveLeft(invisibleLine)));

//        level1.addActor(pipeTop);
//        level1.addActor(pipeBottom);
//        level1.addActor(invisibleLine);

        level1.addRule(new Rule(new TickTrigger(100), new CreateActor(level1, pipeTop, 1024, 1024, -100, 0)));
        level1.addRule(new Rule(new TickTrigger(100), new CreateActor(level1, pipeBottom, 1024, 1024, 300, 400)));

        Group group = new Group();
        Scene scene = new Scene(group);

        Game model = new Game(info, levels);
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
