package usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gameplayer.screens.HighScoreScreen;
import gameplayer.screens.IScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SampleHighScoreScreen implements HighScoreScreen, IScreen{

	Scene myScene;
	Group myRoot;
	
	public SampleHighScoreScreen() {
		setUp();
	}

	@Override
	public void setUp() {
		myScene = new Scene(myRoot);
		myRoot = new Group();
		
	}

	@Override
	public void initialize() {
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
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
