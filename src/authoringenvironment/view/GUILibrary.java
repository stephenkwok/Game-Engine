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
	private ResourceBundle myResources;
	private Pane myPane;
	
	public GUILibrary(ResourceBundle resources) {
		myResources = resources;
		myPane = new StackPane();
		TabPane tp = new TabPane();
		TabImages imageLib = new TabImages(myResources, "Images");
		TabSounds soundLib = new TabSounds(myResources, "Sounds");
		TabBehaviors behaviorLib = new TabBehaviors(myResources, "Behaviors");
		tp.getTabs().addAll(behaviorLib.getTab(), imageLib.getTab(), soundLib.getTab());
		myPane.getChildren().add(tp);
	}

	@Override
	public void updateAllNodes() {
		
	}

	@Override
	public Pane getPane() {
		return myPane;
	}
}
