package authoringenvironment.view;

import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import gameengine.controller.ILevel;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Front-end of the Level Editing Environment
 * @author amyzhao
 *
 */
public class GUILevelEditingEnvironment implements IGUI {
	private static final String GUI_RESOURCE = "authoringGUI";
	private BorderPane myRoot;
	private GUILibrary myLibrary;
	private GUILevelInspector myInspector;
	private ResourceBundle myResources;
	private VBox myLeftPane;
	private Canvas myCanvas;
	private GUIFactory myFactory;
    	
	public GUILevelEditingEnvironment(Controller controller) {
		myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myRoot = new BorderPane();
		initializeLeftPane();
		initializeCenterCanvas();
		myFactory = new GUIFactory(myResources, controller);
	}
	
	private void initializeLeftPane() {
		myLeftPane = new VBox();
		myLeftPane.prefHeightProperty().bind(myRoot.heightProperty());
		myInspector = new GUILevelInspector(myResources, myFactory);
		myLibrary = new GUILibrary(myResources);
		myLeftPane.getChildren().addAll(myInspector.getPane(), myLibrary.getPane());
		myRoot.setLeft(myLeftPane);
	}
	
	private void initializeCenterCanvas() {
		myCanvas = new Canvas();
		myRoot.setCenter(myCanvas);
	}

	@Override
	public Pane getPane() {
		return myRoot;
	}

	@Override
	public void updateAllNodes() {
		// TODO Auto-generated method stub
		
	}
	
	public void setLevel(ILevel level){
		
	}

}
