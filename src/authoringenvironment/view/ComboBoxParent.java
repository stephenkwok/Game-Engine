package authoringenvironment.view;

import java.util.List;
import java.util.ResourceBundle;

import gui.view.IGUIElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

/**
 * Abstract class to implement different types of ComboBoxes.
 * @author AnnieTang
 */

public abstract class ComboBoxParent implements IGUIElement {
	private static final int COMBOBOX_WIDTH = 190;
	private static final int VISIBLE_ROW_COUNT = 5;
	private static final int PADDING = 10;
	private static final int HBOX_SPACING = 5;
	protected static final String NO_NODE_FOR_BOX = "";
	protected String promptText;
	protected ObservableList<String> options;
	protected List<String> optionsList;
	protected ComboBox<String> comboBox;
	protected Button comboButton;
	protected String paletteSource;
	
	public ComboBoxParent(String promptText) {
		this.promptText = promptText;
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
		comboBox = new ComboBox<>(options);
		comboBox.setVisibleRowCount(VISIBLE_ROW_COUNT);
		comboBox.setPrefWidth(COMBOBOX_WIDTH);
		comboBox.setPromptText(promptText);
		comboBox.setCellFactory(factory -> new MyCustomCell());
		comboButton = new Button("Go");
		setButtonAction();
		hbox.getChildren().addAll(comboBox, comboButton);
		hbox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
		return hbox;
	}
	
	/**
	 * Sets action when button is pressed.
	 */
	abstract void setButtonAction();
	
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
       	 	Label lbl = new Label(item);
       	 	hbox.getChildren().addAll(getNodeForBox(item), lbl);
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
	@Override
	public void updateNode() {
		ObservableList<String> newOptions = FXCollections.observableArrayList(
		        getOptionsList()
		    );
		comboBox.setItems(newOptions);
	}
	
	/**
	 * Returns list of items in the ComboBox.
	 * @return
	 */
	abstract List<String> getOptionsList();
}
