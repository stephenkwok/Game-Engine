package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.ActorRuleCreator;
import gui.view.IGUI;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Returns library of available game behaviors, images, and sounds
 * 
 * @author AnnieTang
 *
 */
public class GUILibrary implements IGUI {
	private static final String LIBRARY_RESOURCE = "library";
//	private static final String IMAGES = "Images";
	private static final String SOUNDS = "Sounds";
	private static final String ACTIONS = "Actions";
	private static final String TRIGGERS = "Triggers";
	private ResourceBundle myResources;
	private Pane myPane;
	private ActorRuleCreator myActorRuleCreator;
//	private TabLibraryImages imageLib;
	private TabLibrarySounds soundLib;
	private TabLibraryBehaviors actionLib;
	private TabLibraryBehaviors triggerLib;
	private TabPane tp;

	public GUILibrary() {
		initializeEnvironment();
	}

	public GUILibrary(ActorRuleCreator myRuleMaker) {
		this.myActorRuleCreator = myRuleMaker;
		initializeEnvironment();
	}

	private void initializeEnvironment() {
		myResources = ResourceBundle.getBundle(LIBRARY_RESOURCE);
		myPane = new StackPane();
		tp = new TabPane();
//		imageLib = new TabLibraryImages(myResources, IMAGES, myActorRuleCreator);
		soundLib = new TabLibrarySounds(myResources, SOUNDS, myActorRuleCreator);
		actionLib = new TabLibraryBehaviors(myResources, ACTIONS, myActorRuleCreator);
		triggerLib = new TabLibraryBehaviors(myResources, TRIGGERS, myActorRuleCreator);
		tp.getTabs().addAll(triggerLib.getTab(), actionLib.getTab(), soundLib.getTab());
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myPane.getChildren().add(tp);
	}

	@Override
	public Pane getPane() {
		return myPane;
	}

	public void updateDragEvents() {
//		imageLib.updateDragEvents(myActorRuleCreator);
		soundLib.updateDragEvents(myActorRuleCreator);
		actionLib.updateDragEvents(myActorRuleCreator);
		triggerLib.updateDragEvents(myActorRuleCreator);
	}
}
