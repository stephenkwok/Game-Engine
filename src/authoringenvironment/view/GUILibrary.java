package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GUILibrary implements IGUI{
	private ResourceBundle myResources;
	private static final String TAB_NAME = "Library";
	
	public GUILibrary(ResourceBundle myResources) {
		this.myResources = myResources;
	}

	@Override
	public void updateAllNodes() {
		
	}

	@Override
	public Pane getPane() {
		StackPane wrapper = new StackPane();
		TabPane tp = new TabPane();
		TabImages imageLib = new TabImages(myResources, "Images");
		TabSounds soundLib = new TabSounds(myResources, "Sounds");
		TabBehaviors behaviorLib = new TabBehaviors(myResources, "Behaviors");
		tp.getTabs().addAll(behaviorLib.createTab(), imageLib.createTab(), soundLib.createTab());
		wrapper.getChildren().add(tp);
		return wrapper;
	}

}
