package authoringenvironment.view;
import java.util.ResourceBundle;

import authoringenvironment.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main GUI class. Fixed tool-bar as top pane. 
 * @author AnnieTang
 *
 */
public class GUIMain implements IGUI {
    private static final String GUI_RESOURCE = "authoringGUI";
    private static final int PADDING = 10;
    private Scene myScene;
	private BorderPane myRoot;
	private ResourceBundle myResources;
	private int windowHeight;
	private int windowWidth;
	private Stage myStage;
	private Controller myController;
	private GUIFactory factory;
	private IGUIElement home;
	private IGUIElement levels;
	private IGUIElement save;
	private IGUIElement load;
	
	public GUIMain(int windowWidth, int windowHeight, Stage s) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.myStage = s;
		init();
	}
	
	/**
	 * Initializes resource bundle, controller, and factory class.
	 */
	private void init(){
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new Controller(myStage, this);
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
		setCenterPane();
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
	
	public void setCenterPane(){
//		myController.goToMainScreen();
		TabPane tp = new TabPane();
		TabImages test = new TabImages(myResources, "images"); 
		tp.getTabs().add(test.createTab());
		myRoot.setCenter(tp);
	}
	
	private void setTopPane(){
		HBox hbox = new HBox(PADDING);
		hbox.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));
		home = factory.createNewGUIObject("Home");
		levels = factory.createNewGUIObject("Levels");
		save = factory.createNewGUIObject("Save");
		load = factory.createNewGUIObject("Load");
		hbox.getChildren().addAll(home.createNode(),levels.createNode(),save.createNode(),load.createNode());
		hbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		myRoot.setTop(hbox);
	}

	@Override
	public void updateAllNodes() {
		levels.updateNode();
	}

}