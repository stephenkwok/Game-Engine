package gameplayer.view;

import java.util.Map.Entry;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class HUDScreen extends Window implements IHUDScreen{
	
	private ObservableMap<String, String> status = FXCollections.observableHashMap();
	
	public HUDScreen(double width, double height) {
		super(width, height);
	}

	@Override
	public void update() {
		
	}

	@Override
	public Scene init() {
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight());
		
		Text level = new Text(status.get("Level"));
		
		super.getRoot().getChildren().add(level);
		
		return myScene;
	}

}
