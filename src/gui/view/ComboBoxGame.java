package gui.view;

import java.io.File;
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
		getGames();
	}

	@Override
	public void setButtonAction() {
		comboButton.setOnAction(event -> {
			File file = new File(comboBox.getValue());
			myController.createGameFromFile(file);
		});
		
	}

	private void getGames() {
		File gameFileDir = new File(selectionResource);
		for(File gameFile: gameFileDir.listFiles()) {
			ParserController parserController = new ParserController();
			Game game = parserController.loadforPlaying(gameFile);
			myGames.put(game.getInfo().getName(),game);
		}
	}
	
	@Override
	public void fillImageNames() {
		for (Game game: myGames.values()) {
			imageNames.add(game.getInfo().getName());
		}
		
	}

	@Override
	public Node getNodeForBox(String item) {
		HBox hbox = new HBox();
		VBox vbox = new VBox();
		Game game = myGames.get(item);
		vbox.getChildren().addAll(new Label(item, new Text(game.getInfo().getDescription())));
		hbox.getChildren().addAll(imageMap.get(game.getInfo().getImageName()), vbox);
		return hbox;
	}

}
