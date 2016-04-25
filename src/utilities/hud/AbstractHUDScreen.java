package utilities.hud;

import java.util.Observable;

import javafx.scene.SubScene;

public abstract class AbstractHUDScreen extends Observable{
	public abstract void handleChange(ValueChange change);
	public abstract void init();
	public abstract SubScene getScene();
}
