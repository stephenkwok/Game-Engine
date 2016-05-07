package gameengine.model.Triggers;

public interface ITrigger {
	public Object[] getParameters();
	/**
	 * Provides the name of the Trigger
	 *
	 * @return The Trigger's name
	 */
	public String getMyKey();

	/**
	 * Checks a boolean condition against the state of an actor
	 *
	 * @param otherTrigger
	 *            A trigger to check information against
	 * @return A boolean that says if the condition is true or false
	 */
	public boolean evaluate(ITrigger otherTrigger);
}
