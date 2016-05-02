package gameplayer.view;

import gui.view.IScreen;
import voogasalad.util.hud.source.AbstractHUDScreen;

/**
 * This interface allows the base screen controller to interact with the base screen.
 * @author cmt57
 *
 */
public interface IBaseScreen extends IScreen {

	/**
	 * Assigns the HUD section of the scene's BorderPane to an instance of AbstractHUDScreen
	 */
	void setHUDScreen(AbstractHUDScreen view);

	/**
	 * Assigns the game section of the scene's BorderPane to an instance of IGameScreen 
	 */
	void setGameScreen(IGameScreen view);

	/**
	 * Displays the proper alert sequences for ending and saving games based on three possible conditions:
	 * user wants to save, user does not want to save, user has not made a decision yet
	 */
	void switchAlert();

}