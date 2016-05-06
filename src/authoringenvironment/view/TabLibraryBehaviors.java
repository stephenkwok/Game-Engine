/**
 * This entire file is part of my masterpiece.
 * Annie Tang
 * 
 *   The purpose of this code is to allow the user to create tabs with Actions or Triggers that the user can drag and
 *   place in authoring environment's library/rule maker, or elsewhere.
 *   
 *   This is good code because it doesn't have any code smells (see GUILibrary no code smells explanation), and it shows 
 *   my use of inheritance hierarchies and abstraction. 
 */
package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.ActorRuleCreator;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.TransferMode;

/**
 * Tab contains ListView of behaviors and drag/drop behavior for behaviors.
 * 
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
		setLabels(FXCollections.observableArrayList());
		for (String behavior : getResources().getString(getTabText()).split(" ")) {
			Label mySource = new Label(behavior);
			if (getActorRuleCreator() != null) {
				setDragEvent(mySource, TransferMode.COPY);
			}
			getLabels().add(mySource);
		}
		setListView(new ListView<>(getLabels()));
	}

	/**
	 * Return behavior content of this tab
	 */
	@Override
	Node getContent() {
		return getListView();
	}
}
