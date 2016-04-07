package authoringenvironment.view;

import java.util.ResourceBundle;

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
	private GUIActorRuleMaker myRuleMaker;
	
	public GUILibrary(){
		initializeEnvironment();
	}
	public GUILibrary(GUIActorRuleMaker myRuleMaker) {
		this.myRuleMaker = myRuleMaker;
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		myResources = ResourceBundle.getBundle(LIBRARY_RESOURCE);
		myPane = new StackPane();
		TabPane tp = new TabPane();
		TabLibraryImages imageLib = new TabLibraryImages(myResources, "Images", myRuleMaker);
		TabLibrarySounds soundLib = new TabLibrarySounds(myResources, "Sounds", myRuleMaker);
		TabLibraryBehaviors behaviorLib = new TabLibraryBehaviors(myResources, "Behaviors", myRuleMaker);
		tp.getTabs().addAll(behaviorLib.getTab(), imageLib.getTab(), soundLib.getTab());
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myPane.getChildren().add(tp);
	}

	@Override
	public Pane getPane() {
		return myPane;
	}
}
