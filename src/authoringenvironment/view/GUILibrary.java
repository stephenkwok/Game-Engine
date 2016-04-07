package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.scene.control.TabPane;
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
		TabImages imageLib = new TabImages(myResources, "Images");
		TabSounds soundLib = new TabSounds(myResources, "Sounds");
		TabBehaviors behaviorLib = new TabBehaviors(myResources, "Behaviors", myRuleMaker);
		tp.getTabs().addAll(behaviorLib.getTab(), imageLib.getTab(), soundLib.getTab());
		myPane.getChildren().add(tp);
	}

	@Override
	public Pane getPane() {
		return myPane;
	}
}
