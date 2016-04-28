package gameengine.model.Actions;

import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

import authoringenvironment.view.ActorCopier;
import gameengine.controller.IGame;
import gameengine.model.Actor;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

public class CreateActor extends Action {
	Actor cloneActor;
	Double myX;
	Double myY;
	private Actor myActorToCopy;
    Double myMinX;
    Double myMaxX;
    Double myMinY;
    Double myMaxY;
    boolean isRandom;
	
	public CreateActor(IGameElement element, Actor toCopy, Double x, Double y, Boolean oneTime) {
		super(element, oneTime);
		myX = x;
		myY = y;
	}

    public CreateActor(IGameElement element, Actor toCopy, Double minX, Double maxX, Double minY, Double maxY, Boolean oneTime){
        super(element, oneTime);
        myActorToCopy = toCopy;
        isRandom = true;
        myMaxX = maxX;
        myMaxY = maxY;
        myMinX = minX;
        myMinY = minY;
    }

    @Override
    public Object[] getParameters(){
    	if(isRandom){
    		return new Object[]{getGameElement(),myActorToCopy,myMinY,myMaxX,myMinY,myMaxY, isOneTime()};
    	}
    	return new Object[]{getGameElement(),myActorToCopy,myX,myY, isOneTime()};
    }
    
	@Override
	public void execute() {
		ActorCopier copier = new ActorCopier(myActorToCopy);
		cloneActor = copier.makeCopy();
        if (isRandom){
            myX = myMinX + (myMaxX - myMinX) * Math.random();
            myY = myMinY + (myMaxY - myMinY) * Math.random();
        }
        cloneActor.setX(myX);
		cloneActor.setY(myY);
		getGameElement().changed();
		((Observable) getGameElement()).notifyObservers(Arrays.asList(new Object[]{"addActor",cloneActor}));
	}

	public Double getMyX() {
		return myX;
	}
	
	public Double getMyY() {
		return myY;
	}
	
	public Actor getMyActorToCopy() {
		return myActorToCopy;
	}
}
