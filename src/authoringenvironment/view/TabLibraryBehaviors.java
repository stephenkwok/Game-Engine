package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.TransferMode;
import javafx.collections.FXCollections;
/**
 * Tab contains ListView of behaviors and drag/drop behavior for behaviors.
 * Drag/Drop event handling code mostly acquired from Oracle. 
 * @author AnnieTang
 *
 */
public class TabLibraryBehaviors extends TabLibrary {
	
	public TabLibraryBehaviors(ResourceBundle myResources, String tabText, ActorRuleCreator myRuleMaker) {
		super(myResources, tabText, myRuleMaker);
		setContent();
	}
	/**
	 * Set content of tab to list of behaviors
	 */
	@Override
	void setContent() {
		labels = FXCollections.observableArrayList();
		for(String behavior: myResources.getString(tabText).split(" ")){
			Label mySource = new Label(behavior);
			if(myActorRuleCreator!=null){
				setDragEvent(mySource,TransferMode.COPY);
			}
			labels.add(mySource);
		}
		listView = new ListView<>(labels);
	}
	/**
	 * Return behavior content of this tab
	 */
	@Override
	Node getContent() {
		return listView;
	}
}
