package gamedata.view;

import java.util.Arrays;
import java.util.Map;

import gamedata.controller.HighScoresController;
import gameengine.controller.Game;
import gameplayer.view.BaseScreen;
import gameplayer.view.HighScoreScreen;
import javafx.stage.Stage;

@Deprecated
public class FileChooserScreenScores extends FileChooserScreen {

	public FileChooserScreenScores(Stage myStage) {
		super(myStage);
	}

	@Override
	public void use(Game game) {
		HighScoresController controller = new HighScoresController(game.getInfo().getMyFile(), this);
		Map<String, Integer> gameScores = controller.getGameHighScores();
		HighScoreScreen screen = new HighScoreScreen(getStage(), gameScores, game.getInfo().getMyFile());
		getStage().setScene(screen.getMyScene());
	}

}
