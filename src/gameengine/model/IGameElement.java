package gameengine.model;

import java.util.List;
import java.util.Map;

import gameengine.model.Triggers.AttributeReached;
import gameengine.model.Triggers.ITrigger;
import javafx.geometry.Bounds;

public interface IGameElement {
	public void addAttribute(Attribute attribuet);
	public void removeAttribute(Attribute attribute);
	public void handleReachedAttribute(AttributeReached trigger);
    public Attribute getAttribute(AttributeType type);
    public String getName();
    public void changeAttribute(AttributeType type, int change);
    public void addRule(Rule rule);
    public void removeRule(Rule rule);
    public void handleTrigger(ITrigger trigger);
    public Map<String, List<Rule>> getRules();
    public void changed();
    public Bounds getBounds();
}
