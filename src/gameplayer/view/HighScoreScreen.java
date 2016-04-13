package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import com.sun.prism.paint.Color;

import gameplayer.controller.HighScoreScreenController;
import gui.controller.IScreenController;
import gui.view.GUIFactory;
import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HighScoreScreen extends Screen{

	private ResourceBundle myResources;
	private static final String GUI_RESOURCE = "hsGUI";
	private static final String TOP_BUTTONS = "TopButtons";
	private HighScoreScreenController myController;
	private GUIFactory factory;
	private BorderPane myPane;
	private Map<String, Integer> myMap;
	private String myName;
	
	public HighScoreScreen(Stage s, Map<String, Integer> myMap, String gameName) {
		super(s);
		this.myMap = myMap;
		this.myName = gameName;
		this.myPane =  new BorderPane();
		init();
		addComponents();
	}
	
	private void addComponents() {
		initialize();
		addScorePane();
		getRoot().getChildren().add(myPane);
	}

	private void addScorePane() {
		VBox masterV = new VBox(20);
		masterV.getChildren().add(new Text(myName));
		for(String player : myMap.keySet()){
			HBox myH = new HBox(10);
			Text myPlayer = new Text(player);
			myPlayer.setFont(Font.font("Helvetica", 30));
			myH.getChildren().add(myPlayer);
			Text myScore = new Text(myMap.get(player).toString());
			myScore.setFont(Font.font("Times New Roman", 30));
			myH.getChildren().add(myScore);
			masterV.getChildren().add(myH);
		}
		myPane.setCenter(masterV);
		
	}

	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new HighScoreScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}

	public void initialize(){
		myPane.setLeft(null);
		String[] sideButtons = myResources.getString(TOP_BUTTONS).split(",");
		ToolBar myT = new ToolBar();
		myT.setMinWidth(SCREEN_WIDTH);
		myT.setOrientation(Orientation.HORIZONTAL);
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = factory.createNewGUIObject(sideButtons[i]);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(myResources.getString(sideButtons[i]+ "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
			myB.setFocusTraversable(false);
		}
		myPane.setTop(myT);
	}

	@Override
	public Scene getScene()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IScreenController setController() {
		// TODO Auto-generated method stub
		return null;
	}

}