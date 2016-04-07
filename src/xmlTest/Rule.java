package xmlTest;

public class Rule {
	
	private Trigger myTrigger;
	private Action myAction;
	
	public Rule(Trigger trigger, Action action) {
		myTrigger = trigger;
		myAction = action;
	}
	
	public Trigger getTrigger() {
		return myTrigger;
	}
	
	public void setTrigger (Trigger t) {
		myTrigger = t;
	}
	public Action getAction() {
		return myAction;
	}
	public void setAction (Action a) {
		myAction = a;
	}
	
}
