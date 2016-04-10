package usecases;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.xml.sax.SAXException;

import gameplayer.view.ISplashScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class is an example implementation of the use case where the user wishes to clear the list of 
 * high scores. It relies on the interfaces of the gameplayer.view class to set up the scene and contain
 * the necessary methods of user interaction to handle these mouse events. In addition, it utilizes the 
 * interfaces of the gamedata.controller to make changes in the xml data files. Together, these APIs
 * collaborate to carry out the user's commands of the front end to the back end's data components by relying
 * on private methods to implement the public calls of this work. 
 * @author michaelfigueiras
 *
 */
public class SampleSplash implements ISplashScreen {

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
		// TO BE IMPLEMENTED LATER
		
	}

	@Override
	public void edit() {
		// TO BE IMPLEMENTED LATER
		
	}
	
	public void openHighScores(){
		FileChooser fChooser = new FileChooser();
		File f = fChooser.showSaveDialog(new Stage());
		String fileName = f.getName();
		
		openIndividualHighScores(fileName);
	}

	public void setUp() {
		myStage = new Stage();
		myScene = new Scene(myRoot);
		myRoot = new Group();
		myStage.show();
	}

	@Override
	public void openIndividualHighScores(String gameName) {
		HighScoresController myHSC = new HighScoresController(gameName);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("HIGH SCORES");
		alert.setHeaderText("Press OK to view scores or Cancel to clear scores");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.CANCEL){
			try {
				myHSC.clearHighScores();
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		else if (result.get() == ButtonType.OK){
			myHSC.viewHighScores(myHSC.getHighScores());
		}

	}

}
