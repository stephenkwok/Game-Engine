package gui.view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class PopUpParent {
	
	private static final double DEFAULT_CONTAINER_PADDING = 10.0;
	private String myBackgroundColor;
	private VBox myContainer;
	private int myWidth;
	private int myHeight;
	private Stage myStage;
	private Group myRoot;
	private Scene myScene;
	
	public PopUpParent(String backgroundColor, int popUpWidth, int popUpHeight) {
		myBackgroundColor = backgroundColor;
		myWidth = popUpWidth;
		myHeight = popUpHeight;
		initializePopUp();
		initializeContainer();
		showPopUp();
	}
	
	private void initializePopUp() {
		myStage = new Stage();
		myRoot = new Group();
		myScene = new Scene(myRoot, myWidth, myHeight);
	}

	private void initializeContainer() {
		myContainer = new VBox(DEFAULT_CONTAINER_PADDING);
		myContainer.setStyle(myBackgroundColor);
		myContainer.prefWidthProperty().bind(myScene.widthProperty());
		myContainer.prefHeightProperty().bind(myScene.heightProperty());
		myContainer.setAlignment(Pos.TOP_LEFT);
		myRoot.getChildren().add(myContainer);
	}
	
	protected void closePopUp() {
		myStage.close();
	}
	
	protected void showPopUp() {
		myStage.show();
	}
	
	public VBox getContainer() {
		return myContainer;
	}

}
