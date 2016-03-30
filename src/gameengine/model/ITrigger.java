package gameengine.model;

/**
 * This interface defines the public methods for Trigger objects. Trigger correspond with the different events that can
 * cause an Action response.
 *
 * @author blakekaplan
 */
public interface ITrigger {

    /**
     * Provides the name of the Trigger
     * @return
     * The Trigger's name
     */
    public String getTriggerName();

}
