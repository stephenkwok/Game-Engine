package gameplayer.view;

import java.lang.reflect.InvocationTargetException;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import gui.view.IGUIElement;
import gui.view.Screen;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class displays the high score player data on a BorderPane and includes
 * a ToolBar for necessary screen functionality
 * @author michaelfigueiras
 *
 */
public class HighScoreScreen extends Screen implements Observer{

	private static final String SCORES_RESOURCE = "hsGUI";
	private static final String TOP_BUTTONS = "TopButtons";

	private BorderPane myPane;
	private Map<String, Integer> myMap;
	private String myName;
	
	/**
	 * 
	 * @param Stage s to change the scene
	 * @param myMap which contains player-score relationships, parsed from an xml file
	 * @param gameName to specify the given data
	 */
	public HighScoreScreen(Map<String, Integer> myMap, String gameName) {
		super();
		setUpResourceBundle(SCORES_RESOURCE);
		this.myMap = myMap;
		this.myName = gameName;
		this.myPane =  new BorderPane();
		initialize();
	}
	
	@Override
	protected void initialize() {
		addComponents();
		addScorePane();
		getRoot().getChildren().add(myPane);
	}

	/**
	 * Populates the center pane with map elements made into HBoxes for each individual score
	 */
	private void addScorePane() {
		VBox masterV = new VBox(20);
		masterV.getChildren().add(new Text(myName));
		for(String player : myMap.keySet()){
			HBox myH = new HBox(10);
			Text myPlayer = new Text(player);
			myPlayer.setFont(Font.font("Helvetica", 30));
			myH.getChildren().add(myPlayer);
			Text myScore = new Text(myMap.get(player).toString());
			myScore.setFont(Font.font("Times New Roman", 30));
			myH.getChildren().add(myScore);
			masterV.getChildren().add(myH);
		}
		myPane.setCenter(masterV);
		
	}

	/**
	 * 
	 */
	private void addComponents(){
		myPane.setLeft(null);
		String[] sideButtons = getResources().getString(TOP_BUTTONS).split(",");
		ToolBar myT = new ToolBar();
		myT.setMinWidth(SCREEN_WIDTH);
		myT.setOrientation(Orientation.HORIZONTAL);
		for(int i = 0; i < sideButtons.length; i++){
			IGUIElement newElement = getFactory().createNewGUIObject(sideButtons[i]);
			newElement.addNodeObserver(this);
			Button myB = (Button) newElement.createNode();
			Tooltip t = new Tooltip(getResources().getString(sideButtons[i]+ "Text"));
			t.install(myB, t);
			myT.getItems().add(myB);
			myB.setFocusTraversable(false);
		}
		myPane.setTop(myT);
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(o.getClass().getSimpleName());
		
	}

}