package gameengine.model.Triggers;

/**
 * This interface defines the public methods for Trigger objects. Trigger
 * correspond with the different events that can cause an Action response.
 *
 * @author blakekaplan
 */
public class ITrigger {

	/**
	 * Provides the name of the Trigger
	 *
	 * @return The Trigger's name
	 */
	public String getMyKey() {
		return null;
	}

	/**
	 * Checks a boolean condition against the state of an actor
	 *
	 * @param otherTrigger
	 *            A trigger to check information against
	 * @return A boolean that says if the condition is true or false
	 */
	public boolean evaluate(ITrigger otherTrigger) {
		return false;
	}

}