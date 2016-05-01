package gameengine.model.Triggers;

/**
 * @author blakekaplan
 */
public class TickTrigger extends ITrigger {

	private static final String TICK = "Tick";
	private Integer myInterval;

	public TickTrigger() {
		myInterval = 1;
	}

	public TickTrigger(Integer interval) {
		myInterval = interval;
	}
	
	@Override
	public Object[] getParameters(){
		if(myInterval!=null){
			return new Object[]{myInterval};
		}
		return new Object[]{};
	}

	public int getMyInterval() {
		return myInterval;
	}

	@Override
	public String getMyKey() {
		return TICK;
	}

	/**
	 * Checks a boolean condition against the state of an actor
	 *
	 * @param otherTrigger
	 *            A trigger to check information against
	 * @return A boolean that says if the condition is true or false
	 */
	@Override
	public boolean evaluate(ITrigger otherTrigger) {
		TickTrigger otherTick = (TickTrigger) otherTrigger;
		if (otherTick.getMyInterval() % myInterval == 0)
			return true;
		return false;
	}
	
	public boolean equals(TickTrigger other){
		return myInterval == other.getMyInterval();
	}
}