package usecases;

import gameengine.model.IActor;
import gameengine.model.IRule;
import gameengine.model.ITrigger;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Actor extends ImageView implements IActor {

    private int health;
    private int points;
    private Map<ITrigger, List<Action>> myRules;

    /**
     * Converts a list of Rules to a map of trigger to list of Actions
     *
     * @param rules A list of Rule objects to define the Actor's behavior
     */
    public Actor(List<IRule> rules) {
        myRules = new HashMap<>();
        for (IRule rule : rules) {
            if (myRules.containsKey(rule.getTrigger())) {
                List<Action> myActions = myRules.get(rule.getTrigger());
                myActions.add(rule.getAction());
                myRules.put(rule.getTrigger(), myActions);
            } else {
                List<Action> myActions = new ArrayList<>();
                myActions.add(rule.getAction());
                myRules.put(rule.getTrigger(), myActions);
            }
        }
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
}
