package gui.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gamedata.controller.ParserController;
import gameengine.controller.Game;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ComboBoxGame extends ComboBoxImageCell {

	private static final int STANDARD_IMAGE_HEIGHT = 50;
	private Map<String, Game> myGames;
	
	public ComboBoxGame(String promptText, String imageResource) {
		super(promptText, imageResource, STANDARD_IMAGE_HEIGHT);
		this.myGames = new HashMap<>();
		File directory = new File("gamefiles");
		if (directory.listFiles() == null || directory.listFiles().length < 1) {
			setChanged();
			Object[] args = {"alert", "empty"};
			notifyObservers(Arrays.asList(args));
		}
		else {
			getGames();
			if (myGames.keySet().size() != 0) {
				fillImageNames();
				fillImageMap();
			}
		}
	}

	@Override
	public void setButtonAction() {
		this.getComboBox().setOnMouseClicked(event -> {
			if (getComboBox().getItems().size() < 1) {
				setChanged();
				Object[] args = {"alert", "empty"};
				notifyObservers(Arrays.asList(args));
			}
		});
		getComboButton().setOnAction(event -> {
			this.setChanged();
			Object[] methodArg = {"go", myGames.get(getComboBox().getValue())};
			this.notifyObservers(Arrays.asList(methodArg));
		});
		
	}

	private void getGames() {
		//TODO implement error checking
		File gameFileDir = new File(selectionResource);
		for(File gameFile: gameFileDir.listFiles()) {
			if(!gameFile.isDirectory()) {
				ParserController parserController = new ParserController();
				Game game = parserController.loadforPlaying(gameFile);
				if (game != null)
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
		vbox.getChildren().addAll(new Label(item), new Text("\n" + game.getInfo().getMyDescription()));
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