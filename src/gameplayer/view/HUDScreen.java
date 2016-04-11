package gameplayer.view;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class HUDScreen extends Window implements IHUDScreen{
	
	private ObservableMap<String, Object> status;
	Map<String, Integer> valueToRowMap;
	Map<Integer, String> rowToValueMap;
	private SubScene myScene;
	
	public HUDScreen(double width, double height, ObservableMap<String, Object> status, Map<Integer, String> rowToValueMap) {
		super(width, height);
		this.status = status;
		this.rowToValueMap = rowToValueMap;
		this.valueToRowMap = new HashMap<String, Integer>();
		for (int i = 0; i<rowToValueMap.size(); i++) {
			valueToRowMap.put(rowToValueMap.get(i), i);
		}
	}
	
	
	public HUDScreen(double width, double height, ObservableMap<String, Object> status) {
		this(width, height, status, new HashMap<Integer, String>());
		int i = 0;
		for (String value : status.keySet()) {
			rowToValueMap.put(i, value);
			valueToRowMap.put(value, i);
			i++;
		}
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void init() {
		myScene = new SubScene(super.getRoot(), super.getWidth(), super.getHeight());
		myScene.setFocusTraversable(false);
		
        ObservableList<String> keys = FXCollections.observableArrayList();
        ObservableList<String> values = FXCollections.observableArrayList();
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
        
        status.addListener(new MapChangeListener<String, Object>() {
			@Override
			public void onChanged(Change<? extends String, ? extends Object> change) {
				int rownum = valueToRowMap.get(change.getKey());
				values.set(rownum, change.getValueAdded().toString());
			}
        });
        
        super.getRoot().getChildren().add(container);
	}
	
	public SubScene getScene() {
		return myScene;
	}

}
