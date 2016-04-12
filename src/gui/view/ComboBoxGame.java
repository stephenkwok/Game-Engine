package gui.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gamedata.controller.ParserController;
import gameengine.controller.Game;
import gui.controller.IScreenController;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ComboBoxGame extends ComboBoxImageCell {

	private static final int STANDARD_IMAGE_HEIGHT = 50;
	private IScreenController myController;
	private Map<String, Game> myGames;
	
	public ComboBoxGame(String promptText, String imageResource, IScreenController myController) {
		super(promptText, imageResource, STANDARD_IMAGE_HEIGHT);
		this.myController = myController;
		this.myGames = new HashMap<>();
		getGames();
		fillImageNames();
		fillImageMap();
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event -> {
			myController.useGame(myGames.get(comboBox.getValue()));
		});
		
	}

	private void getGames() {
		File gameFileDir = new File(selectionResource);
		for(File gameFile: gameFileDir.listFiles()) {
			if(!gameFile.isDirectory()){
				ParserController parserController = new ParserController(myController.getScreen());
				Game game = parserController.loadforPlaying(gameFile);
				myGames.put(gameFile.getPath(),game);
			}
		}
	}
	
	@Override
	public void fillImageNames() {
		for (Game game: myGames.values()) {
			imageNames.add(game.getInfo().getMyImageName());
		}
		
	}

	@Override
	public Node getNodeForBox(String item) {
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		Game game = myGames.get(item);
		vbox.getChildren().addAll(new Label(game.getInfo().getMyName()), new Text("\n" + game.getInfo().getMyDescription()));
		hbox.getChildren().addAll(imageMap.get(game.getInfo().getMyImageName()), vbox);
		return hbox;
	}
	
	@Override 
	public List<String> getOptionsList() {
		List<String> myGamePaths = new ArrayList<>();
		for (String path: myGames.keySet()) {
			myGamePaths.add(path);
		}
		return myGamePaths;
	}

	@Override
	protected void updateValueBasedOnEditable() {
		// TODO Auto-generated method stub
		
	}

}
