package gameplayer.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import gui.view.Screen;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class displays the high score player data on a BorderPane and includes a
 * ToolBar for necessary screen functionality
 * 
 * @author cmt57
 *
 */
public class HighScoreScreen extends Screen implements Observer, IHighScoreScreen {

	private static final String SCORES_RESOURCE = "hsGUI";
	private static final String TOP_BUTTONS = "TopButtons";

	private BorderPane myPane;
	private VBox myScoreBox;

	/**
	 * 
	 * @param Stage
	 *            s to change the scene
	 * @param myMap
	 *            which contains player-score relationships, parsed from an xml
	 *            file
	 * @param gameName
	 *            to specify the given data
	 */
	public HighScoreScreen() {
		super();
		setUpResourceBundle(SCORES_RESOURCE);
		this.myPane = new BorderPane();
		initialize();
	}

	@Override
	public void initialize() {
		myPane.setTop(addToolbar(TOP_BUTTONS));
		addScorePane();
		getRoot().getChildren().add(myPane);
	}

	/**
	 * Populates the center pane with map elements made into HBoxes for each
	 * individual score
	 */
	private void addScorePane() {
		// TODO magic number
		myScoreBox = new VBox(20);
		myPane.setCenter(myScoreBox);

	}

	public void displayScores(String gameName, Map<String, Integer> scores) {
		TreeMap<Integer, List<String>> sortedScores = new TreeMap<>();
		// TODO magic number
		for (String player : scores.keySet()) {
			int score = scores.get(player);
			if (sortedScores.keySet().contains(score)) {
				sortedScores.get(score).add(player);
			} else {
				List<String> players = new ArrayList<>();
				players.add(player);
				sortedScores.put(score, players);
			}
		}
		myScoreBox.getChildren().clear();
		myScoreBox.getChildren().add(new Text(gameName));
		for (Integer score : sortedScores.descendingKeySet()) {
			for (String player : sortedScores.get(score)) {
				// TODO figure out font/styling specifics
				HBox myH = new HBox(10);
				myH.getChildren().add(stringToText(score.toString() + " - "));
				myH.getChildren().add(stringToText(player));
				myScoreBox.getChildren().add(myH);
			}
		}
	}

	
	private Text stringToText(String msg){
		Text item = new Text(msg);
		item.setFont(Font.font("Helvetica", 30));
		return item;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

}