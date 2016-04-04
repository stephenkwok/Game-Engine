package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Tab for setting level attributes to go in the Inspector Pane in the Level Editing Environment GUI.
 * @author amyzhao
 *
 */
public class TabLevelAttributes extends TabParent {
	private static final String LEVEL_OPTIONS_RESOURCE = "levelEditorOptions";
	private static final String SETTING_TYPES = "SettingTypes";
	private ResourceBundle myResources;
	
	public TabLevelAttributes(ResourceBundle myResources, String tabText) {
		super(myResources, tabText);
		this.myResources = ResourceBundle.getBundle(LEVEL_OPTIONS_RESOURCE);
	}

	@Override
	void setContent() {
		VBox vbox = new VBox();
		IGUIElement scrollingDir = new ComboBoxScrollingDirection(myResources, "Select");
		vbox.getChildren().add(scrollingDir.createNode());
		tab.setContent(vbox);
		// get types
			// for each type
				// switchcase
					//Textview
					//ComboBoxes
	}

}
