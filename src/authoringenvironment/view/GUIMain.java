package authoringenvironment.view;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main GUI class. Creates main JavaFX components for game authoring environment.
 * @author AnnieTang
 *
 */
public class GUIMain implements IGUI {
    private static final String GUI_RESOURCE = "GUI";
    private Scene myScene;
	private BorderPane myRoot;
	private ResourceBundle myResources;
	private int windowHeight;
	private int windowWidth;
	private Stage myStage;
	private Controller myController;
	private GUIFactory factory;
	
	public GUIMain(int windowWidth, int windowHeight, Stage s) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.myStage = s;
		init();
	}
	
	private void init(){
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new Controller(myStage);
		factory = new GUIFactory(myResources, myController);
	}
	
	/**
	 * Creates the fixed tool-bar and sets up over-arching BorderPane. 
	 * @return Scene
	 */
	@Override
	public Scene getScene() {
		myRoot = new BorderPane();
		setTopPane();
		myScene = new Scene(myRoot, windowHeight, windowWidth, Color.WHITE);
		return myScene;
	}
	
	public void setLeftPane(Pane pane){
		myRoot.setLeft(pane);
	}
	
	public void setRightPane(Pane pane){
		myRoot.setRight(pane);
	}
	
	public void setCenterPane(Pane pane){
		myRoot.setCenter(pane);
	}
	
	public void setBottomPane(Pane pane){
		myRoot.setBottom(pane);
	}
	
	private void setTopPane(){
		
		//TODO
		//home button
		//combobox of scenes
		//save button
	}
	
	private void setCenterPane(){
		//set to main screen 
	}

	@Override
	public void updateAllNodes() {
		
	}

}