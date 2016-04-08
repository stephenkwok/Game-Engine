package gui.view;

import java.util.ResourceBundle;

import authoringenvironment.view.ActorRuleCreator;
import authoringenvironment.view.TabLibraryBehaviors;
import authoringenvironment.view.TabLibraryImages;
import authoringenvironment.view.TabLibrarySounds;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
/**
 * Returns library of available game behaviors, images, and sounds  
 * @author AnnieTang
 *
 */
public class GUILibrary implements IGUI{
	private static final String LIBRARY_RESOURCE = "library";
	private ResourceBundle myResources;
	private Pane myPane;
	private ActorRuleCreator myActorRuleCreator;
	private TabLibraryImages imageLib;
	private TabLibrarySounds soundLib;
	private TabLibraryBehaviors behaviorLib;
	private TabPane tp;
	
	public GUILibrary(){
		initializeEnvironment();
	}
	public GUILibrary(ActorRuleCreator myRuleMaker) {
		this.myActorRuleCreator = myRuleMaker;
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		myResources = ResourceBundle.getBundle(LIBRARY_RESOURCE);
		myPane = new StackPane();
		tp = new TabPane();
		imageLib = new TabLibraryImages(myResources, "Images", myActorRuleCreator);
		soundLib = new TabLibrarySounds(myResources, "Sounds", myActorRuleCreator);
		behaviorLib = new TabLibraryBehaviors(myResources, "Behaviors", myActorRuleCreator);
		tp.getTabs().addAll(behaviorLib.getTab(), imageLib.getTab(), soundLib.getTab());
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myPane.getChildren().add(tp);
	}

	@Override
	public Pane getPane() {
		return myPane;
	}
	
	public void updateDragEvents(){
//		initializeEnvironment();
//		imageLib.setContent();
//		soundLib.setContent();
		behaviorLib.updateDragEvents(myActorRuleCreator);
//		System.out.println("Size of ActorRuleCreator Rules: " + myActorRuleCreator.getRules().size());
	}
}
