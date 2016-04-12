package gui.view;

import java.util.List;

import authoringenvironment.model.IEditableGameElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Abstract class to implement different types of ComboBoxes.
 * @author AnnieTang
 */

public abstract class ComboBoxParent implements IGUIElement, IGUIEditingElement {
	private static final int COMBOBOX_WIDTH = 150;
	private static final int VISIBLE_ROW_COUNT = 5;
	private static final int HBOX_SPACING = 5;
	private static final String GO = "Go";
	protected static final String NO_NODE_FOR_BOX = "";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 40;
	protected String promptText;
	protected ObservableList<String> options;
	protected List<String> optionsList;
	protected ComboBox<String> comboBox;
	protected Button comboButton;
	protected String paletteSource;
	protected String labelText;
	private IEditableGameElement myEditableElement;
	private boolean hasBeenSelected;
	
	public ComboBoxParent(String promptText) {
		this.promptText = promptText;
		this.labelText = null;
		myEditableElement = null;
		hasBeenSelected = false;
	}
	
	/**
	 * Creates ComboBox Node.
	 */
	@Override
	public Node createNode(){
		HBox hbox = new HBox(HBOX_SPACING);
		options = FXCollections.observableArrayList(
			        getOptionsList()
			    );
		addLabel(hbox);
		comboBox = new ComboBox<>(options);
		comboBox.setVisibleRowCount(VISIBLE_ROW_COUNT);
		comboBox.setPrefWidth(COMBOBOX_WIDTH);
		comboBox.setPromptText(promptText);
		comboBox.setCellFactory(factory -> new MyCustomCell());
		comboButton = new Button(GO);
		comboButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		HBox.setHgrow(comboBox, Priority.ALWAYS);
		setButtonAction();
		hbox.getChildren().addAll(comboBox, comboButton);
		hbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}
	
	private void addLabel(HBox container) {
		if (labelText != null) {
			Label label = new Label(labelText);
			label.setWrapText(true);
			container.getChildren().add(label);
		}
	}
	
	/**
	 * Sets action when button is pressed.
	 */
	public abstract void setButtonAction();
	
	/**
	 * Creates custom cell factory for ComboBox
	 * @author AnnieTang
	 */
	private class MyCustomCell extends ListCell<String> {
        @Override 
    	protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
       	 	HBox hbox = new HBox();
       	 	//Label lbl = new Label(item);
       	 	hbox.getChildren().addAll(getNodeForBox(item));//,lbl);
            setGraphic(hbox);
        }
       }
    }
	
	/**
	 * Sets icon for ComboBox
	 * @param item
	 * @return
	 */
	protected abstract Node getNodeForBox(String item);
	
	/**
	 * Updates Node whenever new information or data is available.
	 */
	public void updateNode() {
		ObservableList<String> newOptions = FXCollections.observableArrayList(
		        getOptionsList()
		    );
		comboBox.setItems(newOptions);
	}

	protected abstract void updateValueBasedOnEditable();
	
	@Override
	public void setEditableElement(IEditableGameElement element) {
		myEditableElement = element;
		if (myEditableElement != null) {
			updateValueBasedOnEditable();
		}
	}
	
	protected void hasBeenSelected() {
		hasBeenSelected = true;
	}

	protected IEditableGameElement getEditableElement() {
		return myEditableElement;
	}
	
	/**
	 * Returns list of items in the ComboBox.
	 * @return
	 */
	public abstract List<String> getOptionsList();
}
