package gameplayer.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;


public class HUDScreen extends Observable {

	
	private static final int DEFAULT_HEIGHT = 100;
	private static final int DEFAULT_WIDTH = 200;
	
	private Map<String, Object> status;
	Map<String, Integer> valueToRowMap;
	Map<Integer, String> rowToValueMap;
	private SubScene myScene;
    ObservableList<String> keys;
    ObservableList<String> values;
    private Group mySubGroup;
    
	public HUDScreen(double width, double height, Map<String, Object> status, Map<Integer, String> rowToValueMap) {
		mySubGroup = new Group();
		myScene = new SubScene(mySubGroup, width, height);
		myScene.setFocusTraversable(false);
		this.status = status;
		this.rowToValueMap = rowToValueMap;
		this.valueToRowMap = new HashMap<String, Integer>();
		for (int i = 0; i<rowToValueMap.size(); i++) {
			valueToRowMap.put(rowToValueMap.get(i), i);
		}
	}
	
	
	public HUDScreen(double width, double height, Map<String, Object> status) {
		this(width, height, status, new HashMap<Integer, String>());
		int i = 0;
		for (String value : status.keySet()) {
			rowToValueMap.put(i, value);
			valueToRowMap.put(value, i);
			i++;
		}
	}
	
	public HUDScreen(Map<String, Object> status) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, status);
	}
	
	
	public void init() {
		
		keys = FXCollections.observableArrayList();
		values = FXCollections.observableArrayList();
        ListView<String> keyView = new ListView<>(keys);
        ListView<String> valueView = new ListView<>(values);
        keyView.setMaxWidth(myScene.getWidth()/2);
        valueView.setMaxWidth(myScene.getWidth()/2);
        
        BorderPane container = new BorderPane();
        container.setFocusTraversable(false);
        container.setLeft(keyView);
        container.setRight(valueView);
		
        for (int i = 0; i<status.size(); i++) {
        	keys.add(rowToValueMap.get(i));
        	values.add(status.get(rowToValueMap.get(i)).toString());
        }
        
	}
	
	public SubScene getScene() {
		return myScene;
	}
	
	public void handleChange(Change<? extends String, ? extends Object> change) {
		int rownum = valueToRowMap.get(change.getKey());
		values.set(rownum, change.getValueAdded().toString());
	}
	
	
	
}
