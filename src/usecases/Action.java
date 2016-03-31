package usecases;

import gameengine.model.IAction;

/**
 * @author blakekaplan
 */
public abstract class Action implements IAction {

    private Actor myActor;

    /**
     * Creates a reference to the Actor that will be changed
     *
     * @param assignedActor The Actor that will be changed
     */
    public Action(Actor assignedActor) {
        myActor = assignedActor;
    }

    /**
     * The perform command that will implement the functionality unique to each Action type
     */
    @Override
    public abstract void perform();

    /**
     * Provides the Actor linked to the Action
     *
     * @return The Actor that the Action references
     */
    public Actor getActor() {
        return myActor;
    }
}
