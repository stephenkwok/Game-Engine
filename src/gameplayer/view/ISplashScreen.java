package gameplayer.view;

/** 
 * This class serves as the private interface that a Game must implement in order to be able to provide functionality for the buttons that will be on the splash screen first displayed to a user.
 * @author cmt57
 */

public interface ISplashScreen {

	/** 
	 * Will open a game playing screen.
	 */
	public void play ();
	
	/** 
	 * Will open a authoring environment screen.
	 */
	public void edit ();
	
	/** 
	 * Will open a high scores options screen.
	 */
	public void openHighScores ();
	
	
	/** 
	 * Will open a high scores screen corresponding to the chosen game to look at.
	 */
	public void openIndividualHighScores (String gameName);
	
}