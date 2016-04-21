package gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class PopUpParent {
	
	private static final double DEFAULT_CONTAINER_PADDING = 25.0;
	private static final double DEFAULT_CONTAINER_SPACING = 10.0;
	private VBox myContainer;
	private int myWidth;
	private int myHeight;
	private Stage myStage;
	private Group myRoot;
	private Scene myScene;
	
	public PopUpParent(int popUpWidth, int popUpHeight) {
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
		myContainer = new VBox(DEFAULT_CONTAINER_SPACING);
		myContainer.setPadding(new Insets(DEFAULT_CONTAINER_PADDING));
		myContainer.prefWidthProperty().bind(myScene.widthProperty());
		myContainer.prefHeightProperty().bind(myScene.heightProperty());
		myContainer.setAlignment(Pos.CENTER);
		myRoot.getChildren().add(myContainer);
	}
	
	protected void closePopUp() {
		myStage.close();
	}
	
	protected void showPopUp() {
		myStage.setScene(myScene);
		myStage.show();
	}
	
	public VBox getContainer() {
		return myContainer;
	}

}
