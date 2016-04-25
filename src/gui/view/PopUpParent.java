package gui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class serves as the parent class for all pop ups
 * 
 * @author Stephen
 *
 */
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
	
	/**
	 * Initializes the pop up's stage, group, and scene
	 */
	private void initializePopUp() {
		myStage = new Stage();
		myRoot = new Group();
		myScene = new Scene(myRoot, myWidth, myHeight);
	}

	/**
	 * Initializes the VBox containing all nodes to be displayed in the pop up
	 */
	private void initializeContainer() {
		myContainer = new VBox(DEFAULT_CONTAINER_SPACING);
		myContainer.setPadding(new Insets(DEFAULT_CONTAINER_PADDING));
		myContainer.prefWidthProperty().bind(myScene.widthProperty());
		myContainer.prefHeightProperty().bind(myScene.heightProperty());
		myContainer.setAlignment(Pos.CENTER);
		myRoot.getChildren().add(myContainer);
	}
	
	/**
	 * Closes the pop up
	 */
	protected void closePopUp() {
		myStage.close();
	}
	
	/**
	 * Opens the pop up
	 */
	protected void showPopUp() {
		myStage.setScene(myScene);
		myStage.show();
	}
	
	/**
	 * 
	 * @return myContainer, the VBox holding all nodes in the pop up
	 */
	public VBox getContainer() {
		return myContainer;
	}
	
	/**
	 * Binds width of all child nodes in container to the pop up container's width
	 */
	protected void bindChildrenWidthsToContainerWidth() {
		myContainer.getChildren().stream().forEach(node -> bindChildWidthToParentWidth(node));
	}
	
	/**
	 * Binds the width of a single node to the width of the pop up container
	 * 
	 * @param node: node whose width is to be bound to the pop up container's width
	 */
	private void bindChildWidthToParentWidth(Node node) {
		((Region) node).prefWidthProperty().bind(myContainer.widthProperty());
	}

}
