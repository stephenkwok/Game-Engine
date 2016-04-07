package gameplayer.view;

/**
 * This class represents a private interface provides for a screen to display the highest scores recorded for games.
 * @author Carine
 *
 */
public interface IHighScoreScreen {

	/**
	 * Creates the scene with with current scores saved in the game score xml data
	 */
	public void initialize();
	
	/**
	 * Updates the screen to reflect the most recent changes to the game score xml data
	 */
	public void refresh();
	
}
