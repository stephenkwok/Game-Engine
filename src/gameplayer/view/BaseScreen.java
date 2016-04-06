package gameplayer.view;

import javafx.scene.control.Alert;

/**
 * This class provides for a private interface to create a base screen view that will hold the other view components of the gaming program.
 * @author cmt57
 *
 */
public class BaseScreen {
	/**
	 * Adds the auxiliary views, like the HUD display, MenuBar, and GameScreen, to the BaseScreen
	 */
	public void addComponents () {} ;
	
	public void showError (String s) {
		 Alert alert = new Alert(Alert.AlertType.ERROR);
	     alert.setContentText(s);
	     alert.showAndWait();
	}


}
