package xmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Actor {
	
    private static final String DEFAULT_NAME = "Default Name";
    private static final String DEFAULT_IMAGE_NAME = "default_actor.jpg";
	
	//private int myHealth;
	//private int myPoints;
	private int myId;
	private String myName;
	private String myImgName;
	private Map<String, List<Action>> myRules;
	
	public Actor () {
		this.setMyName(DEFAULT_NAME);
		this.setMyRules(new HashMap<>());
		this.setMyImgName(DEFAULT_IMAGE_NAME);
	}

	public int getMyId() {
		return myId;
	}

	public void setMyId(int myId) {
		this.myId = myId;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyImgName() {
		return myImgName;
	}

	public void setMyImgName(String myImgName) {
		this.myImgName = myImgName;
	}

	public Map<String, List<Action>> getMyRules() {
		return myRules;
	}

	public void setMyRules(Map<String, List<Action>> myRules) {
		this.myRules = myRules;
	}

	public void addRule(Rule newRule) {
        if (myRules.containsKey(newRule.getTrigger().getTriggerName())) {
            List<Action> myActions = myRules.get(newRule.getTrigger().getTriggerName());
            myActions.add(newRule.getAction());
            myRules.put(newRule.getTrigger().getTriggerName(), myActions);
        } else {
            List<Action> myActions = new ArrayList<>();
            myActions.add(newRule.getAction());
            myRules.put(newRule.getTrigger().getTriggerName(), myActions);
        }
    }
	
}
