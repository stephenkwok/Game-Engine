package gameengine.model;

import java.util.List;
import java.util.Map;

import gameengine.controller.IPlayGame;
import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.ITrigger;
import javafx.geometry.Bounds;

public interface IGameElement {
	
	/**
	 * Adds an Attribute to the IGameElement
	 * 
	 * @param attribuet	The Attribute to be added
	 */
	public void addAttribute(Attribute attribuet);

	/**
	 * Removes an Attribute from the IGameElement
	 * 
	 * @param attribute	The Attribute to be removed
	 */
	public void removeAttribute(Attribute attribute);

	/**
	 * Executes the appropriate Actions when an Attribute reaches its Trigger value
	 * 
	 * @param trigger	The Attribute whose Trigger value has been reached
	 */
	public void handleReachedAttribute(AttributeReached trigger);

	/**
	 * Gets the IGameElement's Attribute for a particular AttributeType
	 * 
	 * @param type	The desired AttributeType
	 * @return	The Attribute for the particular AttributeType
	 */
    public Attribute getAttribute(AttributeType type);
    
    /**
     * Gets the name for the IGameElement
     * 
     * @return The IGameElement's name
     */
    public String getName();
    
    /**
     * Changes the value of an Attribute in the IGameElement
     * 
     * @param type	The AttributeType of the Attribute to be changed
     * @param change	The value to change the Attribute by
     */
    public void changeAttribute(AttributeType type, int change);
    
    /**
     * 
     * @param rule
     */
    public void addRule(Rule rule);
    public void removeRule(Rule rule);
    public void handleTrigger(ITrigger trigger);
    public Map<String, List<Rule>> getRules();
    public void changed();
    public Bounds getBounds();
    public void setGame(IPlayGame game);
    public IPlayGame getGame();
}
