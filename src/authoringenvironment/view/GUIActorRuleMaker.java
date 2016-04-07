package authoringenvironment.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUIActorRuleMaker implements IGUI {
//	private StackPane myWrapperPane;
//	private ScrollPane myScrollPane;
	private GridPane myContent;
	
	public GUIActorRuleMaker() {
		initializeEnvironment();
	}
	
	private void initializeEnvironment(){
		myContent = new GridPane();
		Rectangle rect = new Rectangle(1000,10);
		rect.setFill(Color.RED);
//		myContent.add(rect, 0, 0);
		myContent.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	@Override
	public Pane getPane() {
		return myContent;
	}

	public void addNode(Node node){
		myContent.add(node, 0, 0);
	}

}
