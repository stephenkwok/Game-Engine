package authoringenvironment.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Rule container for an actor containing behavior, images, and/or sounds.
 * @author AnnieTang
 *
 */
public class ActorRule {
	private static final int RULE_WIDTH = 900;
	private static final int RULE_HEIGHT = 250;
	private static final int CORNER_RADIUS = 20;
	private static final int DEFAULT_COL = 0;
	private static final int BEHAVIOR_ROW = 0;
	private static final int IMAGE_ROW = 1; //how to implement this?
	private static final int SOUND_ROW = 1;
	private GridPane myRule;
	
	public ActorRule() {
		initializeEnvironment();
	}

	private void initializeEnvironment() {
		myRule = new GridPane(); 
		myRule.setBackground(new Background(new BackgroundFill(Color.LAVENDER, new CornerRadii(CORNER_RADIUS), Insets.EMPTY)));
		myRule.setPrefSize(RULE_WIDTH, RULE_HEIGHT);
	}

	public GridPane getGridPane() {
		return myRule;
	}

	public void addBehavior(Label behavior) {
		myRule.add(behavior, DEFAULT_COL, BEHAVIOR_ROW);
	}

	public void addSound(Label sound) {
		myRule.add(sound, DEFAULT_COL, SOUND_ROW);
	}

	public void addImage(Label image) {
		myRule.add(image, DEFAULT_COL, IMAGE_ROW);		
	}

}
