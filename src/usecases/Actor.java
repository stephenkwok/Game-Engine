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

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void move(double distance, double direction) {
        setX(distance * Math.cos(direction));
        setY(distance * Math.sin(direction));
    }

    @Override
    public void changePoints(int change) {
        points += change;
    }

    @Override
    public void changeHealth(int change) {
        health += change;
    }

    @Override
    public void performActionsFor(ITrigger myTrigger) {
        List<Action> myActions = myRules.get(myTrigger);
        for (Action myAction : myActions) {
            myAction.perform();
        }
    }
}
