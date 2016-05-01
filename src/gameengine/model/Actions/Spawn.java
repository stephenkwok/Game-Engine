package gameengine.model.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import authoringenvironment.model.ActorCopier;
import gameengine.model.Actor;
import gameengine.model.IPlayActor;


public class Spawn extends ActorAction{
	private Double ZERO = 0.0;
	private IPlayActor mySpawnedActor;
	private Double spawnAngle ;
	

	
	public Spawn(Actor actor1, Actor spawnedActor, Double angle) {
		super(actor1);
		mySpawnedActor  = (IPlayActor) spawnedActor;
		spawnAngle = angle;
	}
	
	public double getSpawnAngle(){
		if(spawnAngle==null){
			spawnAngle = ZERO;
		}
		return spawnAngle;
	}

	@Override
	public Object[] getParameters(){
		return new Object[]{getMyActor(),mySpawnedActor, spawnAngle};
	}
	
	public void perform() {
		
		ActorCopier myActorCopier = new ActorCopier((Actor)mySpawnedActor);
		Actor clone = myActorCopier.makeCopy();
		clone.setHeading(getMyActor().getHeading());
		double halfWidth = getMyActor().getBounds().getWidth()/2;
		double halfHeight = getMyActor().getBounds().getHeight()/2;
		double startingXPos = getMyActor().getBounds().getMinX() +getMyActor().getBounds().getWidth()/2;
		double startingYPos = getMyActor().getBounds().getMinY() + getMyActor().getBounds().getHeight()/2;
		double y_Offset = Math.sin(Math.toRadians(spawnAngle));
		double x_Offset = Math.cos(Math.toRadians(spawnAngle));		
		clone.setX(startingXPos+(x_Offset*halfWidth)-(clone.getBounds().getWidth()/2));
		clone.setY(startingYPos-(y_Offset*halfHeight)-(clone.getBounds().getHeight()/2));
		clone.setHeading(spawnAngle);

		getMyActor().changed();
		List<Object> myList = new ArrayList<>();
		myList.add("addActor");
		myList.add(clone);

		((Observable) getMyActor()).notifyObservers(myList);
	}

	public IPlayActor getMySpawnedActor() {
		return this.mySpawnedActor;
	}
}
