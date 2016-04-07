package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Tab contains ListView of behaviors and drag/drop behavior for behaviors.
 * Drag/Drop event handling code mostly acquired from Oracle. 
 * @author AnnieTang
 *
 */
public class TabLibraryBehaviors extends TabLibrary {
	private ObservableList<Label> behaviorLabels;
	
	public TabLibraryBehaviors(ResourceBundle myResources, String tabText, GUIActorRuleMaker myRuleMaker) {
		super(myResources, tabText, myRuleMaker);
	}

	@Override
	Node getContent() {
		behaviorLabels = FXCollections.observableArrayList();
		for(String behavior: myResources.getString(tabText).split(" ")){
			Label mySource = new Label(behavior);
			if(myRuleMaker!=null){
				setDragEvent(mySource);
			}
			behaviorLabels.add(mySource);
		}
		ListView<Label> listView = new ListView<>(behaviorLabels);
		return listView;
	}
}
