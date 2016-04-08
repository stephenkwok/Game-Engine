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
	private ListView<Label> listView;
	
	public TabLibraryBehaviors(ResourceBundle myResources, String tabText, ActorRuleCreator myRuleMaker) {
		super(myResources, tabText, myRuleMaker);
		setContent();
	}
	
	@Override
	void setContent() {
		behaviorLabels = FXCollections.observableArrayList();
		for(String behavior: myResources.getString(tabText).split(" ")){
			Label mySource = new Label(behavior);
			if(myActorRuleCreator!=null){
				setDragEvent(mySource);
			}
			behaviorLabels.add(mySource);
		}
		listView = new ListView<>(behaviorLabels);
	}

	@Override
	Node getContent() {
		return listView;
	}

	public void updateDragEvents(ActorRuleCreator myActorRuleCreator) {
		this.myActorRuleCreator = myActorRuleCreator;
		for(Label behaviorLabel: behaviorLabels){
			if(myActorRuleCreator!=null){
				setDragEvent(behaviorLabel);
			}
		}
		
	}
}
