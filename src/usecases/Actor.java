package usecases;

import gameengine.model.IActor;
import gameengine.model.IRule;
import gameengine.model.ITrigger;
import javafx.scene.image.ImageView;

import java.util.*;

/**
 * This class defines the logic for an Actor object.
 * Each interactive element in the Game is an Actor. Actors contains a set of rules that are contained
 * within the myRules map. When provided with a Trigger object, all actions associated with a particular Trigger
 * are executed. The Actor also extends the ImageView class so they will also be visual elements.
 *
 * @author blakekaplan
 */

public class Actor extends ImageView implements IActor {

    private int health;
    private int points;
    private int myID;

    private Map<ITrigger, List<Action>> myRules;

    /**
     * Converts a list of Rules to a map of trigger to list of Actions
     *
     * @param id The Actor's ID number
     */
    public Actor(int id) {
        myRules = new HashMap<>();
        myID = id;
    }

    /**
     * Provides the Actor's health
     *
     * @return The Actor's health
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Provides the Actor's number of points
     *
     * @return The Actor's number of points
     */
    @Override
    public int getPoints() {
        return points;
    }

    /**
     * Moves the Actor based on a provided distance and direction
     *
     * @param distance  The distance to move the Actor
     * @param direction The direction that the Actor should move in
     */
    @Override
    public void move(double distance, double direction) {
        setX(distance * Math.cos(direction));
        setY(distance * Math.sin(direction));
    }

    /**
     * Changes the Actor's number of points
     *
     * @param change The desired change in the Actor's number of points
     */
    @Override
    public void changePoints(int change) {
        points += change;
    }

    /**
     * Changes the Actor's health
     *
     * @param change The desired change in the Actor's amount of health
     */
    @Override
    public void changeHealth(int change) {
        health += change;
    }

    /**
     * Calls the appropriate sequence of Actions based on a provided Trigger
     *
     * @param myTrigger A Trigger object that calls for an appropriate response
     */
    @Override
    public void performActionsFor(ITrigger myTrigger) {
        List<Action> myActions = myRules.get(myTrigger);
        for (Action myAction : myActions) {
            myAction.perform();
        }
    }

    /**
     * Adds a new Rule to the Actor
     *
     * @param newRule The Rule to be added to the Actor
     */
    @Override
    public void addRule(IRule newRule) {
        if (myRules.containsKey(newRule.getTrigger())) {
            List<Action> myActions = myRules.get(newRule.getTrigger());
            myActions.add(newRule.getAction());
            myRules.put(newRule.getTrigger(), myActions);
        } else {
            List<Action> myActions = new ArrayList<>();
            myActions.add(newRule.getAction());
            myRules.put(newRule.getTrigger(), myActions);
        }
    }

    /**
     * Provides a list of Triggers that the Actor responds to
     *
     * @return The list of Triggers that the Actor responds to
     */
    public Set<ITrigger> getTriggers() {
        return myRules.keySet();
    }

    /**
     * Provides the Actor's ID number
     *
     * @return The Actor's ID number
     */
    @Override
    public int getID() {
        return myID;
    }

	@Override
	public double getXVelo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYVelo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setXPos(double updateXPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setYPos(double updateYPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXVelo(double updateXVelo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setYVelo(double updateYVelo) {
		// TODO Auto-generated method stub
		
	}
}
