package gameplayer.view;

/**
 * This class provides for a private interface to add specific Menu-related functionality, such as saving progress and exiting the game, to the BaseScreen view.
 * @author Carine
 *
 */
public interface MenuScreen {

	/**
	 * Will prompt the saving of a game at current status.
	 */
	public void saveProgress ();
	
	/**
	 * Will stop current game to switch to a different game.
	 */
	public void switchGame ();

	/**
	 * Will exit the game and return the user to the splash screen
	 */
	public void returnToSplash();
}
