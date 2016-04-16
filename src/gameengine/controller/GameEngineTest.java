package gameengine.controller;

import java.util.ArrayList;
import java.util.List;

import gameengine.model.Actor;
import gameengine.model.ITrigger;
import gameengine.model.PhysicsEngine;
import gameengine.model.Rule;
import gameengine.model.Actions.Action;
import gameengine.model.Actions.GlideLeft;
import gameengine.model.Actions.MoveLeft;
import gameengine.model.Actions.MoveRight;
import gameengine.model.Actions.MoveUp;
import gameengine.model.Actions.VerticalStaticCollision;
import gameengine.model.Triggers.BottomCollision;
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.KeyTrigger;
import gameengine.model.Triggers.TickTrigger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * @author blakekaplan
 */
public class GameEngineTest {

    public static void main(String[] args) {

        //Initialize Level
        List<Level> myLevels = new ArrayList<>();
        Level myLevel = new Level();
        myLevels.add(myLevel);

        //Create Actor 1
        //Rules:
        //Moves right on right arrow key press
        
        Actor floor = new Actor();
        floor.setMyImageViewName("square.png");
        floor.setImageView(new ImageView(new Image(GameEngineTest.class.getClassLoader().getResourceAsStream("square.png"))));
        
        Actor actor1 = new Actor();
        actor1.setMyImageViewName("redball.png");
        actor1.setImageView(new ImageView(new Image(GameEngineTest.class.getClassLoader().getResourceAsStream("redball.png"))));
        
        actor1.getImageView().setFitHeight(50);
        actor1.getImageView().setFitHeight(50);

        KeyTrigger rightTrigger  = new KeyTrigger(KeyCode.RIGHT);
        MoveRight action1 = new MoveRight(actor1);
        
        BottomCollision bc = new BottomCollision(actor1, floor);
        VerticalStaticCollision vsc = new VerticalStaticCollision(actor1);
        
        KeyTrigger space = new KeyTrigger(KeyCode.SPACE);
        MoveUp jump = new MoveUp(actor1);
        
        Rule moveRight = new Rule(rightTrigger, action1);
        Rule hitFloor  = new Rule(bc,vsc);
        Rule jumpUp    = new Rule(space,jump);
        
        actor1.setInAir(true);
        
        actor1.addRule(moveRight);
        actor1.addRule(hitFloor);

        //Puts Actors in Level
        myLevel.addActor(actor1);
        

        //Initialize Game
        Game myGame = new Game(new GameInfo(), myLevels);

        //Prints Initial State
        PhysicsEngine physicsEngine = new PhysicsEngine();
        List<Actor> actors = (List<Actor>) myGame.getActors();
        for (Actor actor : actors){
           System.out.printf(actor.getMyName() + ", X: " + actor.getX() + ", Y: " + actor.getY() + "\t\t");
           actor.setEngine(physicsEngine);
        }
        System.out.println();

        myGame.handleTrigger(rightTrigger);
        
        //Prints Final State

        actors = (List<Actor>) myGame.getActors();
        for (Actor actor : actors){
            System.out.printf(actor.getMyName() + ", X: " + actor.getX() + ", Y:  " + actor.getY() + "\t\t");
       
        }
   }
}