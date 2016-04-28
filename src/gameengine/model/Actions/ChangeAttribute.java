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

    public ChangeAttribute(IGameElement actor1, AttributeType type, int change, Boolean oneTime){
        super(actor1, oneTime);
        myType = type;
        myChange = change;
    }
    
    @Override
    public Object[] getParameters(){
    	return new Object[]{getGameElement(),myType,myChange, isOneTime()};
    }

    @Override
    public void execute() {
        getGameElement().changeAttribute(myType,myChange);
    }

	public String getMyAttributeType() {
		return myType.toString();
	}
	
	public AttributeType getMyType() {
		return myType;
	}
	
	public int getMyValue() {
		return myChange;
	}
}
