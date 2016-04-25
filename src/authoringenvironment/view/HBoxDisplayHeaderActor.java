package authoringenvironment.view;

import javafx.beans.binding.DoubleExpression;

/**
 * This class acts as the header for the display of created Actors on the Main
 * Screen
 * 
 * @author Stephen
 *
 */
public class HBoxDisplayHeaderActor extends HBoxDisplayHeader {

	private static final String LABEL_TEXT = "Actors";

	public HBoxDisplayHeaderActor(DoubleExpression bindWidth) {
		super(LABEL_TEXT, bindWidth);
	}

}
