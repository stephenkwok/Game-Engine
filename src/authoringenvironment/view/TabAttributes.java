package authoringenvironment.view;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import gui.view.IGUIElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Abstract Tab for setting attributes to go in the Inspector Pane in either the Level or Actor Editing Environment GUI.
 * @author amyzhao, AnnieTang
 *
 */
public class TabAttributes extends TabParent {
	private static final int PADDING = 10;
	private static final double TEXTFIELD_WIDTH = 150;
	private static final String EDITOR_ELEMENTS = "EditorElements";
	private static final String PROMPT = "Select";
	private static final String DELIMITER = ",";
	private static final String LABEL = "Label";
	private static final String OPTIONS = "Options";
	private static final String TEXTFIELD = "Textfields";
	private static final String COMBOBOXES = "Comboboxes";
	private static final String TEST = "TEST";
	private static final String GO = "GO";
	private ResourceBundle myAttributesResources;
	
	public TabAttributes(ResourceBundle myResources, String tabText, String levelOptionsResource) {
		super(myResources, tabText);
		this.myAttributesResources = ResourceBundle.getBundle(levelOptionsResource); 
	}

	@Override
	Node getContent() {
		VBox vbox = new VBox(PADDING);
		vbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

		
		String[] elements = myAttributesResources.getString(EDITOR_ELEMENTS).split(DELIMITER);
		for (int i = 0; i < elements.length; i++) {
			HBox hbox = new HBox(PADDING);
			hbox.setAlignment(Pos.CENTER_LEFT);
			Label label = new Label(myAttributesResources.getString(elements[i] + LABEL));
			label.setWrapText(true);
			IGUIElement elementToCreate = null;
			elementToCreate = createNewGUIObject(elements[i]);
			hbox.getChildren().addAll(label, elementToCreate.createNode());
			vbox.getChildren().add(hbox);
		}
		return vbox;
	}

	private IGUIElement createNewGUIObject(String nodeType) {
		if (isTextField(nodeType)) {
			return createTextField(nodeType);
		} else if (isComboBox(nodeType)) {
			return createComboBox(nodeType);
		}
		return null;
	}
	
	private boolean isTextField(String nodeType) {
		return Arrays.asList(myAttributesResources.getString(TEXTFIELD).split(",")).contains(nodeType);
	}

	private boolean isComboBox(String nodeType) {
		return Arrays.asList(myAttributesResources.getString(COMBOBOXES).split(",")).contains(nodeType);
	}

	private IGUIElement createTextField(String nodeType) {
		IGUIElement textField = new TextFieldWithButton("", TEST, TEXTFIELD_WIDTH, GO, null);
		return textField;
	}

	private IGUIElement createComboBox(String nodeType) {
		String options = myAttributesResources.getString(nodeType + OPTIONS);
		List<String> optionsList = Arrays.asList(options.split(DELIMITER));
		return (IGUIElement) new ComboBoxOptions(myAttributesResources,PROMPT,optionsList);
	}
}
