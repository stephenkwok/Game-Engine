package authoringenvironment.view;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public abstract class TextFieldParent implements IGUIElement {
	private static final int COMBOBOX_WIDTH = 190;
//	private static final int VISIBLE_ROW_COUNT = 5;
	private static final int PADDING = 10;
	private static final int HBOX_SPACING = 5;
	private TextField textField;
	protected Button goButton;
	private String myDefault;
	
	public TextFieldParent(String defaultText) {
		myDefault = defaultText;
	}
	
	/**
	 * Creates ComboBox Node.
	 */
	@Override
	public Node createNode(){
		HBox hbox = new HBox(HBOX_SPACING);
		textField = new TextField();
		textField.setPrefWidth(COMBOBOX_WIDTH);
		textField.setPromptText(myDefault);
		goButton = new Button("Go");
		setButtonAction();
		hbox.getChildren().addAll(textField, goButton);
		hbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		return hbox;
	}
	
	/**
	 * Sets action when button is pressed.
	 */
	abstract void setButtonAction();
	
	/**
	 * Updates Node whenever new information or data is available.
	 */
	@Override
	public void updateNode() {
		//TODO: not sure...
	}
}
