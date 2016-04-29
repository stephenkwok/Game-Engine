package gui.view;

import java.lang.reflect.InvocationTargetException;

import javafx.scene.Scene;

public interface IScreen {

	public Scene getScene();

	public void showError(String message);

}
