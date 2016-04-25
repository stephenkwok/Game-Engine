package gameengine.model.Actions;

import gameengine.model.AttributeType;
import gameengine.model.IPlayActor;

/**
 * @author blakekaplan
 */
public class ChangeAttribute extends Action {

	private AttributeType myType;
	private int myChange;

	public ChangeAttribute(IPlayActor actor1, AttributeType type, int change) {
		super(actor1);
		myType = type;
		myChange = change;
	}

	@Override
	public void perform() {
		getMyActor().changeAttribute(myType, myChange);
	}

	public String getMyAttributeType() {
		return myType.toString();
	}
}
