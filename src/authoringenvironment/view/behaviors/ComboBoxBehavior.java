package authoringenvironment.view.behaviors;

import java.util.List;
import java.util.ResourceBundle;

import authoringenvironment.model.IEditableGameElement;
import authoringenvironment.view.TriggerFactory;
import gui.view.EditingElementParent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Abstract class to implement ComboBox<IAuthoringActor> or ComboBox<Level>.
 * @author AnnieTang
 */

public abstract class ComboBoxBehavior extends EditingElementParent{
	private static final String LABEL = "Label";
	private static final String PROMPT = "Prompt";
	private static final int COMBOBOX_WIDTH = 150;
	private static final int VISIBLE_ROW_COUNT = 5;
	private static final int HBOX_SPACING = 5;
	private static final String GO = "Go";
	private static final int BUTTON_HEIGHT = 30;
	private static final int BUTTON_WIDTH = 40;
	private String promptText;
	private ObservableList<IEditableGameElement> options;
	private ComboBox<IEditableGameElement> comboBox;
	private String labelText;
	private TriggerFactory triggerFactory;
//	private ActionFactory actionFactory;
	
	public ComboBoxBehavior(String behaviorType, ResourceBundle myResources) {
		super(GO);
		this.promptText =  myResources.getString(behaviorType+PROMPT);
		this.labelText = myResources.getString(behaviorType+LABEL);
		this.triggerFactory = new TriggerFactory();
	}
	
	/**
	 * Creates ComboBox Node.
	 */
	public Node createNode(){
		HBox hbox = new HBox(HBOX_SPACING);
		options = FXCollections.observableArrayList(
			        getOptionsList()
			    );
		addLabel(hbox);
		initComboBox();
		getButton().setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		HBox.setHgrow(comboBox, Priority.ALWAYS);
		hbox.getChildren().addAll(comboBox, getButton());
		hbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}
	/**
	 * Add label to ComboBox 
	 * @param container
	 */
	private void addLabel(HBox container) {
		if (labelText != null) {
			Label label = new Label(labelText);
			label.setWrapText(true);
			container.getChildren().add(label);
		}
	}
	/**
	 * Initialize ComboBox with size, row count, text, cell factory
	 */
	//abstract
	private void initComboBox(){
		comboBox = new ComboBox<>(options);
		comboBox.setVisibleRowCount(VISIBLE_ROW_COUNT);
		comboBox.setPrefWidth(COMBOBOX_WIDTH);
		comboBox.setPromptText(promptText);
		comboBox.setCellFactory(factory -> new MyCustomCell());
	}
	
	/**
	 * Creates custom cell factory for ComboBox
	 * @author AnnieTang
	 */
	private class MyCustomCell extends ListCell<IEditableGameElement> {
        @Override 
    	protected void updateItem(IEditableGameElement item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
        } else {
        	HBox graphic = new HBox();
        	graphic.getChildren().addAll(item.getMyImageView(), new Label(item.getMyName()));
            setGraphic(graphic);
        }
       }
    }
	
	/**
	 * Updates Node whenever new information or data is available.
	 */
	public void updateNode() {
		options = FXCollections.observableArrayList(
		        getOptionsList()
		    );
		comboBox.setItems(options);
	}	
	
	/**
	 * Returns list of items in the ComboBox.
	 * @return
	 */
	abstract List<IEditableGameElement> getOptionsList();
	
	/**
	 * Gets the combobox.
	 * @return combobox.
	 */
	protected ComboBox<IEditableGameElement> getComboBox() {
		return comboBox;
	}
	/**
	 * Gets the trigger factory. 
	 * @return factory
	 */
	protected TriggerFactory getTriggerFactory(){
		return this.triggerFactory;
	}
	
}
