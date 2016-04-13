package gameengine.model.Actions;

import gameengine.model.Actor;
import gameengine.model.Triggers.AttributeType;

/**
 * @author blakekaplan
 */
public class ChangeAttribute extends Action{

    private AttributeType myType;
    private int myChange;

    public ChangeAttribute(Actor assignedActor, AttributeType type, int change){
        super(assignedActor);
        myType = type;
        myChange = change;
    }

    @Override
    public void perform() {
        getMyActor().changeAttribute(myType,myChange);
    }
}
