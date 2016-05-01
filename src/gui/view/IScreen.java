package gui.view;

import javafx.scene.Scene;

public interface IScreen {

	public Scene getScene();

	public void showError(String message);

}
