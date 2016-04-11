package gameplayer.view;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import com.sun.prism.paint.Color;

import gameplayer.controller.HighScoreScreenController;
import gui.controller.IScreenController;
import gui.view.GUIFactory;
import gui.view.Screen;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	
	public HighScoreScreen(Stage s) {
		super(s);
		initialize();
		Map<String, HashMap<String, Integer>> myMap = new HashMap<String, HashMap<String,Integer>>();
		myMap.put("mario", new HashMap<String, Integer>());
		myMap.get("mario").put("rihanna", 100);
		myMap.put("flappy bird", new HashMap<String, Integer>());
		myMap.get("flappy bird").put("mariel", 20);
		displayScores(myMap);
	}
	
	public void init() {
		this.myResources = ResourceBundle.getBundle(GUI_RESOURCE);
		myController = new HighScoreScreenController(getStage(), this, this.myResources);
		factory = new GUIFactory(myResources, myController);
	}

	public void initialize(){
		Button b = new Button("Back");
		getRoot().getChildren().add(b);
		b.setOnAction(e -> returnToSplash());
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
