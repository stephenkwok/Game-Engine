package authoringenvironment.view.behaviors;




import java.util.ArrayList;

import java.util.List;

import java.util.ResourceBundle;




import authoringenvironment.model.IAuthoringActor;

import authoringenvironment.view.ActorRule;

import gameengine.model.Triggers.ITrigger;




public class CollisionBehavior extends SelectActorBehavior {

private ITrigger myTrigger;




public CollisionBehavior(ActorRule myActorRule, String behaviorType, ResourceBundle myResources, 

IAuthoringActor myActor, List<IAuthoringActor> myActors) {

super(myActorRule, behaviorType, myResources, myActor, myActors);

}




@Override

public void setTriggerOrAction() {

setTrigger(this, myTrigger);

}




@Override

protected void createTriggerOrAction() {

List<Object> arguments = new ArrayList<>();

arguments.add(getMyActor());

arguments.add(getOtherActor());

myTrigger = getTriggerFactory().createNewTrigger(getBehaviorType(), arguments);

}




@Override

public boolean isTrigger() {

return true;

}

}