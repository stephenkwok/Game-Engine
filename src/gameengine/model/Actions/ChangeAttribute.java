package gameengine.model.Actions;

import gameengine.model.AttributeType;
import gameengine.model.IGameElement;
import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public class ChangeAttribute extends Action {

	private AttributeType myType;
	private int myChange;


    public ChangeAttribute(IGameElement actor1, AttributeType type, int change){
        super(actor1);
        myType = type;
        myChange = change;
    }

    @Override
    public void perform() {
        getGameElement().changeAttribute(myType,myChange);
    }

	public String getMyAttributeType() {
		return myType.toString();
	}
}
