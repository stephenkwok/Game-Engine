package Utilities.HUD;

/** 
 * This class serves as the private interface that an HUDScreen must implement to display certain game values stored in the backend (game engine) such as health, scores, etc.
 * @author cmt57
 */

public interface IHUDScreen {
	
	/** 
	 * Will reflect observed changes from the observable backend version of a display info screen.
	 */
	public void update();

}
