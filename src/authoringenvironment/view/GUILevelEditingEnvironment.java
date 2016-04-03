package authoringenvironment.view;

import java.util.ResourceBundle;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * Front-end of the Level Editing Environment
 * @author amyzhao
 *
 */
public class GUILevelEditingEnvironment implements IGUI {
	private static final String GUI_RESOURCE = "authoringGUI";
	private static final int VGAP = 5;
	private static final int LEFT_PANE_WIDTH = 350;
	private BorderPane myRoot;
	private GUILibrary myLibrary;
	private GUILevelInspector myInspector;
	private ResourceBundle myResources;
	private FlowPane myLeftPane;
	private Canvas myCanvas;
    	
	public GUILevelEditingEnvironment() {
		myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myRoot = new BorderPane();
		initializeLeftPane();
		initializeCenterCanvas();
	}
	
	private void initializeLeftPane() {
		myLeftPane = new FlowPane(0, VGAP);
		myLeftPane.setMaxWidth(LEFT_PANE_WIDTH);
		myInspector = new GUILevelInspector(myResources);
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

}
