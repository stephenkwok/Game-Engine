/**
 * This entire file is part of my masterpiece.
 * Annie Tang  
 * 
 * The purpose of this code is to provide the user with a library of actions, triggers, and sounds that the
 * user can click and drag into the Actor Rule Creator to create ActorRules and implement game engine
 * Rules (Trigger -> Action) in the authoring environment. 
 * 
 * This code is well designed not necessarily because it implements a specific design pattern (most of the design patterns
 * we learned about are also not naturally implemented by front-end code) but because it doesn't have any code smells- 
 * no long methods, not a large class, no duplicated code, no magic numbers/Strings, no public variables. 
 * It also demonstrates an appropriate use of a properties file and the TabParent class hierarchy that I created 
 * (expanded upon more in those classes). 
 * 
 */
package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.model.ActorRuleCreator;
import gui.view.IGUI;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Returns library of available game behaviors (actions and triggers) and sounds
 * 
 * @author AnnieTang
 *
 */
public class GUILibrary implements IGUI {
	private static final String LIBRARY_RESOURCE = "library";
	private static final String SOUNDS = "Sounds";
	private static final String ACTIONS = "Actions";
	private static final String TRIGGERS = "Triggers";
	private ResourceBundle myResources;
	private Pane myPane;
	private ActorRuleCreator myActorRuleCreator;
	private TabLibrarySounds soundLib;
	private TabLibraryBehaviors actionLib;
	private TabLibraryBehaviors triggerLib;
	private TabPane tp;

	/**
	 * Constructor for a GUILibrary.
	 */
	public GUILibrary() {
		initializeEnvironment();
	}

	/**
	 * Constructor for a GUILibrary with a RuleMaker.
	 * @param myRuleMaker: actor rule creator.
	 */
	public GUILibrary(ActorRuleCreator myRuleMaker) {
		this.myActorRuleCreator = myRuleMaker;
		initializeEnvironment();
	}
	
	/**
	 * Initialize the environment's panes.
	 */
	private void initializeEnvironment() {
		myResources = ResourceBundle.getBundle(LIBRARY_RESOURCE);
		myPane = new StackPane();
		tp = new TabPane();
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

	/**
	 * Update the drag events.
	 */
	public void updateDragEvents() {
		soundLib.updateDragEvents(myActorRuleCreator);
		actionLib.updateDragEvents(myActorRuleCreator);
		triggerLib.updateDragEvents(myActorRuleCreator);
	}
}
