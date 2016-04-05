package authoringenvironment.view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class TextFieldGameNameEditor extends TextFieldWithButton{

	private static final double CONTAINER_SPACING = 10.0;
	private static final double CONTAINER_PADDING = 10.0;
	
	public TextFieldGameNameEditor(String prompt, Double textFieldWidth, String buttonText, EventHandler<ActionEvent> buttonAction) {
		super(prompt, textFieldWidth, buttonText, buttonAction);
		setContainerSpacing(CONTAINER_SPACING);
		setContainerPadding(new Insets(CONTAINER_PADDING));
	}

}
