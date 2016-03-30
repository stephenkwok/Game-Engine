package gameengine.model;

/**
 * Created by blakekaplan on 3/30/16.
 */
public interface IRule {

    /**
     * Gets the Trigger from the Rule
     *
     * @return The specific Trigger from the Rule
     */
    public ITrigger getTrigger();

    /**
     * Gets the Action from the Rule
     *
     * @return The specific Action from the Rule
     */
    public IAction getAction();
}
