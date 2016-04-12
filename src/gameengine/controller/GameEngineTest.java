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
import gameengine.model.Triggers.ClickTrigger;
import gameengine.model.Triggers.TickTrigger;

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
        //  Moves Right 50 on Click
        //  Move Right 70 on Tick
        Actor actor1 = new Actor();
        actor1.setMyID(1);
        ITrigger trigger1 = new ClickTrigger();
        List<Object> args1 = new ArrayList<>();
        args1.add(50.0);
        MoveRight action1 = new MoveRight(actor1);
        ITrigger trigger3 = new TickTrigger();
        List<Object> args3 = new ArrayList<>();
        args3.add(70.0);
        Action action3 = new MoveUp(actor1);
        actor1.addRule(new Rule(trigger1, action1));
        actor1.addRule(new Rule(trigger3, action3));

        //Create Actor 2
        //Rules:
        //  Moves Right 50 on Tick
        Actor actor2 = new Actor();
        actor2.setMyID(2);
        ITrigger trigger2 = new TickTrigger();
        List<Object> args2 = new ArrayList<>();
        args2.add(50.0);
        Action action2 = new MoveRight(actor2);
        actor2.addRule(new Rule(trigger2, action2));

        //Puts Actors in Level
        myLevel.addActor(actor1);
        myLevel.addActor(actor2);

        //Initialize Game
        Game myGame = new Game(new GameInfo(), myLevels);

        //Prints Initial State
        PhysicsEngine physicsEngine = new PhysicsEngine();
        List<Actor> actors = (List<Actor>) myGame.getActors();
        for (Actor actor : actors){
           System.out.printf(actor.getMyID() + ", " + actor.getX() + ", " + actor.getY() + "\t\t");
           actor.setEngine(physicsEngine);
           actor.setInAir(false);
           actor.setVeloX(2);
        }
        System.out.println();

        //Testing Actions directly. Should eventually be modified to test Triggers
        //Only use one at a time, comment out rest
        for (Actor actor: actors){
//        	MoveRight mr = new MoveRight(actor);  //works
//        	mr.perform();
        	MoveUp ml = new MoveUp(actor);
        	ml.perform();
        	physicsEngine.tick(actor);
        	physicsEngine.tick(actor);
        	physicsEngine.tick(actor);
        	physicsEngine.tick(actor);
//        	MoveUp mp = new MoveUp(actor);
//        	mp.perform();
//        	GlideLeft gl = new GlideLeft(actor);
//        	gl.perform();
        }

        //Prints Final State

        actors = (List<Actor>) myGame.getActors();
        for (Actor actor : actors){
            System.out.printf(actor.getMyID() + ", " + actor.getX() + ", " + actor.getY() + "\t\t");
       
        }
   }
}