package gameengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.ITrigger;

public class RuleManager {
	private Map<String, List<Rule>> myRules = new HashMap<String,List<Rule>>();
	
	public void addRule(Rule newRule){
		 if (myRules.containsKey(newRule.getMyTrigger().getMyKey())) {
	            List<Rule> myBehaviors = myRules.get(newRule.getMyTrigger().getMyKey());
	            myBehaviors.add(newRule);
	            myRules.put(newRule.getMyTrigger().getMyKey(), myBehaviors);
	        } else {
	            List<Rule> myBehaviors = new ArrayList<>();
	            myBehaviors.add(newRule);
	            myRules.put(newRule.getMyTrigger().getMyKey(), myBehaviors);
	        }
	}
	
    public void handleTrigger(ITrigger myTrigger) {
    	if(myRules.containsKey(myTrigger.getMyKey())){
            List<Rule> myBehaviors = myRules.get(myTrigger.getMyKey());
            for (Rule myRule : myBehaviors) {
                if (myRule.getMyTrigger().evaluate(myTrigger)) myRule.getMyAction().perform();
            }
    	}
    }
    
    public Map<String, List<Rule>> getRules(){
    	return myRules;
    }
    
}
