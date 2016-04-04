package authoringenvironment.view;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Tab contains ListView of behaviors
 * @author AnnieTang
 *
 */
public class TabBehaviors extends TabParent {
	private ObservableList<Label> behaviorLabels; 
	public TabBehaviors(ResourceBundle myResources, String tabText) {
		super(myResources, tabText);
	}

	@Override
	void setContent() {
		behaviorLabels = FXCollections.observableArrayList();
		for(String behavior: myResources.getString(tabText).split(" ")){
			behaviorLabels.add(new Label(behavior));
		}
		ListView<Label> listView = new ListView<>(behaviorLabels);
		tab.setContent(listView);
	}

}
