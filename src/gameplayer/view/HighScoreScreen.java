//This entire file is part of my masterpiece.
// Michael Figueiras, The Loops Goat Cheese Salad
/**
 * See BaseScreen.java in the gameplayer.screen package for a detailed explanation of how the refactoring of the 
 * Screen class is implemented in these examples.
 */

package gameplayer.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import gui.view.Screen;
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
	private static final int OFFSET = 20;

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
		initialize(TOP_BUTTONS);
		addScorePane();
	}

	/**
	 * Populates the center pane with map elements made into HBoxes for each
	 * individual score
	 */
	private void addScorePane() {
		myScoreBox = new VBox(OFFSET);
		getPane().setCenter(myScoreBox);

	}

	public void displayScores(String gameName, Map<String, Integer> scores) {
		TreeMap<Integer, List<String>> sortedScores = new TreeMap<>();
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
				HBox myH = new HBox(OFFSET / 2);
				myH.getChildren().add(stringToText(score.toString() + " - "));
				myH.getChildren().add(stringToText(player));
				myScoreBox.getChildren().add(myH);
			}
		}
	}

	private Text stringToText(String msg) {
		Text item = new Text(msg);
		item.setFont(Font.font("Helvetica", OFFSET));
		return item;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * NOTE THIS IS FROM INTERFACE NOT SUPER CLASS, AND THEREFORE NOT PART OF
	 * CODE MASTERPIECE
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

}