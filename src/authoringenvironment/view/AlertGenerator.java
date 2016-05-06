package authoringenvironment.view;

import javafx.scene.control.Alert;

/**
 * Generates and shows an Alert with an error message
 * 
 * @author Stephen
 *
 */

public class AlertGenerator extends Alert {
	
	/**
	 * Generates an Alert of the type ERROR
	 */
	public AlertGenerator() {
		super(AlertType.ERROR);
	}
	
	/**
	 * Sets the Alert's text and displays the Alert
	 * @param errorMessage: error message to be displayed
	 */
	public void generateAlert(String errorMessage) {
		this.setContentText(errorMessage);
		this.show();
	}
}
