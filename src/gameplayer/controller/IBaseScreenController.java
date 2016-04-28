package gameplayer.controller;

import gameplayer.view.GameScreen;
import voogasalad.util.hud.source.AbstractHUDScreen;

public interface IBaseScreenController {

	/**
	 * Sets the subscene of a base screen to a game screen
	 * @param screen
	 */
	public void setGameScreen(GameScreen screen);
	
	/**
	 * Initializes the HUD pane and sets the lower pane of the base screen to the new pane
	 * @param screen
	 */
	public void setHUDScreen(AbstractHUDScreen screen);
}
