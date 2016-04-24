package authoringenvironment.view;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import gui.view.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Main GUI class. Fixed tool-bar as top pane. 
 * @author AnnieTang, amyzhao
 *
 */
public class GUIMain extends Screen implements IGUI, Observer {
    private static final String GUI_RESOURCE = "authoringGUI";
    private static final String TOP_PANE_ELEMENTS = "TopPaneElements";
    private static final int WINDOW_HEIGHT = 700;
	private static final int WINDOW_WIDTH = 1300;
    private static final int PADDING = 10;
    private Scene myScene;
	private BorderPane myRoot;

	
	public GUIMain()  {
		super();
		setUpResourceBundle(GUI_RESOURCE);
		init();
	}
	
	/**
	 * Initializes resource bundle, controller, and factory class.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void init() {
		myRoot = new BorderPane();
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		setTopPane();
		setCenterPane();
	}
	
	/**
	 * Creates the fixed tool-bar and sets up over-arching BorderPane. 
	 * @return Scene
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Scene getScene() {
		return myScene;
	}
	/**
	 * Set center section of screen to given Pane
	 * @param pane
	 */
	public void setCenterPane(Pane pane){
		myRoot.setCenter(pane);
	}
	/**
	 * Set center screen to default, the home screen 
	 */
	public void setCenterPane(){
		setChanged();
		notifyObservers("goMain");
	}
	/**
	 * Sets top section of screen to fixed toolbar 
	 */
	private void setTopPane() {
		HBox hbox = new HBox(PADDING);
		hbox.setPadding(new Insets(PADDING,PADDING,PADDING,PADDING));		
		initializeTopPaneElements(hbox);
		hbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		myRoot.setTop(hbox);
	}
	/**
	 * Initialize elements to be in toolbar
	 * @param hbox
	 */
	private void initializeTopPaneElements(HBox hbox) {
		try{
			String[] topPaneElements = getResources().getString(TOP_PANE_ELEMENTS).split(",");
			for (int i = 0; i < topPaneElements.length; i++) {
				IGUIElement elementToCreate = getFactory().createNewGUIObject(topPaneElements[i]);
				elementToCreate.addNodeObserver(this); // NOT SURE IF THIS WILL WORK, CARINE ADDED THIS.......
				hbox.getChildren().add(elementToCreate.createNode());
				
			}

		}catch(Exception e){
			
		}
	}
	/**
	 * Return Pane representation of authoring environment
	 */
	@Override
	public Pane getPane() {
		return myRoot;
	}
	
	/**
	 * Return width of authoring environment Scene
	 * @return
	 */
	public double getWidth() {
		return myScene.getWidth();
	}
	/**
	 * Return height of authoring environment Scene
	 * @return
	 */
	public double getHeight() {
		return myScene.getHeight();
	}

	@Override
	protected void initialize()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(o.getClass().getSimpleName());
		
	}
}