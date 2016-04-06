package gameplayer.view;

import javafx.scene.Scene;

public class HUDScreen extends Window implements IHUDScreen{
	
	public HUDScreen(double width, double height) {
		super(width, height);
	}

	@Override
	public void update() {
		
	}

	@Override
	public Scene init() {
		Scene myScene = new Scene(super.getRoot(), super.getWidth(), super.getHeight());
		return myScene;
	}

}
