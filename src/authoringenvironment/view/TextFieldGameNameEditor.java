package authoringenvironment.view;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;

/**
 * This class extends the TextFieldWithButton class and allows the author to set the game's name
 * 
 * @author Stephen
 *
 */

public class TextFieldGameNameEditor extends TextFieldWithButton{

	private ResourceBundle myResources;
	private static final double CONTAINER_SPACING = 10.0;
	private static final double CONTAINER_PADDING = 10.0;
	
	public TextFieldGameNameEditor(String labelText, String prompt, Double textFieldWidth, String buttonText, EventHandler<ActionEvent> buttonAction) {
		super(labelText, prompt, textFieldWidth, buttonText, buttonAction);
		this.myResources = ResourceBundle.getBundle("mainScreenGui");
		setTextFieldPromptText(myResources.getString("enterGameName"));
		setContainerSpacing(CONTAINER_SPACING);
		setContainerPadding(new Insets(CONTAINER_PADDING));
	}

	@Override
	public Node createNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNode() {
		// TODO Auto-generated method stub
		
	}

}
