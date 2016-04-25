
// package gameengine.model;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import gameengine.controller.Action;
// import gameengine.controller.Actor;
// import gameengine.controller.Game;
// import gameengine.controller.Level;
// import gameengine.controller.PowerUpActor;
//
// public class Test {
//
// public Test() {
// ClickTrigger myClickTrigger = new ClickTrigger();
// Actor myActor = new Actor();
// System.out.println(myActor.getX());
// System.out.println("****");
// List myDistance = new ArrayList<>();
// myDistance.add(50.0);
//
// Action moveRight = new MoveRight(myActor, myDistance);
// Rule myRule = new Rule(myClickTrigger, moveRight);
// myActor.addRule(myRule);
// myActor.performActionsFor(myClickTrigger);
//
// PowerUpActor mPA = new PowerUpActor();
//
// System.out.println(mPA.getMyActorType());
//// Level myLevel = new Level();
//// myLevel.addActor(myActor);
//// List myLevels = new ArrayList<>();
//// myLevels.add(myLevel);
//// Game myGame = new Game(0, myLevels, "blah");
//// myGame.handleTrigger(myClickTrigger);
//
// System.out.println(myActor.getX());
// }
//
// }
