package gameengine.model.Actions;

import java.util.Arrays;
import java.util.Observable;

import authoringenvironment.model.ActorCopier;
import gameengine.model.Actor;
import gameengine.model.IGameElement;

public class CreateActor extends Action {
	Actor cloneActor;
	double myX;
	double myY;
	private Actor myActorToCopy;
    double myMinX;
    double myMaxX;
    double myMinY;
    double myMaxY;
    boolean isRandom;
	
	public CreateActor(IGameElement element, Actor toCopy, double x, double y) {
		super(element);
		myActorToCopy = toCopy;
		myX = x;
		myY = y;
	}

    public CreateActor(IGameElement element, Actor toCopy, double minX, double maxX, double minY, double maxY){
        super(element);
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
    		return new Object[]{getGameElement(),myActorToCopy,myMinY,myMaxX,myMinY,myMaxY};
    	}
    	return new Object[]{getGameElement(),myActorToCopy,myX,myY};
    }
    
	@Override
	public void perform() {
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
