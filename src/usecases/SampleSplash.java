package usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gamedata.HighScoreParser;
import gameplayer.screens.IScreen;
import gameplayer.screens.ISplashScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleSplash implements ISplashScreen, IScreen {

	Stage myStage;
	Scene myScene;
	Group myRoot;
	
	public SampleSplash() {
		setUp();
		Button b = new Button("High Scores");
		b.setLayoutX(40);
		b.setLayoutY(40);
		b.setOnAction(e -> openHighScores());
		myRoot.getChildren().add(b);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}
	
	public void openHighScores(){
		SampleParser myParser = new SampleParser();
		List<String> myList = new ArrayList<String>();
		Map<String, List<String>> myInfo = myParser.getHighScoreInfo(myList);
		VBox myBox = new VBox();
		for(String key : myInfo.keySet()){
			HBox hb = new HBox();
			for(String info : myInfo.get(key)){
				hb.getChildren().add(new Text(info));
			}
			myBox.getChildren().add(hb);
		}
		myRoot.getChildren().add(myBox);
	}

	@Override
	public void setUp() {
		myStage = new Stage();
		myScene = new Scene(myRoot);
		myRoot = new Group();
		myStage.show();
	}

}
