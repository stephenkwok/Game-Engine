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
	//ActorCopier copier;
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
		//copier = new ActorCopier(toCopy);
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

	public double getMyX() {
		return myX;
	}
	
	public double getMyY() {
		return myY;
	}
	
	public Actor getMyActorToCopy() {
		return myActorToCopy;
	}
}
