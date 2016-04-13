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
	private HighScoreScreenController myController;
	private GUIFactory factory;
	private BorderPane myPane;
	private Map<String, HashMap<String, Integer>> myMap;
	
	public HighScoreScreen(Stage s) {
		super(s);
		myPane = new BorderPane();
		init();
		myMap = new HashMap<String, HashMap<String,Integer>>();
		myMap.put("mario", new HashMap<String, Integer>());
		myMap.get("mario").put("rihanna", 100);
		myMap.put("flappy bird", new HashMap<String, Integer>());
		myMap.get("flappy bird").put("literally anyone with a pulse", 10);
		myMap.get("flappy bird").put("michelle", -5);
		
		addComponents();
	}
	
	private void addComponents() {
		initialize();
		addGamePane();
		getRoot().getChildren().add(myPane);
	}

	private void addScorePane(String game) {
		VBox masterV = new VBox(20);
		VBox myBox = new VBox(5);
		for(String player : myMap.get(game).keySet()){
			HBox myH = new HBox(10);
			Text myPlayer = new Text(player);
			myPlayer.setFont(Font.font("Helvetica", 30));
			myH.getChildren().add(myPlayer);
			Text myScore = new Text(myMap.get(game).get(player).toString());
			myScore.setFont(Font.font("Times New Roman", 30));
			myH.getChildren().add(myScore);
			myBox.getChildren().add(myH);
		}
		masterV.getChildren().add(myBox);
		myPane.setCenter(masterV);
		
	}

	private void addGamePane() {
		ToolBar myT = new ToolBar();
		myT.setMinHeight(SCREEN_HEIGHT);
		myT.setOrientation(Orientation.VERTICAL);
		for(String game : myMap.keySet()){
			Button myB = new Button(game);
			myB.setOnMouseClicked(e -> addScorePane(game));
			myT.getItems().add(myB);
		}
		myPane.setLeft(myT);
		
	}

	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new HighScoreScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}

	public void initialize(){
		ToolBar myT = new ToolBar();
		myT.setMinWidth(SCREEN_WIDTH);
		IGUIElement newElement = factory.createNewGUIObject("Splash");
		Button myB = (Button) newElement.createNode();
		myB.setMinSize(8, 8);
		Tooltip t = new Tooltip(myResources.getString("SplashText"));
		t.install(myB, t);
		myT.getItems().add(myB);
		myPane.setTop(myT);
	}
	
	public void returnToSplash(){
		SplashScreen mySplash = new SplashScreen(getStage());
		try {
			getStage().setScene(mySplash.getScene());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void displayScores(Map<String, HashMap<String, Integer>> myMap){
		VBox masterV = new VBox(20);
		for(String game : myMap.keySet()){
			VBox myBox = new VBox(5);
			myBox.getChildren().add(new Text(game));
			for(String player : myMap.get(game).keySet()){
				HBox myH = new HBox(10);
				Text myPlayer = new Text(player);
				myPlayer.setFont(Font.font("Helvetica", 30));
				myH.getChildren().add(myPlayer);
				Text myScore = new Text(myMap.get(game).get(player).toString());
				myScore.setFont(Font.font("Times New Roman", 20));
				myH.getChildren().add(myScore);
				myBox.getChildren().add(myH);
			}
			masterV.getChildren().add(myBox);
		}
		masterV.setLayoutX(100);
		masterV.setLayoutY(10);
		getRoot().getChildren().add(masterV);
	}

	@Override
	public Scene getScene() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IScreenController setController() {
		// TODO Auto-generated method stub
		return null;
	}

}
