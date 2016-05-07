// This entire file is part of my masterpiece.
// Colette Torres 
/**
 * This code is purposed to handle a game element's maintenance of rules, such as by allowing a rule to be added to or removed from
 * a game element, or accessing a game element's rules and checking the game element for a rule containing a signaled trigger
 * so as to perform the linked action for that game element.
 * 
 * I think this class shows good design because it was part of a refactoring decision to extract details pertaining
 * to the management of rules from the Actor class, so as to better adhere to the Single Responsibility Principle.
 * Previously, the implementation for adding, removing, accessing, etc. rules was contained in the Actor class, but by
 * extracting them into a separate class, we reduce the amount of responsibilities in the Actor class, keeping it more
 * focused.  Because this logic isn't particularly relevant to the Actor class, it is better for it to exist outside of 
 * that class so that if the logic for handling rules were to change in the program, the Actor class wouldn't have these
 * dependencies and wouldn't need to change because of it.  Reducing this coupling is better design, as it decreases the
 * risk of breaking any other functionality in Actor because of changes to this functionality.  Additionally, this decision
 * extract rule logic from Actor came as part of a decision to allow Levels to contain rules as well, so this applies the
 * Foundation Pattern heuristic we read about, on not implementing identical behavior in two or more places.  Because, now,
 * both Actor and Rule can contain rules, extracting this logic to a third class means changes to this implementation can
 * be done in one place only, which is safer practice to avoid introducing inconsistencies or bugs into our program.  Also,
 * this way, the basic implementation for handling rules can be closed for modification in this class, but remain open 
 * to extension in the specific game element classes, since they're composed with a RuleManager, creating greater flexibility
 * for different game elements to handle rules in their own particular manner if need be.
 * 
 * Lastly, I think this class shows good design for very basic design traits, such as being a very concise class with short 
 * methods, no duplicate code, etc.  It has also been refactored to ensure it makes use of interfaces as opposed to concrete
 * classes such that  
 */
package gameengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gameengine.model.Triggers.ITrigger;
/**
 * This class is designed to manage a game element's rules.
 * @author colettetorres
 *
 */
public class RuleManager {
	private Map<String, List<IRule>> myRules;

	public RuleManager() {
		myRules = new HashMap<String, List<IRule>>();
	}
	
	/**
	 * Adds a rule to a game element's collection of rules 
	 * @param newRule The rule to be added to the game element's collection of rules to manage
	 */
	public void addRule(IRule newRule) {
		List<IRule> myBehaviors = new ArrayList<>();
		String key = newRule.getMyTrigger().getMyKey();
		if (myRules.containsKey(key)) {
			myBehaviors = myRules.get(key);
		} 
		myBehaviors.add(newRule);
		myRules.put(key, myBehaviors);
	}
	
	/**
	 * Removes a rule from a game element's collection of rules
	 * @param rule The rule to be removed from the game element's collection of rules 
	 */
    public void removeRule(IRule rule){
    	myRules.get(rule.getMyTrigger().getMyKey()).remove(rule);
    }
	
    /**
     * Checks a game element's collection of rules to see if one contains the specified trigger.
     * Performs the actions tied to that trigger if it exists.
     * @param otherTrigger The trigger to be checked and potentially performed 
     */
    public void handleTrigger(ITrigger otherTrigger) {
    	if(myRules.containsKey(otherTrigger.getMyKey())){
            List<IRule> myBehaviors = myRules.get(otherTrigger.getMyKey());
            for (IRule myRule : myBehaviors) {
                if (myRule.getMyTrigger().evaluate(otherTrigger)){
                	myRule.getMyAction().perform();
                }
            }
    	}
    }
    
    /**
     * Gets a game element's collection of rules 
     * @return The game element's rules 
     */
    public Map<String, List<IRule>> getRules(){
    	return myRules;
    }    
}