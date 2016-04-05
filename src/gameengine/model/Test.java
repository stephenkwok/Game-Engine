package gameengine.model;

import java.util.ArrayList;
import java.util.List;

import gameengine.actors.Actor;
import gameengine.actors.PowerUpActor;
import gameengine.controller.Action;
import gameengine.controller.Game;
import gameengine.controller.Level;

public class Test {

	public Test() {
		ClickTrigger myClickTrigger = new ClickTrigger();
		Actor myActor = new PowerUpActor();
		System.out.println(myActor.getX());
		System.out.println("****");
		List myDistance = new ArrayList<>();
		myDistance.add(55.0);

		Action moveLeft = new MoveLeft(myActor, myDistance);
		Rule myRule = new Rule(myClickTrigger, moveLeft);		
		myActor.addRule(myRule);
		myActor.performActionsFor(myClickTrigger);
		
		PowerUpActor mPA = new PowerUpActor();
	
		System.out.println(mPA.getMyActorType());
//		Level myLevel = new Level();
//		myLevel.addActor(myActor);	
//		List myLevels = new ArrayList<>();
//		myLevels.add(myLevel);
//		Game myGame = new Game(0, myLevels, "blah");	
//		myGame.handleTrigger(myClickTrigger);
		
		System.out.println(myActor.getX());
	}

}
